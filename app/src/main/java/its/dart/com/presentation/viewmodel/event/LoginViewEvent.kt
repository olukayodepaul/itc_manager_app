package its.dart.com.presentation.viewmodel.event


sealed class LoginUIEvent {
    class OnUsername(val username: String) : LoginUIEvent()
    class OnPassword(val password: String) : LoginUIEvent()
    class OnButtonState(val buttonState: Boolean): LoginUIEvent()
    data object OnLoginClick : LoginUIEvent()
    data class OnLoading(val isLoading: Boolean) : LoginUIEvent()
    class OnErrorMessage(val message: String) : LoginUIEvent()
}