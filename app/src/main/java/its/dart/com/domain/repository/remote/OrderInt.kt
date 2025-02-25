package its.dart.com.domain.repository.remote

import its.dart.com.data.repository.remote.dto.OrderDTO
import its.dart.com.data.repository.remote.dto.PackPlacementDTO
import its.dart.com.data.repository.remote.dto.SurveyStateResDTO

interface OrderInt {
    suspend fun taskRequest(body: OrderDTO): Result<SurveyStateResDTO>
}