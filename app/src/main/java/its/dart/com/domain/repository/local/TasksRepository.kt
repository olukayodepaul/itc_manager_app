package its.dart.com.domain.repository.local

import its.dart.com.data.repository.local.entity.TasksEntity
import its.dart.com.domain.repository.remote.model.TasksDTO
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun getTasks(): Flow<List<TasksDTO>>
    suspend fun persistTask(task: TasksEntity)
}