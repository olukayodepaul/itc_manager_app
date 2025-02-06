package its.dart.com.data.repository.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WholeSellersDTO(
    val status: String,
    val message: String,
    val data: List<WholeSellerDTO>
)

@Serializable
data class WholeSellerDTO(
    val id: Int,
    @SerialName("users_id") val usersId: Int,
    val name: String,
    val address: String,
    val latitude: String,
    val longitude: String,
    @SerialName("contact_phone") val contactPhone: String,
)
