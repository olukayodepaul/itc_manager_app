package its.dart.com.data.repository.prefence

import android.content.SharedPreferences
import its.dart.com.domain.preference.PreferenceInt
import javax.inject.Inject


class PreferenceImp @Inject constructor(private val sharedPreferences: SharedPreferences): PreferenceInt {

    override suspend fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override suspend  fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    override suspend fun saveInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override suspend fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue) ?: defaultValue
    }

    override suspend fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
    }

}