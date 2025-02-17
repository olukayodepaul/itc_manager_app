package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.domain.preference.PreferenceInt
import its.dart.com.domain.repository.remote.model.RepsModel
import its.dart.com.domain.usecases.RepUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SalesRepViewModel @Inject constructor(
    private val repUseCase: RepUseCases,
    private val sharePreference: PreferenceInt
): ViewModel() {

    private val _salesReps = MutableStateFlow<List<RepsModel>>(emptyList())
    val salesReps: StateFlow<List<RepsModel>> = _salesReps.asStateFlow()

    private var _userNameState = MutableStateFlow("")
    val userNameState: StateFlow<String> = _userNameState.asStateFlow()

    init{
        fetchSalesReps()
    }

    private fun fetchSalesReps() {
        viewModelScope.launch {
            userNameState.collect {
                _userNameState.value = sharePreference.getString(key= "username", defaultValue = "")
            }
        }
        viewModelScope.launch {
            repUseCase.fetchSalesReps().collect { repsList ->
                _salesReps.value = repsList
            }
        }
    }
}