package its.dart.com.domain.usecases

import its.dart.com.data.repository.local.configuration.RoomDatabaseTable
import its.dart.com.domain.repository.remote.model.RepsModel
import its.dart.com.mapper.toRepsModelList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RepUseCases @Inject constructor(private val localCache: RoomDatabaseTable) {

    fun fetchSalesReps(): Flow<List<RepsModel>> {
        return localCache.getAllSalesReps()
            .map { it.toRepsModelList() }
            .flowOn(Dispatchers.IO)
    }

}