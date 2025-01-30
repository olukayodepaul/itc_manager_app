package its.dart.com.presentation.viewmodel.state

import its.dart.com.domain.repository.remote.model.AllCustomersModel

sealed class CustomerRepState {
    data object Loading : CustomerRepState()
    data object Empty : CustomerRepState()  // No customers found
    data class Success(val data: List<AllCustomersModel>) : CustomerRepState()
    data class Failure(val exception: Throwable) : CustomerRepState()
}

data class LoginUIState(
    val username: String = "",
    val password: String = "",
    val buttonState: Boolean = true,
    val isLoading: Boolean = false,
    val isErrorMessage: String = "",
)


