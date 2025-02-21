package its.dart.com.data.repository.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import its.dart.com.data.repository.local.entity.AllCustomersEntity
import its.dart.com.data.repository.local.entity.PromoterEntity
import its.dart.com.data.repository.local.entity.MerchantEntity
import its.dart.com.data.repository.local.entity.ProductEntity
import its.dart.com.data.repository.local.entity.SalesRepsEntity
import its.dart.com.data.repository.local.entity.TasksEntity
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

    //customers information
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun persistCustomer(logins: List<AllCustomersEntity>)

    @Query("SELECT * FROM customers WHERE properid =:properid AND rep_id =:rep_id ")
    fun getCustomers(properid: Int, rep_id: Int): Flow<List<AllCustomersEntity>>

    @Query("SELECT COUNT(id) FROM customers WHERE properid =:properid AND rep_id =:rep_id ")
    fun getCustomer(properid: Int, rep_id: Int): Flow<Int>

    @Query("DELETE FROM customers")
    suspend fun delCustomer(): Int

    //task fetch from repository
    @Query("SELECT * FROM tasks")
    fun getTasks(): Flow<List<TasksEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun persistTask(task: TasksEntity)

    //other customers.best is to map. but i am not mapping.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun persistPromoter(task: PromoterEntity)

    @Query("SELECT * FROM promoter order by id desc")
    fun getPromoters(): Flow<List<PromoterEntity>>

    //merchant
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun persistMerchants(task: MerchantEntity)

    @Query("SELECT * FROM promoter order by id desc")
    fun getMerchants(): Flow<List<MerchantEntity>>

}


