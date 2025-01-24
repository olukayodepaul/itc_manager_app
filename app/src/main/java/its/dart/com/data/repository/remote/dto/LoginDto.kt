package its.dart.com.data.repository.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginDto(
    val status: Int? = null,
    val message:String? = null,
    val data: DataDto?,
    val error: ErrorDto,
    @SerialName("trans_date")
    val transDate:String
)

@Serializable
data class DataDto(
    val user: UsersDto,
    val allReps: List<AllRepsDto> = emptyList(),
    val toke:String,
    val expiresIn:String
)

@Serializable
data class UsersDto(
    val uis:Int,
    @SerialName("user_name")
    val userName: String? = null,
    val role: String? = null,
    @SerialName("deport_id")
    val deportId: Int? = null,
)

@Serializable
data class AllRepsDto(
    val id:Int? = null,

    @SerialName("route_id")
    val routeId:Int? = null,

    @SerialName("rep_name")
    val repName:String,

    @SerialName("rep_contact")
    val repContact:String
)

@Serializable
data class ErrorDto(
    @SerialName("error_code")
    val errorCode:String? = null,
    val description:String? = null,
)