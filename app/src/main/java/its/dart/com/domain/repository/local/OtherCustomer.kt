package its.dart.com.domain.repository.local

import its.dart.com.data.repository.local.entity.OtherAllCustomersEntity
import kotlinx.coroutines.flow.Flow

interface OtherCustomer {
    fun getOtherCustomers(): Flow<List<OtherAllCustomersEntity>>
    suspend fun persistOtherCustomers(task: OtherAllCustomersEntity)
}