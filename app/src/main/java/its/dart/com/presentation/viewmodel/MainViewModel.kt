package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.domain.preference.PreferenceInt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val sharePreference:  PreferenceInt): ViewModel(){

    private val _userState = MutableStateFlow(0)
    val userState: StateFlow<Int> = _userState.asStateFlow()

    init {
        viewModelScope.launch {
            _userState.value = sharePreference.getInt(key= "sysCategory", defaultValue = 0)
        }
    }

}