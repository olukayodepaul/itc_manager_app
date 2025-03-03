package its.dart.com.domain.usecases


import its.dart.com.data.repository.remote.dto.MerchantDTO
import its.dart.com.domain.repository.remote.LoginRemoteRepositoryDataInterface
import its.dart.com.domain.repository.remote.model.LoginModel
import javax.inject.Inject

class LoginUseCases @Inject constructor(
    private val loginRemoteRep: LoginRemoteRepositoryDataInterface,
) {

    suspend fun invokeLogin(username: String, password: String): Result<LoginModel> {
        return try {
            val getLoginResponse = loginRemoteRep.login(username, password)
            Result.success(getLoginResponse)
        }catch(e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun invokeGetMerchantAndPromoterCustomers(category_id: String, user_id: String) : List<MerchantDTO>{
            return loginRemoteRep.getMerchantAndPromoterCustomers(category_id, user_id)
    }

}