package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import its.dart.com.presentation.viewmodel.event.AddCustomerViewEvent
import its.dart.com.presentation.viewmodel.state.AddCustomerViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddCustomerViewModel : ViewModel() {

    private var _addCustomerState = MutableStateFlow(AddCustomerViewState())
    val addCustomerState: StateFlow<AddCustomerViewState> = _addCustomerState

    private fun updateState(update: AddCustomerViewState.() -> AddCustomerViewState) {
        _addCustomerState.value = _addCustomerState.value.update()
    }

    private fun outletName(outletName: String) = updateState { copy(outletName = outletName) }
    private fun contactPerson(contactPerson: String) = updateState { copy(contactPerson = contactPerson) }
    private fun mobileNumber(mobileNumber: String) = updateState { copy(mobileNumber = mobileNumber) }
    private fun language(language: Int) = updateState {copy(language = language)}
    private fun outletType(outletType: Int) = updateState {copy(outletType = outletType)}
    private fun address(address: String) = updateState { copy(address = address) }
    private fun onClickButton() {

    }

    fun onEvent(event: AddCustomerViewEvent) {
        eventListener(event)
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
        }
    }
}
