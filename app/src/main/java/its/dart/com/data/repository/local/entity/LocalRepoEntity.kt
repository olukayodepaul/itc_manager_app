package its.dart.com.data.repository.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


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

//list of reps
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
    val rep_id: Int = 0,
    val properid: Int = 0,
)

@Entity(tableName = "tasks")
data class TasksEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val task_id: Int? = 0,
    val latitude: String? = "0.0",
    val longitude: String? = "0.0",
    val user_id: String? = "0.0",
    val time_ago: String? = "0.0",
)


@Entity(tableName = "promoter")
data class PromoterEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val urno: Int,
    val latitude: String? = "0.0",
    val longitude: String? = "0.0",
    val outlet_name: String,
    val outlet_address: String,
    val contact_phone: String,
    val outlet_type: String
)

@Entity(tableName = "merchant")
data class MerchantEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val urno: Int,
    val latitude: String? = "0.0",
    val longitude: String? = "0.0",
    val outlet_name: String,
    val outlet_address: String,
    val contact_phone: String,
    val outlet_type: String
)

