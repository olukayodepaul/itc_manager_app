package its.dart.com.data.repository.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import its.dart.com.data.repository.local.entity.ProductEntity
import its.dart.com.data.repository.local.entity.SalesRepsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface LocalDatabase {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun persistLogin(logins: List<SalesRepsEntity>)

    @Query("SELECT * FROM logins")
    fun getAllSalesReps(): Flow<List<SalesRepsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun persistProduct(product: List<ProductEntity>)

    @Query("SELECT * FROM product")
    fun getAllProducts(): Flow<List<ProductEntity>>

}


