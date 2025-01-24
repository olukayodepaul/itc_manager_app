package its.dart.com.domain.repository.remote


import its.dart.com.domain.repository.remote.model.LoginModel
import kotlinx.coroutines.flow.Flow


interface LoginRemoteRep {
    suspend fun login(username: String, password: String): LoginModel
}