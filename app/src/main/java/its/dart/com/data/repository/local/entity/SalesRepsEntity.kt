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