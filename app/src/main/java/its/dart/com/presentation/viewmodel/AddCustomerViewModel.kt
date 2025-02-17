package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.data.repository.local.entity.OtherAllCustomersEntity
import its.dart.com.domain.preference.PreferenceInt
import its.dart.com.domain.repository.local.OtherCustomer
import its.dart.com.domain.repository.remote.AddCustomer
import its.dart.com.domain.repository.remote.model.AddCustomerReqModel
import its.dart.com.presentation.viewmodel.event.AddCustomerViewEvent
import its.dart.com.presentation.viewmodel.state.AddCustomerViewState
import its.dart.com.presentation.viewmodel.state.GenericState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCustomerViewModel @Inject constructor(
    private val remoteRepo: AddCustomer,
    private val localRepo: OtherCustomer,
    private val sharePreference: PreferenceInt
): ViewModel( ) {

    private var _addCustomerState = MutableStateFlow(AddCustomerViewState())
    val addCustomerState: StateFlow<AddCustomerViewState> = _addCustomerState.asStateFlow()

    private var _customersState = MutableStateFlow<List<OtherAllCustomersEntity>>(emptyList())
    val customersState: StateFlow<List<OtherAllCustomersEntity>> = _customersState.asStateFlow()

    private var _userNameState = MutableStateFlow("")
    val userNameState: StateFlow<String> = _userNameState.asStateFlow()

    private fun updateState(update: AddCustomerViewState.() -> AddCustomerViewState) {
        _addCustomerState.value = _addCustomerState.value.update()
    }

    private fun outletName(outletName: String) = updateState { copy(outletName = outletName) }
    private fun contactPerson(contactPerson: String) = updateState { copy(contactPerson = contactPerson) }
    private fun mobileNumber(mobileNumber: String) = updateState { copy(mobileNumber = mobileNumber) }
    private fun language(language: Int) = updateState { copy(language = language) }
    private fun outletType(outletType: Int) = updateState { copy(outletType = outletType) }
    private fun address(address: String) = updateState { copy(address = address) }
    private fun onClickDismissButton() = updateState { copy(dialogLoader = false) }
    private fun onClickConfirmButton() {

        val state = _addCustomerState.value

        if (state.outletName.isBlank() ||
            state.contactPerson.isBlank() ||
            state.mobileNumber.isBlank() ||
            state.language == 0 ||
            state.outletType == 0 ||
            state.address.isBlank()
        ) {
            updateState { copy(errorMessage = "All fields are required!") }
            return
        }
        updateState { copy(errorMessage = "", dialogLoader = true) }
    }

    private fun onClickButton() {
        viewModelScope.launch {

            updateState { copy(dialogLoader = false, loader = true ) }

            val state = _addCustomerState.value

            val post = AddCustomerReqModel.builder()
                .outletName(state.outletName)
                .contactPension(state.contactPerson)
                .phoneNumber(state.mobileNumber)
                .languageId(state.language)
                .outletTypeId(state.outletType)
                .outletClassId(0)
                .address(state.address)
                .lat(0.1)
                .lng(0.2)
                .userId(sharePreference.getInt(key="id", defaultValue = 0))
                .userType(sharePreference.getInt(key="sysCategory", defaultValue = 0).toString())
                .build()

            val fetchCloudData = remoteRepo.postCustomer(post)

            if(fetchCloudData.status==200){
                val cache = OtherAllCustomersEntity(
                    id  = fetchCloudData.id,
                    urno = fetchCloudData.id,
                    latitude = "0.1",
                    longitude = "0.3",
                    outlet_name = state.outletName,
                    outlet_address = state.address,
                    contact_phone = state.mobileNumber,
                    outlet_type = state.outletType.toString()
                )
                localRepo.persistOtherCustomers(cache)
                updateState { copy(loader = false , success = true) }
                return@launch
            }

            updateState {copy(dialogLoader = false, loader = false, errorMessage = fetchCloudData.message ) }
        }
    }

    fun onEvent(event: AddCustomerViewEvent) {
        viewModelScope.launch {
            eventListener(event)
        }
    }

    private fun eventListener(event: AddCustomerViewEvent) {
        when (event) {
            is AddCustomerViewEvent.OnOutletName -> outletName(event.outletName)
            is AddCustomerViewEvent.OnContactPerson -> contactPerson(event.contactPerson)
            is AddCustomerViewEvent.OnMobileNumber -> mobileNumber(event.mobileNumber)
            is AddCustomerViewEvent.OnLanguage -> language(event.language)
            is AddCustomerViewEvent.OnOutletType -> outletType(event.outletType)
            is AddCustomerViewEvent.OnAddress -> address(event.address)
            is AddCustomerViewEvent.OnclickButton -> onClickButton()
            is AddCustomerViewEvent.OnclickDismissButton -> onClickDismissButton()
            is AddCustomerViewEvent.OnclickConfirmButton -> onClickConfirmButton()
            is AddCustomerViewEvent.OnErrorClear->{ updateState { copy(errorMessage = "")}}
        }
    }

    fun fetchPromoterCustomers() {
        viewModelScope.launch {
            localRepo.getOtherCustomers().collect{result->
                _customersState.value = result
            }
        }
    }

    init{
        viewModelScope.launch {
            userNameState.collect {
                _userNameState.value = sharePreference.getString(key= "username", defaultValue = "")
            }
        }
    }

}
