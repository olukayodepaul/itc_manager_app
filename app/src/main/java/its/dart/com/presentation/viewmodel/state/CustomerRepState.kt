package its.dart.com.presentation.viewmodel.state


sealed class GenericState<out T> {
    data object Loading : GenericState<Nothing>()
    data class Success<out T>(val data: T) : GenericState<T>()
    data class Failure(val exception: Throwable) : GenericState<Nothing>()
}

data class LoginUIState(
    val username: String = "",
    val password: String = "",
    val buttonState: Boolean = true,
    val isLoading: Boolean = false,
    val isErrorMessage: String = "",
)


