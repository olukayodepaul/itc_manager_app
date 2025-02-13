package its.dart.com.presentation.viewmodel.event


sealed class LoginUIEvent {
    class OnUsername(val username: String) : LoginUIEvent()
    class OnPassword(val password: String) : LoginUIEvent()
    class OnButtonState(val buttonState: Boolean): LoginUIEvent()
    data object OnLoginClick : LoginUIEvent()
    data class OnLoading(val isLoading: Boolean) : LoginUIEvent()
    class OnErrorMessage(val message: String) : LoginUIEvent()
}

sealed class ChatFilter(val id: Int, val label: String) {

    data object Monday : ChatFilter(1, "Monday")
    data object Tuesday : ChatFilter(2, "Tuesday")
    data object Wednesday : ChatFilter(3, "Wednesday")
    data object Thursday : ChatFilter(4, "Thursday")
    data object Friday : ChatFilter(5, "Friday")
    data object Saturday : ChatFilter(6, "Saturday")

    companion object {
        fun fromId(id: Int): ChatFilter? {
            return when (id) {
                1 -> Monday
                2 -> Tuesday
                3 -> Wednesday
                4 -> Thursday
                5 -> Friday
                6 -> Saturday
                else -> null
            }
        }
    }
}