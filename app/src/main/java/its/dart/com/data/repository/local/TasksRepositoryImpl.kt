package its.dart.com.data.repository.local

import its.dart.com.data.repository.local.database.LocalDatabase
import its.dart.com.data.repository.local.entity.TasksEntity
import its.dart.com.domain.repository.local.TasksRepository
import its.dart.com.domain.repository.remote.model.TasksDTO
import its.dart.com.mapper.toTasksDTOList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val localDatabase: LocalDatabase
) : TasksRepository {

    override fun getTasks(): Flow<List<TasksDTO>> {
        return localDatabase.getTasks().map { it.toTasksDTOList() }
    }

    override suspend fun persistTask(task: TasksEntity) {
        return localDatabase.persistTask(task)
    }

}