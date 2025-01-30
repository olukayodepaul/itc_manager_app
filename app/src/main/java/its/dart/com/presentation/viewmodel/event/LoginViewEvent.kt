package its.dart.com.presentation.viewmodel.event

import its.dart.com.domain.repository.remote.model.LoginModel

//incoming data
//sealed class LoginState {
//    data object Idle : LoginState()
//    data object Loading : LoginState()
//    data class Success(val loginModel: LoginModel) : LoginState()
//    data class Error(val message: String) : LoginState()
//}


sealed class LoginUIEvent {
    class OnUsername(val username: String) : LoginUIEvent()
    class OnPassword(val password: String) : LoginUIEvent()
    class OnButtonState(val buttonState: Boolean): LoginUIEvent()
    data object OnLoginClick : LoginUIEvent()
    data class OnLoading(val isLoading: Boolean) : LoginUIEvent()
    class OnErrorMessage(val message: String) : LoginUIEvent()
}


