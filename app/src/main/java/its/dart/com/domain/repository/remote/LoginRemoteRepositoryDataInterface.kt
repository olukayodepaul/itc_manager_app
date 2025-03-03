package its.dart.com.domain.repository.remote


import its.dart.com.data.repository.remote.dto.MerchantDTO
import its.dart.com.domain.repository.remote.model.LoginModel

interface LoginRemoteRepositoryDataInterface {
    suspend fun login(username: String, password: String): LoginModel
    suspend fun getMerchantAndPromoterCustomers(category_id: String, user_id: String): List<MerchantDTO>
}