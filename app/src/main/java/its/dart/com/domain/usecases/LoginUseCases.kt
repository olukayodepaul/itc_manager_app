package its.dart.com.domain.usecases

import its.dart.com.domain.repository.remote.LoginRemoteRep
import its.dart.com.domain.repository.remote.model.LoginModel
import javax.inject.Inject

class LoginUseCases @Inject constructor(
    private val loginRemoteRep: LoginRemoteRep
) {
    suspend fun invokeLogin(username: String, password: String): Result<LoginModel> {
        return try {
            val getLoginResponse = loginRemoteRep.login(username, password)
            Result.success(getLoginResponse)
        }catch(e: Exception) {
            Result.failure(e)
        }
    }
}