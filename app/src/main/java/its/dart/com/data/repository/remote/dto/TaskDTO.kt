package its.dart.com.data.repository.remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class TaskDto(
    val status: Int,
    val message: String,
    val time: String,
    val taskid: String,
)

@Serializable
data class TaskRequestDto (
    val user_id: Int,
    val task_id: Int,
    val lat: String,
    val lng: String,
    val taskname: String,
    val user_type: Int
)