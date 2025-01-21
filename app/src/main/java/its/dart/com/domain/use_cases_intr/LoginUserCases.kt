package its.dart.com.domain.use_cases_intr

interface LoginUserCases {
    suspend fun login(username: String, password: String): Boolean
}