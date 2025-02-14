package its.dart.com.data.repository.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    val status: Int,
    val message: String,
    @SerialName("trans_date") val transDate: String,
    val data: UserDataDto? = null,
    val products: List<ProductDto>? = null,
    val error: ErrorDto? = null
)

@Serializable
data class UserDataDto(
    val users: UserDto,
    val rep: List<RepsDto>
)

@Serializable
data class UserDto (
    @SerialName("full_name") val fullName: String,
    val id: Int,
    @SerialName("depot_id") val depotId: Int,
    @SerialName("system_category_id") val systemCategoryId: Int,
    @SerialName("depot_lat") val depotLat: String,
    @SerialName("depot_lng") val depotLng: String
)

@Serializable
data class RepsDto(
    val id: Int,
    @SerialName("route_id") val routeId: Int,
    @SerialName("full_name") val fullName: String,
    @SerialName("staff_code") val staffCode: String,
    @SerialName("phone_no") val phoneNo: String? = null,
    @SerialName("route_name") val routeName: String? = null,
    val state: Int,
    val time: String? = null
)

@Serializable
data class ProductDto(
    val id: Int,
    val item: String,
    val code: String,
    val qty: String? = null,
)

@Serializable
data class ErrorDto(
    @SerialName("error_message") val errorMessage: String,
    @SerialName("error_code") val errorCode: Int
)
