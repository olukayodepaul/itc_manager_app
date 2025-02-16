package its.dart.com.data.repository.local

import its.dart.com.data.repository.local.database.LocalDatabase
import its.dart.com.data.repository.local.entity.OtherAllCustomersEntity
import its.dart.com.domain.repository.local.OtherCustomer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OtherCustomerImpl @Inject constructor(private val localDatabase: LocalDatabase): OtherCustomer {

    //return entity and leave mapping.for quick deliver, avoid mapping
    override fun getOtherCustomers(): Flow<List<OtherAllCustomersEntity>> {
        return localDatabase.getOtherCustomers()
    }

    override suspend fun persistOtherCustomers(task: OtherAllCustomersEntity) {
       return localDatabase.persistOtherCustomers(task)
    }

}