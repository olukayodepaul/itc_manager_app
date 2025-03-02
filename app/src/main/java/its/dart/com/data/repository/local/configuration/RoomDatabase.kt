package its.dart.com.data.repository.local.configuration

import androidx.room.Database
import androidx.room.RoomDatabase
import its.dart.com.data.repository.local.database.LocalDatabase
import its.dart.com.data.repository.local.entity.AllCustomersEntity
import its.dart.com.data.repository.local.entity.PromoterEntity
import its.dart.com.data.repository.local.entity.MerchantEntity
import its.dart.com.data.repository.local.entity.ProductEntity
import its.dart.com.data.repository.local.entity.SalesRepsEntity
import its.dart.com.data.repository.local.entity.TasksEntity


@Database(
    entities = [SalesRepsEntity::class, ProductEntity::class, AllCustomersEntity::class,
        TasksEntity::class, PromoterEntity::class,
        MerchantEntity::class], version = 1, exportSchema = false
)
abstract class RoomDatabase: RoomDatabase() {
    abstract val doa: LocalDatabase

    companion object{
        val DARTSPATIALBUILDER = "sales_reps_database"
    }
}