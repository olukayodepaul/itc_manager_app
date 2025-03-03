package its.dart.com.data.repository.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import its.dart.com.data.repository.remote.dto.DailyActivityViewDTO
import its.dart.com.data.repository.remote.dto.DailyCustomerDTO
import its.dart.com.data.repository.remote.dto.SurveyStateResDTO
import its.dart.com.domain.repository.remote.DailyActivityInt
import javax.inject.Inject

class DailyActivityImpl @Inject constructor(private val httpClient: HttpClient) : DailyActivityInt {

    override suspend fun taskDailyRetail(body: DailyActivityViewDTO): Result<SurveyStateResDTO> {
        return runCatching {
            val response = httpClient.post("/v3/daily/activity") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            response.body<SurveyStateResDTO>()
        }
    }

    override suspend fun taskDailyCustomer(body: DailyCustomerDTO): Result<SurveyStateResDTO> {
        return runCatching {
            val response = httpClient.post("/v3/daily/customer") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            response.body<SurveyStateResDTO>()
        }
    }

}