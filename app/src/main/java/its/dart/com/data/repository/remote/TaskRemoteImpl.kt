package its.dart.com.data.repository.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import its.dart.com.data.repository.remote.dto.TaskDto
import its.dart.com.domain.repository.remote.TaskRemote
import its.dart.com.domain.repository.remote.model.TaskModel
import its.dart.com.domain.repository.remote.model.TaskRequestModel
import its.dart.com.mapper.mapToTaskModel
import its.dart.com.mapper.toTaskRequestDto
import javax.inject.Inject

class TaskRemoteImpl @Inject constructor(private val httpClient: HttpClient) : TaskRemote {

    override suspend fun taskRequest(body: TaskRequestModel): TaskModel {
        return try {
            val response = httpClient.post("/v3/task") {
                contentType(ContentType.Application.Json)
                setBody(body.toTaskRequestDto())
            }
            response.body<TaskDto>().mapToTaskModel()
        } catch (e: Exception) {
            throw Exception("Task request failed: ${e.message}", e)
        }
    }

}