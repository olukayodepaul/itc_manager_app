package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.domain.repository.TaskRemote
import its.dart.com.domain.repository.local.TasksRepository
import its.dart.com.domain.repository.remote.model.TaskRequestModel
import its.dart.com.domain.repository.remote.model.TasksModel
import its.dart.com.mapper.mapToTaskDto
import its.dart.com.mapper.toTasksEntity
import its.dart.com.presentation.viewmodel.event.TaskViewEvent
import its.dart.com.presentation.viewmodel.state.TaskViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TaskViewModel @Inject constructor(
    private val localRep: TasksRepository,
    private val remoteRepo: TaskRemote
    ): ViewModel() {

    private val _taskUpdate = MutableStateFlow(TaskViewState())
    val taskUpdate: StateFlow<TaskViewState> = _taskUpdate

    init {
        getTask()
    }

    fun onEvent(event: TaskViewEvent) {
        viewModelScope.launch {
            eventListener(event)
        }
    }

    private suspend fun eventListener(event: TaskViewEvent) {
        when (event) {
            is TaskViewEvent.OnClickResume -> {
                try {
                    if (_taskUpdate.value.resume.isBlank() || _taskUpdate.value.resume == "00:00:00") {
                        _taskUpdate.value = _taskUpdate.value.copy(dialogLoader = true)

                        val request = TaskRequestModel(
                            userId = 2,
                            taskId = 1,
                            lat = "0.2",
                            lng = "0.5",
                            taskName = "resume",
                            userType = 3
                        )

                        val result = remoteRepo.taskRequest(request)

                        val cache = TasksModel
                            .builder()
                            .setTaskId(result.taskId.toInt())
                            .setLatitude("0.0")
                            .setLongitude("0.0")
                            .setUserId(1)
                            .setTimeAgo(result.time)
                            .build()

                        localRep.persistTask(cache.toTasksEntity())
                        _taskUpdate.value = _taskUpdate.value.copy(resume = result.time)
                    }

                    _taskUpdate.value = _taskUpdate.value.copy(dialogLoader = false)
                } catch (e: Exception) {
                    _taskUpdate.value = _taskUpdate.value.copy(dialogLoader = false)
                }
            }
            is TaskViewEvent.OnClickClockOut -> { /* TODO */ }
            is TaskViewEvent.OnClickClockIn -> { /* TODO */ }
            is TaskViewEvent.OnClickClose -> { /* TODO */ }
        }
    }


    private fun getTask() {
        viewModelScope.launch {
            localRep.getTasks().collect { fetchedTask ->
                val newState = _taskUpdate.value.copy(
                    resume = fetchedTask.find { it.taskId == 1 }?.timeAgo ?: "00:00:00",
                    clockOut = fetchedTask.find { it.taskId == 2 }?.timeAgo ?: "00:00:00",
                    clockIn = fetchedTask.find { it.taskId == 3 }?.timeAgo ?: "00:00:00",
                    close = fetchedTask.find { it.taskId == 4 }?.timeAgo ?: "00:00:00"
                )
                _taskUpdate.value = newState
            }
        }
    }

}