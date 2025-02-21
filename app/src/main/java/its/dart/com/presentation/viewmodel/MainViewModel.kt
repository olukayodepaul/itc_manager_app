package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.domain.preference.PreferenceInt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val sharePreference:  PreferenceInt): ViewModel(){

    private val _userState = MutableStateFlow(0)
    val userState: StateFlow<Int> = _userState.asStateFlow()

    suspend fun updateUserId(){
        val copyId = sharePreference.getInt(key= "sysCategory", defaultValue = 0)
        _userState.value = copyId
    }
}