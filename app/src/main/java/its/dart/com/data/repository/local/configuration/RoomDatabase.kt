package its.dart.com.data.repository.local.configuration

import androidx.room.Database
import androidx.room.RoomDatabase
import its.dart.com.data.repository.local.entity.SalesRepsEntity



@Database(
    entities = [SalesRepsEntity::class], version = 1, exportSchema = false
)
abstract class RoomDatabase: RoomDatabase() {
    abstract val doa: RoomDatabaseTable

    companion object{
        val DARTSPATIALBUILDER = "sales_reps_database"
    }
}