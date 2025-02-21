package its.dart.com.data.repository.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import its.dart.com.data.repository.remote.dto.SurveyStateDTO
import its.dart.com.data.repository.remote.dto.SurveyStateResDTO
import its.dart.com.domain.repository.remote.SurveyInt
import javax.inject.Inject

class SurveyImpl @Inject constructor(private val httpClient: HttpClient) : SurveyInt {

    override suspend fun taskRequest(body: SurveyStateDTO): Result<SurveyStateResDTO> {
        return runCatching {
            val response = httpClient.post("/v3/survey") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            response.body<SurveyStateResDTO>()
        }
    }
}