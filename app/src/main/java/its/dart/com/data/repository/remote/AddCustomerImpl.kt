package its.dart.com.data.repository.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import its.dart.com.data.repository.remote.dto.AddCustomerResDTO
import its.dart.com.domain.repository.remote.AddCustomer
import its.dart.com.domain.repository.remote.model.AddCustomerReqModel
import its.dart.com.domain.repository.remote.model.AddCustomerResModel
import its.dart.com.mapper.mapToDTO
import its.dart.com.mapper.mapToModel
import javax.inject.Inject


class AddCustomerImpl  @Inject constructor(private val httpClient: HttpClient): AddCustomer {

    override suspend fun postCustomer(customer: AddCustomerReqModel): AddCustomerResModel {
        return try {
            val response = httpClient.post("/v3/add/customer") {
                contentType(ContentType.Application.Json)
                setBody(customer.mapToDTO())
            }
            response.body<AddCustomerResDTO>().mapToModel()
        } catch (e: Exception) {
            throw Exception("Task request failed: ${e.message}", e)
        }
    }

}