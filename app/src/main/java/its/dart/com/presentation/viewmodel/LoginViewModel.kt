package its.dart.com.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.data.repository.local.database.LocalDatabase
import its.dart.com.domain.usecases.LoginUseCases
import its.dart.com.mapper.toEntityList
import its.dart.com.mapper.toSalesRepsList
import its.dart.com.presentation.viewmodel.event.LoginViewEvent
import its.dart.com.presentation.viewmodel.state.LoginViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCases,
    private val localCache: LocalDatabase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginViewState())
    val uiState: StateFlow<LoginViewState> = _uiState

    private val _navController = MutableStateFlow(false)
    val navController: StateFlow<Boolean> = _navController


    fun onEvent(event: LoginViewEvent) {
        viewModelScope.launch {
            eventHandler(event)
        }
    }

    private suspend  fun navigateToHomeScreen() {
        _navController.emit(true)
        //_navController.value = true
    }

    suspend fun resetNavigation() {
        _navController.emit(false)
        //_navController.value = false
    }

    private fun eventHandler(event: LoginViewEvent) = viewModelScope.launch{
        when(event){
            is LoginViewEvent.OnUsernameTextBox->{usernameTextBox(event.usernameTextBox)}
            is LoginViewEvent.OnPasswordTextBox->{passwordTextBox(event.passwordTextBox)}
            is LoginViewEvent.OnClickButton->onLoginClick()
        }
    }

    private fun usernameTextBox(usernameTextBox: String) {
        _uiState.value = _uiState.value.copy(usernameTextBox = usernameTextBox)
    }

    private fun passwordTextBox(passwordTextBox: String ) {
        _uiState.value = _uiState.value.copy(passwordTextBox = passwordTextBox)
    }

    private suspend fun onLoginClick() {

        if (uiState.value.usernameTextBox.isBlank() || uiState.value.passwordTextBox.isBlank()) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "Username and password cannot be empty"
            )
            return
        }

        _uiState.value = _uiState.value.copy(dialogLoader = true)
        val result = loginUseCase.invokeLogin(uiState.value.usernameTextBox, uiState.value.passwordTextBox)

        result.onSuccess {
            if (it.status == 200) {
                localCache.persistLogin(it.data.rep.toSalesRepsList())
                localCache.persistProduct(it.products.toEntityList())
                _uiState.value = _uiState.value.copy(
                    errorMessage = "",
                    dialogLoader = false
                )
                navigateToHomeScreen()
            } else {
                println(it.error.errorMessage)
                _uiState.value = _uiState.value.copy(
                    dialogLoader = false,
                    errorMessage = it.error.errorMessage
                )
            }
        }.onFailure { error ->
            println(error.message ?: "An error occurred. Please try again.")
            _uiState.value = _uiState.value.copy(
                dialogLoader = false,
                errorMessage = error.message ?: "An error occurred. Please try again."
            )
        }
    }
}
