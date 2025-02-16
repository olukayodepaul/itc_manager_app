package its.dart.com.presentation.viewmodel.state


//generic state
sealed class GenericState<out T> {
    data object Loading : GenericState<Nothing>()
    data class Success<out T>(val data: T) : GenericState<T>()
    data class Failure(val exception: Throwable) : GenericState<Nothing>()
}

data class LoginViewState(
    val usernameTextBox: String = "",
    val passwordTextBox: String = "",
    val errorMessage: String = "",
    val dialogLoader: Boolean  = false
)

data class TaskViewState(
    val resume: String = "00:00:00",
    val clockOut: String = "00:00:00",
    val clockIn: String = "00:00:00",
    val close : String = "00:00:00",
    val dialogLoader: Boolean  = false
)

data class AddCustomerViewState(
    val outletName: String = "",
    val contactPerson: String = "",
    val mobileNumber: String = "",
    val language: Int = 0,
    val outletType: Int = 0,
    val address: String = "",
    val errorMessage: String = "",
    val dialogLoader: Boolean  = false,
    val loader: Boolean = false
)



