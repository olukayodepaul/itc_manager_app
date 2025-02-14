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
