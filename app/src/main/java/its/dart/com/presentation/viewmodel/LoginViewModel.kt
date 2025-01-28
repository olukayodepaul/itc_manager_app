package its.dart.com.presentation.viewmodel



import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.domain.usecases.LoginUseCases
import its.dart.com.presentation.viewmodel.event.LoginUIEvent
import its.dart.com.presentation.viewmodel.event.LoginUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCases
) : ViewModel() {

    var uiState = MutableStateFlow(LoginUIState())
    private val _navigateToHome = MutableStateFlow(false)
    val navigateToHome: StateFlow<Boolean> = _navigateToHome

    fun onEvent(event: LoginUIEvent) {
        viewModelScope.launch {
            eventHandler(event)
        }
    }

    private suspend fun eventHandler(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.OnUsername -> onUsername(event.username)
            is LoginUIEvent.OnPassword -> onPassword(event.password)
            is LoginUIEvent.OnButtonState -> onButtonState(event.buttonState)
            is LoginUIEvent.OnLoginClick->onLoginClick()
            is LoginUIEvent.OnLoading -> onLoading(event.isLoading)
            is LoginUIEvent.OnErrorMessage->isErrorMessage(event.message)
        }
    }

    private fun navigateToHomeScreen() {
        _navigateToHome.value = true
    }

    fun resetNavigation() {
        _navigateToHome.value = false
    }

    private fun isErrorMessage(message: String) {
        uiState.value = uiState.value.copy(isErrorMessage = message)
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

    private suspend fun onLoginClick() {

        if (uiState.value.username.isBlank() || uiState.value.password.isBlank()) {
            uiState.value = uiState.value.copy(
                isErrorMessage = "Username and password cannot be empty"
            )
            return
        }

//        uiState.value = uiState.value.copy(isLoading = true, buttonState = false)

        val result = loginUseCase.invokeLogin(uiState.value.username, uiState.value.password)
        Log.d("epokhai", result.toString())

        result.onSuccess {
            if (it.status == 200) {
                //just navigate to the next Screen
                navigateToHomeScreen()
            } else {
                uiState.value = uiState.value.copy(
                    isLoading = false,
                    buttonState = true,
                    isErrorMessage = it.error.description ?: "Login failed. Please try again."
                )
            }

        }.onFailure { error ->
            uiState.value = uiState.value.copy(
                isLoading = false,
                buttonState = true,
                isErrorMessage = error.message.toString()
            )
            Log.d("epokhai", result.toString())
        }
    }

    init{
        viewModelScope.launch {

        }
    }
}