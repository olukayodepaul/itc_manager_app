package its.dart.com.domain.repository.remote

import its.dart.com.domain.repository.remote.model.TaskModel
import its.dart.com.domain.repository.remote.model.TaskRequestModel

interface TaskRemote {
    suspend fun taskRequest(body : TaskRequestModel): TaskModel
}