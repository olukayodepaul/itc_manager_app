package its.dart.com.domain.repository.remote


import its.dart.com.domain.repository.remote.model.LoginModel


interface LoginRemoteRep {
    suspend fun login(username: String, password: String): LoginModel
}