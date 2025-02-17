package its.dart.com.data.repository.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import its.dart.com.data.repository.remote.dto.LoginDto
import its.dart.com.domain.repository.remote.LoginRemoteRepositoryDataInterface
import its.dart.com.domain.repository.remote.model.LoginModel
import its.dart.com.mapper.mapToLoginModel
import javax.inject.Inject


class LoginRemoteDataImpl @Inject constructor(private val httpClient: HttpClient) :
    LoginRemoteRepositoryDataInterface {

    override suspend fun login(username: String, password: String): LoginModel {
        return try {
            val response = httpClient.get("/v3/login") {
                url {
                    parameter("username", username)
                    parameter("password", password)
                }
            }
            response.body<LoginDto>().mapToLoginModel()
        } catch (e: Exception) {
            throw Exception("Login request failed: ${e.message}", e)
        }
    }
}