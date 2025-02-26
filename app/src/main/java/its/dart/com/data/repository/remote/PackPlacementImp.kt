package its.dart.com.data.repository.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import its.dart.com.data.repository.remote.dto.PackPlacementDTO
import its.dart.com.data.repository.remote.dto.SurveyStateResDTO
import its.dart.com.domain.repository.remote.PackPlacement
import javax.inject.Inject

class PackPlacementImp @Inject constructor(private val httpClient: HttpClient) : PackPlacement {
    override suspend fun taskPackPlacement(body: PackPlacementDTO): Result<SurveyStateResDTO> {
        return runCatching {
            val response = httpClient.post("/v3/pack/placement") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            response.body<SurveyStateResDTO>()
        }
    }
}