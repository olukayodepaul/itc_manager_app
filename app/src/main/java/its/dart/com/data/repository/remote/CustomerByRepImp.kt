package its.dart.com.data.repository.remote


import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import its.dart.com.data.repository.remote.dto.CustomersDto
import its.dart.com.domain.repository.remote.CustomerByRepInterface
import its.dart.com.domain.repository.remote.model.CustomersModel
import its.dart.com.mapper.toCustomersModel
import javax.inject.Inject

class CustomerByRepImp @Inject constructor(private val httpClient: HttpClient): CustomerByRepInterface {

    override suspend fun customer(userId: Int, weekDay: Int, properId: Int): CustomersModel {
        return  try {
            val response = httpClient.get("/v3/customers") {
                url{
                    parameter("userid", userId)
                    parameter("week_days", weekDay) //subtract from day
                    parameter("proper_id", properId)
                }
            }
            response.body<CustomersDto>().toCustomersModel()
        }catch (e: Exception) {
            throw Exception("Customers failed: ${e.message}", e)
        }
    }
}