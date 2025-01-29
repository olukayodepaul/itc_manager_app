package its.dart.com.data.repository.local.configuration

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import its.dart.com.data.repository.local.entity.SalesRepsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RoomDatabaseTable {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLogin(login: SalesRepsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLogins(logins: List<SalesRepsEntity>)

    @Query("SELECT * FROM logins")
    fun getAllSalesReps(): Flow<List<SalesRepsEntity>>

    @Query("SELECT * FROM logins WHERE id = :id LIMIT 1")
    fun getSalesRepById(id: Int): Flow<SalesRepsEntity?>
}


