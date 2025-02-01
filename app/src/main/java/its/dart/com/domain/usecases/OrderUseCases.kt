package its.dart.com.domain.usecases

import its.dart.com.data.repository.local.database.LocalDatabase
import its.dart.com.domain.repository.remote.model.ProductModel
import its.dart.com.mapper.toModelList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class OrderUseCases @Inject constructor(private val localDatabase: LocalDatabase) {
    fun fetchProduct(): Flow<List<ProductModel>> {
        return localDatabase.getAllProducts()
            .map {
                it.toModelList()
            }
            .flowOn(Dispatchers.IO)
    }
}