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
    data object OnclickDismissButton: AddCustomerViewEvent()
    data object OnclickConfirmButton: AddCustomerViewEvent()
    data object OnErrorClear: AddCustomerViewEvent()
}

data class OptionData(
    val day: String,
    val id: Int
)

fun getPromoterSupervisor(): List<OptionData> {
    return listOf(
        OptionData("Monday", 1),
        OptionData("Tuesday", 2),
        OptionData("Wednesday", 3),
        OptionData("Thursday", 4),
        OptionData("Friday", 5),
        OptionData("Saturday", 6)
    )
}



