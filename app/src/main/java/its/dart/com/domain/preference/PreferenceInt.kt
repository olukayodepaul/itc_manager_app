package its.dart.com.domain.preference

interface PreferenceInt {
    suspend fun saveString(key: String, value: String)
    suspend fun getString(key: String, defaultValue: String): String
    suspend fun saveInt(key: String, value: Int)
    suspend fun getInt(key: String, defaultValue: Int): Int
    suspend fun clearPreferences()
}