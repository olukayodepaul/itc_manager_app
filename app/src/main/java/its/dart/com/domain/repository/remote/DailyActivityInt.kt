package its.dart.com.domain.repository.remote

import its.dart.com.data.repository.remote.dto.DailyActivityViewDTO
import its.dart.com.data.repository.remote.dto.DailyCustomerDTO
import its.dart.com.data.repository.remote.dto.SurveyStateResDTO

interface DailyActivityInt {
    suspend fun taskDailyRetail(body: DailyActivityViewDTO): Result<SurveyStateResDTO>
    suspend fun taskDailyCustomer(body: DailyCustomerDTO): Result<SurveyStateResDTO>
}