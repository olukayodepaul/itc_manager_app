package its.dart.com.data.repository.prefence

import android.content.SharedPreferences
import its.dart.com.domain.repository.PreferenceInt
import javax.inject.Inject


class PreferenceImp @Inject constructor(private val sharedPreferences: SharedPreferences): PreferenceInt {

    override fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    override fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
    }

}