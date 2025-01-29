package its.dart.com.data.repository.local.configuration

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import its.dart.com.data.repository.local.entity.SalesRepsEntity


@Dao
interface RoomDatabaseTable {
    // Insert a single Login object
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLogin(login: SalesRepsEntity)

    // Insert a list of Login objects
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLogins(logins: List<SalesRepsEntity>)
}


