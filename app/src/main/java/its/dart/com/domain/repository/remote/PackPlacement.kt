package its.dart.com.domain.repository.remote

import its.dart.com.data.repository.remote.dto.PackPlacementDTO
import its.dart.com.data.repository.remote.dto.SurveyStateResDTO

interface PackPlacement {
    suspend fun taskPackPlacement(body: PackPlacementDTO): Result<SurveyStateResDTO>
}