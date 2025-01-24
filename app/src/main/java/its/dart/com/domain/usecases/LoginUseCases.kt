package its.dart.com.domain.usecases

import its.dart.com.domain.repository.remote.LoginRemoteRep
import javax.inject.Inject

class LoginUseCases @Inject constructor(
    private val loginRemoteRep: LoginRemoteRep
) {

//    suspend operator fun invokeLogin(username: String, password: String): Result<LoginModel> {
//
//       val getLoginResponse: LoginModel = loginRemoteRep.login(username, password)
//
//    }

}