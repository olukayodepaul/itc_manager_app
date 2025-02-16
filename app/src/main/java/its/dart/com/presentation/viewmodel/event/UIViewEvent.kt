package its.dart.com.presentation.viewmodel.event


sealed class LoginViewEvent {
    class OnUsernameTextBox(val usernameTextBox: String) : LoginViewEvent()
    class OnPasswordTextBox(val passwordTextBox: String) : LoginViewEvent()
    data object OnClickButton: LoginViewEvent()
}

sealed class TaskViewEvent {
    data object OnClickResume: TaskViewEvent()
    data object OnClickClockOut: TaskViewEvent()
    data object OnClickClockIn: TaskViewEvent()
    data object OnClickClose: TaskViewEvent()
}

sealed class AddCustomerViewEvent{
    class OnOutletName(val outletName: String): AddCustomerViewEvent()
    class OnContactPerson(val contactPerson: String): AddCustomerViewEvent()
    class OnMobileNumber(val mobileNumber: String): AddCustomerViewEvent()
    class OnLanguage(val language: Int): AddCustomerViewEvent()
    class OnOutletType(val outletType: Int): AddCustomerViewEvent()
    class OnAddress(val address: String) : AddCustomerViewEvent()
    data object OnclickButton: AddCustomerViewEvent()
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



