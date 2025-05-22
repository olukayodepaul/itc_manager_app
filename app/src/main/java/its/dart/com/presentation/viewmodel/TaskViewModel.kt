package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.domain.preference.PreferenceInt
import its.dart.com.domain.repository.remote.TaskRemote
import its.dart.com.domain.repository.local.TasksRepository
import its.dart.com.domain.repository.remote.model.TaskRequestModel
import its.dart.com.domain.repository.remote.model.TasksModel
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
    private val remoteRepo: TaskRemote,
    private val sharePreference: PreferenceInt
) : ViewModel() {

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
            is TaskViewEvent.OnClickResume -> handleTaskEvent(taskId = 1, taskName = "resume")
            is TaskViewEvent.OnClickClockOut -> handleTaskEvent(taskId = 2, taskName = "ClockOut")
            is TaskViewEvent.OnClickClockIn -> handleTaskEvent(taskId = 3, taskName = "ClockIn")
            is TaskViewEvent.OnClickClose -> handleTaskEvent(taskId = 4, taskName = "Close")
        }
    }

    private suspend fun handleTaskEvent(taskId: Int, taskName: String) {
        try {
            val currentTime = when (taskId) {
                1 -> _taskUpdate.value.resume
                2 -> _taskUpdate.value.clockOut
                3 -> _taskUpdate.value.clockIn
                4 -> _taskUpdate.value.close
                else -> "00:00:00"
            }

            if (currentTime.isBlank() || currentTime == "00:00:00") {

                _taskUpdate.value = _taskUpdate.value.copy(dialogLoader = true)

                val request = TaskRequestModel(
                    userId = sharePreference.getInt(key="id", defaultValue = 0),
                    taskId = taskId,
                    lat = "0.2",
                    lng = "0.5",
                    taskName = taskName,
                    userType = 3
                )

                val result = remoteRepo.taskRequest(request)

                val cache = TasksModel.builder()
                    .setTaskId(result.taskId.toInt())
                    .setLatitude("0.2")
                    .setLongitude("0.5")
                    .setUserId(sharePreference.getInt(key="id", defaultValue = 0))
                    .setTimeAgo(result.time)
                    .build()

                localRep.persistTask(cache.toTasksEntity())

                _taskUpdate.value = when (taskId) {
                    1 -> _taskUpdate.value.copy(resume = result.time)
                    2 -> _taskUpdate.value.copy(clockOut = result.time)
                    3 -> _taskUpdate.value.copy(clockIn = result.time)
                    4 -> _taskUpdate.value.copy(close = result.time)
                    else -> _taskUpdate.value
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            _taskUpdate.value = _taskUpdate.value.copy(dialogLoader = false)
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


