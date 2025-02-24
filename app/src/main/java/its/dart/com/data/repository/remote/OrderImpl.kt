package its.dart.com.data.repository.remote

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import its.dart.com.data.repository.remote.dto.OrderDTO
import its.dart.com.data.repository.remote.dto.SurveyStateResDTO
import its.dart.com.domain.repository.remote.OrderInt
import javax.inject.Inject


class OrderImpl @Inject constructor(private val httpClient: HttpClient) : OrderInt {
    override suspend fun taskRequest(body: OrderDTO): Result<SurveyStateResDTO> {

        Log.d("epokai", body.toString())
        return runCatching {
            val response = httpClient.post("/v3/orders") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            Log.d("epokai", "${response.body<SurveyStateResDTO>()}")
            response.body<SurveyStateResDTO>()
        }
    }
}