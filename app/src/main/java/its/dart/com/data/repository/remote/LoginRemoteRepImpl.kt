package its.dart.com.data.repository.remote

import io.ktor.client.HttpClient
import its.dart.com.domain.repository.remote.LoginRemoteRep
import javax.inject.Inject

class LoginRemoteRepImpl @Inject constructor(private val httpClient: HttpClient) : LoginRemoteRep{

    override fun login(username: String, password: String) {
        TODO("Not yet implemented")
    }

}