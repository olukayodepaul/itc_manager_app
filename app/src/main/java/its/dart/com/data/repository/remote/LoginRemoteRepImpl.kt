package its.dart.com.data.repository.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import its.dart.com.data.repository.remote.dto.LoginDto
import its.dart.com.domain.repository.remote.LoginRemoteRep
import its.dart.com.domain.repository.remote.model.LoginModel
import its.dart.com.mapper.toLoginModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

//no any business logic, just alone fetch the data
class LoginRemoteRepImpl @Inject constructor(private val httpClient: HttpClient) : LoginRemoteRep {

    override suspend fun login(username: String, password: String): LoginModel {
           return  try {
                val response: LoginDto = httpClient.post("v3/login") {
                    setBody(
                        mapOf(
                            "username" to username,
                            "password" to password
                        )
                    )
                }.body()

               response.toLoginModel()

            }catch (e: Exception) {
                throw Exception("Login request failed: ${e.message}", e)
            }
    }
}