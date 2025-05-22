package its.dart.com.data.repository.local

import its.dart.com.data.repository.local.database.LocalDatabase
import its.dart.com.data.repository.local.entity.MerchantEntity
import its.dart.com.data.repository.local.entity.PromoterEntity
import its.dart.com.domain.repository.local.OtherCustomer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OtherCustomerImpl @Inject constructor(private val localDatabase: LocalDatabase): OtherCustomer {

    //return entity and leave mapping.for quick deliver, avoid mapping
    override fun getOtherPromoters(): Flow<List<PromoterEntity>> {
        return localDatabase.getPromoters()
    }

    override suspend fun persistOtherPromoters(task: PromoterEntity) {
       return localDatabase.persistPromoter(task)
    }

    override suspend fun persistOtherMerchants(task: MerchantEntity) {
        return localDatabase.persistMerchants(task)
    }

    override fun getOtherMerchant(): Flow<List<MerchantEntity>> {
        return localDatabase.getMerchants()
    }

    override suspend fun searchMerchantByDefault(): List<MerchantEntity> {
        return localDatabase.searchMerchantByDefault()
    }

    override suspend fun searchMerchant(search: String): List<MerchantEntity> {
        return localDatabase.searchMerchant(search)
    }

    override suspend fun searchPromoterByDefault(): List<PromoterEntity> {
        return localDatabase.searchPromoterByDefault()
    }

    override suspend fun searchPromoter(search: String): List<PromoterEntity> {
        return localDatabase.searchPromoter(search)
    }

}