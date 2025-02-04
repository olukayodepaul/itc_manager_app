package its.dart.com.domain.repository

interface PreferenceInt {
    fun saveString(key: String, value: String)
    fun getString(key: String, defaultValue: String): String
    fun clearPreferences()
}