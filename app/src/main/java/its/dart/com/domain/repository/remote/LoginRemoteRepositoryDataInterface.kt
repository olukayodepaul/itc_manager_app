package its.dart.com.domain.repository.remote


import its.dart.com.domain.repository.remote.model.LoginModel

interface LoginRemoteRepositoryDataInterface {
    suspend fun login(username: String, password: String): LoginModel
}