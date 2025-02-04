package its.dart.com.data.repository.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Entity(tableName = "logins")
data class SalesRepsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val route_id: Int,
    val full_name: String,
    val staff_code: String,
    val phone_no: String,
    val route_name: String,
    var state: Int,
    var time: String
)

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val item: String,
    val code: String,
    val qty: String? = null
)

@Entity(tableName = "customers")
data class AllCustomersEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val urno: Int,
    val latitude: String? = "0.0",
    val longitude: String? = "0.0",
    val posid: String? = "0.0",
    val outlet_name: String,
    val outlet_address: String,
    val contact_phone: String,
)
