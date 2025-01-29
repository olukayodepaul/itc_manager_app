package its.dart.com.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.data.repository.local.configuration.RoomDatabaseTable
import its.dart.com.domain.usecases.LoginUseCases
import its.dart.com.mapper.toSalesRepsList
import its.dart.com.presentation.viewmodel.event.LoginUIEvent
import its.dart.com.presentation.viewmodel.event.LoginUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCases,
    private val localCache: RoomDatabaseTable
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState

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
            is LoginUIEvent.OnLoginClick -> onLoginClick()
            is LoginUIEvent.OnLoading -> onLoading(event.isLoading)
            is LoginUIEvent.OnErrorMessage -> isErrorMessage(event.message)
        }
    }

    private fun navigateToHomeScreen() {
        _navigateToHome.value = true
    }

    fun resetNavigation() {
        _navigateToHome.value = false
    }

    private fun isErrorMessage(message: String) {
        _uiState.value = _uiState.value.copy(isErrorMessage = message)
    }

    private fun onLoading(isLoading: Boolean) {
        _uiState.value = _uiState.value.copy(isLoading = isLoading)
    }

    private fun onButtonState(buttonState: Boolean) {
        _uiState.value = _uiState.value.copy(buttonState = buttonState)
    }

    private fun onPassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    private fun onUsername(username: String) {
        _uiState.value = _uiState.value.copy(username = username)
    }

    private suspend fun onLoginClick() {
        if (uiState.value.username.isBlank() || uiState.value.password.isBlank()) {
            _uiState.value = _uiState.value.copy(
                isErrorMessage = "Username and password cannot be empty"
            )
            return
        }

        _uiState.value = _uiState.value.copy(isLoading = true, buttonState = false)
        val result = loginUseCase.invokeLogin(uiState.value.username, uiState.value.password)

        result.onSuccess {
            if (it.status == 200) {
                localCache.insertLogins(it.data.rep.toSalesRepsList())
                navigateToHomeScreen()
            } else {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    buttonState = true
                )
            }
        }.onFailure { error ->
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                buttonState = true,
                isErrorMessage = error.message ?: "An error occurred. Please try again."
            )
        }
    }
}
