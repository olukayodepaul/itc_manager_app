package its.dart.com.domain.repository.remote

import its.dart.com.data.repository.remote.dto.DailyActivityViewDTO
import its.dart.com.data.repository.remote.dto.SurveyStateResDTO

interface DailyActivityInt {
    suspend fun taskPackPlacement(body: DailyActivityViewDTO): Result<SurveyStateResDTO>
}