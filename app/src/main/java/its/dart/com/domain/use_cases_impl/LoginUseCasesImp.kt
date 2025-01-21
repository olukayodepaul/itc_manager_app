package its.dart.com.domain.use_cases_impl

import its.dart.com.domain.repository.remote.LoginRemoteRep
import its.dart.com.domain.use_cases_intr.LoginUserCases
import javax.inject.Inject

class LoginUseCasesImp @Inject constructor(
    private val loginUserCases: LoginRemoteRep
): LoginUserCases {

    override suspend fun login(username: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

}