package its.dart.com.presentation.viewmodel.state

import its.dart.com.domain.repository.remote.model.LoginModel


sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(val loginModel: LoginModel) : LoginState()
    data class Error(val message: String) : LoginState()
}