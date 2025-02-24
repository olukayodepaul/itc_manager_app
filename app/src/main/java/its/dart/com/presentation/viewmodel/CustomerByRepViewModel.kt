package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.data.repository.local.database.LocalDatabase
import its.dart.com.data.repository.local.entity.AllCustomersEntity
import its.dart.com.domain.repository.remote.model.AllCustomersModel
import its.dart.com.domain.usecases.CustomerByRepUseCases
import its.dart.com.mapper.toAllCustomersEntity
import its.dart.com.mapper.toAllCustomersModel
import its.dart.com.mapper.toAllCustomersModelList
import its.dart.com.mapper.toCustomersModel
import its.dart.com.presentation.viewmodel.state.GenericState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject


@HiltViewModel
class CustomerByRepViewModel @Inject constructor(
    private val cloud: CustomerByRepUseCases,
    private val local: LocalDatabase
) : ViewModel() {

    private val _customersState = MutableStateFlow<GenericState<List<AllCustomersModel>>>(GenericState.Loading)
    val customersState: StateFlow<GenericState<List<AllCustomersModel>>> = _customersState.asStateFlow()

    private val _optionState = MutableStateFlow(0)
    val optionState: StateFlow<Int> = _optionState

    private val _searchItems = MutableStateFlow<List<AllCustomersModel>>(emptyList())
    val searchItems:StateFlow<List<AllCustomersModel>> = _searchItems.asStateFlow()

    private val _search = MutableStateFlow("")
    val search: StateFlow<String> = _search.asStateFlow()

    fun getCurrentOptionState() {
        _optionState.value = getDayAsNumber()
    }

    fun updateOptionState(newState: Int, userId: Int) {
        _optionState.value = newState
        invokeBridgeCall(userId, newState)
    }

    fun getCustomers(repId: Int, option: Int) {
        invokeBridgeCall(repId, option)
    }

    private fun invokeBridgeCall(userId: Int, option: Int) {
        viewModelScope.launch {
            _search.value = ""
            try {
                _customersState.value = GenericState.Loading

                val customerCount = local.getCustomer(option, userId).first()

                if (customerCount == 0) {
                    val daysOfTheWeek = option - getDayAsNumber()
                    val result = cloud.invokeCustomer(userId, daysOfTheWeek, option)
                    result.onSuccess { customersModel ->
                        local.persistCustomer(customersModel.data.toAllCustomersEntity())
                    }.onFailure { exception ->
                        _customersState.value = GenericState.Failure(exception)
                        return@launch
                    }
                }
                local.getCustomers(option, userId).collectLatest { customerEntities ->
                    _customersState.value = GenericState.Success(customerEntities.toAllCustomersModel().toList())
                }
            }catch (e: Exception){
                _customersState.value = GenericState.Failure(e)
            }
        }
    }

    fun searchEvent(search: String, repId: Int, setToDefault: Int) {
        _search.value = search
        viewModelScope.launch {

            if(setToDefault == 2){
                local.getCustomers(_optionState.value , repId).collectLatest { customerEntities ->
                    _customersState.value = GenericState.Success(
                        customerEntities.toAllCustomersModel().toList()
                    )
                }
            }else{
                if (search.isNotEmpty()) {
                    _customersState.value = GenericState.Success(
                        local.searchCustomers(search, _optionState.value, repId).toAllCustomersModel().toList()
                    )
                } else {
                    local.getCustomers(_optionState.value , repId).collectLatest { customerEntities ->
                        _customersState.value = GenericState.Success(
                            customerEntities.toAllCustomersModel().toList()
                        )
                    }
                }
            }

        }
    }

    fun searchEmptySearch(repId: Int) {
        _search.value = ""
        viewModelScope.launch {
            local.getCustomers(_optionState.value, repId).collectLatest { customerEntities ->
                _customersState.value = GenericState.Success(
                    customerEntities.toAllCustomersModel().toList()
                )
            }
        }
    }

    private fun getDayAsNumber(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.DAY_OF_WEEK) - 1
    }
}
