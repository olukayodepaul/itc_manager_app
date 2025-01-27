package its.dart.com.presentation.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.domain.usecases.LoginUseCases
import its.dart.com.presentation.viewmodel.event.LoginUIEvent
import its.dart.com.presentation.viewmodel.event.LoginUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCases
) : ViewModel() {

    var uiState = MutableStateFlow(LoginUIState())


    fun eventHandler(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.OnButtonState -> onButtonState(event.buttonState)
            is LoginUIEvent.OnPassword -> onPassword(event.password)
            is LoginUIEvent.OnUsername -> onUsername(event.username)
            is LoginUIEvent.OnLoading -> onLoading(event.isLoading)
            is LoginUIEvent.OnLoginClick -> onLoginClick()
        }
    }

    private fun onLoading(isLoading: Boolean) {
        uiState.value = uiState.value.copy(isLoading = isLoading)
    }

    private fun onButtonState(buttonState: Boolean) {
        uiState.value = uiState.value.copy(buttonState = buttonState)
    }

    private fun onPassword(password: String) {
        uiState.value = uiState.value.copy(password = password)
    }

    private fun onUsername(username: String) {
        uiState.value = uiState.value.copy(username = username)
    }

    private fun onLoginClick() {
        uiState.value = uiState.value.copy(
            isLoading = true,
            buttonState = false
        )
    }

    init{
        viewModelScope.launch {

        }
    }

}