package its.dart.com.domain.repository.remote

import its.dart.com.data.repository.remote.dto.SurveyStateDTO
import its.dart.com.data.repository.remote.dto.SurveyStateResDTO

interface SurveyInt {
    suspend fun taskRequest(body: SurveyStateDTO): Result<SurveyStateResDTO>
}