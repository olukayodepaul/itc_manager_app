package its.dart.com.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.data.repository.local.database.LocalDatabase
import its.dart.com.data.repository.local.entity.MerchantEntity
import its.dart.com.data.repository.local.entity.PromoterEntity
import its.dart.com.data.repository.remote.dto.MerchantDTO
import its.dart.com.domain.preference.PreferenceInt
import its.dart.com.domain.usecases.LoginUseCases
import its.dart.com.mapper.toEntityList
import its.dart.com.mapper.toSalesRepsList
import its.dart.com.presentation.viewmodel.event.LoginViewEvent
import its.dart.com.presentation.viewmodel.state.LoginViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCases,
    private val localCache: LocalDatabase,
    private val sharePreference: PreferenceInt
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginViewState())
    val uiState: StateFlow<LoginViewState> = _uiState

    private val _navController = MutableStateFlow(false)
    val navController: StateFlow<Boolean> = _navController

    fun onEvent(event: LoginViewEvent) {
        viewModelScope.launch {
            eventHandler(event)
        }
    }

    private suspend  fun navigateToHomeScreen() {
        _navController.emit(true)
    }

    suspend fun resetNavigation() {
        _navController.emit(false)
    }

    private fun eventHandler(event: LoginViewEvent) {
        when(event){
            is LoginViewEvent.OnUsernameTextBox->{usernameTextBox(event.usernameTextBox)}
            is LoginViewEvent.OnPasswordTextBox->{passwordTextBox(event.passwordTextBox)}
            is LoginViewEvent.OnClickButton->{viewModelScope.launch{onLoginClick()}}
        }
    }

    private fun usernameTextBox(usernameTextBox: String) {
        _uiState.value = _uiState.value.copy(usernameTextBox = usernameTextBox)
    }

    private fun passwordTextBox(passwordTextBox: String ) {
        _uiState.value = _uiState.value.copy(passwordTextBox = passwordTextBox)
    }

    private suspend fun onLoginClick() {

        val state = _uiState.value

        if (state.usernameTextBox.isBlank() || state.passwordTextBox.isBlank()) {
            _uiState.value = state.copy(
                errorMessage = "Username and password cannot be empty"
            )
            return
        }

        _uiState.value = state.copy(dialogLoader = true)
        val result = loginUseCase.invokeLogin(state.usernameTextBox, state.passwordTextBox)

        result.onSuccess {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val currentDate = sdf.format(Date())

            val data = sharePreference.getString(key= "currentDate", defaultValue = "0000-00-00")
            val userId = sharePreference.getInt(key= "id", defaultValue = 0)
            val systemCategory = sharePreference.getInt(key= "sysCategory", defaultValue = 0)

            if(data == currentDate && userId == it.data.users.id) {
                navigateToHomeScreen()
                return
            }

            if (it.status == 200) {
                //delete first
                localCache.deleteAllCustomers()
                localCache.deleteAllProduct()
                localCache.deleteAllTask()
                localCache.deleteAllPromoter()
                localCache.deleteAllLogins()
                localCache.deleteAllMerchant()

                localCache.persistLogin(it.data.rep.toSalesRepsList())
                localCache.persistProduct(it.products.toEntityList())
                sharePreference.saveString(key= "username", value = it.data.users.fullName)
                sharePreference.saveInt(key="sysCategory", value = it.data.users.systemCategoryId)
                sharePreference.saveString(key="currentDate", value = currentDate)
                sharePreference.saveInt(key="id", value= it.data.users.id)
                sharePreference.saveString(key = "lat" , value = it.data.users.depotLat)
                sharePreference.saveString(key = "lng" , value = it.data.users.depotLng)

                val pullRequest =  loginUseCase.invokeGetMerchantAndPromoterCustomers(it.data.users.systemCategoryId.toString(), it.data.users.id.toString())
                if(it.data.users.systemCategoryId==5) {

                    val merchantEntities = pullRequest.map { dto ->
                        MerchantEntity(
                            id = dto.urno,
                            urno = dto.urno,
                            latitude = dto.latitude.ifEmpty { "0.0" },
                            longitude = dto.longitude.ifEmpty { "0.0" },
                            outlet_name = dto.outlet_name,
                            outlet_address = dto.outlet_address,
                            contact_phone = dto.contact_phone,
                            outlet_type = dto.outlet_type
                        )
                    }
                    localCache.persistAllMerchants(merchantEntities)
                }

                if(it.data.users.systemCategoryId==6) {
                    val merchantEntities = pullRequest.map { dto ->
                        PromoterEntity(
                            id = dto.urno,
                            urno = dto.urno,
                            latitude = dto.latitude.ifEmpty { "0.0" },
                            longitude = dto.longitude.ifEmpty { "0.0" },
                            outlet_name = dto.outlet_name,
                            outlet_address = dto.outlet_address,
                            contact_phone = dto.contact_phone,
                            outlet_type = dto.outlet_type
                        )
                    }
                    localCache.persistAllPromoter(merchantEntities)
                }

                _uiState.value = state.copy(
                    errorMessage = "",
                    dialogLoader = false
                )
                navigateToHomeScreen()
            } else {
                println(it.error.errorMessage)
                _uiState.value = state.copy(
                    dialogLoader = false,
                    errorMessage = it.error.errorMessage
                )
            }
        }.onFailure { error ->
            println(error.message ?: "An error occurred. Please try again.")
            _uiState.value = state.copy(
                dialogLoader = false,
                errorMessage = error.message ?: "An error occurred. Please try again."
            )
        }
    }
}
