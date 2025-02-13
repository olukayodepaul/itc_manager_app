package its.dart.com.presentation.viewmodel.state


//generic state
sealed class GenericState<out T> {
    data object Loading : GenericState<Nothing>()
    data class Success<out T>(val data: T) : GenericState<T>()
    data class Failure(val exception: Throwable) : GenericState<Nothing>()
}

data class LoginUIState(
    val usernameState: String = "",
    val passwordState: String = "",
    val buttonState: Boolean = true,
    val isLoadingState: Boolean = false,
    val isErrorMessageState: String = "",
    val dialogLoaderState: Boolean  = false
)


