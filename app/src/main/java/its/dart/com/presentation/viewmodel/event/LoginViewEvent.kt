package its.dart.com.presentation.viewmodel.event

import its.dart.com.domain.repository.remote.model.LoginModel

//incoming data
//sealed class LoginState {
//    data object Idle : LoginState()
//    data object Loading : LoginState()
//    data class Success(val loginModel: LoginModel) : LoginState()
//    data class Error(val message: String) : LoginState()
//}


data class LoginUIState(
    val username: String = "",
    val password: String = "",
    val buttonState: Boolean = true,
    val isLoading: Boolean = false,
)

sealed class LoginUIEvent {
    class OnUsername(val username: String) : LoginUIEvent()
    class OnPassword(val password: String) : LoginUIEvent()
    class OnButtonState(val buttonState: Boolean): LoginUIEvent()
    data object OnLoginClick : LoginUIEvent()
    data class OnLoading(val isLoading: Boolean) : LoginUIEvent()
}





