package its.dart.com.data.repository.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomersDto(
    val status: Int,
    val message: String,
    @SerialName("trans_date") val transDate: String,
    val data: List<AllCustomersDto>
)

@Serializable
data class AllCustomersDto (
    val id: Int,
    val urno: Int,
    val latitude: String? = "0.0",
    val longitude: String? = "0.0",
    val posid: String? = "0.0",
    @SerialName("outlet_name") val outletName: String,
    @SerialName("outlet_address") val outletAddress: String,
    @SerialName("contact_phone") val contactPhone: String,
)


