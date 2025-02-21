package its.dart.com.domain.repository.local

import its.dart.com.data.repository.local.entity.MerchantEntity
import its.dart.com.data.repository.local.entity.PromoterEntity
import kotlinx.coroutines.flow.Flow

interface OtherCustomer {
    fun getOtherPromoters(): Flow<List<PromoterEntity>>
    suspend fun persistOtherPromoters(task: PromoterEntity)
    suspend fun persistOtherMerchants(task: MerchantEntity)
    fun getOtherMerchant(): Flow<List<MerchantEntity>>
}