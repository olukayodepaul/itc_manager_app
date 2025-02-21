package its.dart.com.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.data.repository.local.database.LocalDatabase
import its.dart.com.domain.repository.remote.model.AllCustomersModel
import its.dart.com.domain.usecases.CustomerByRepUseCases
import its.dart.com.mapper.toAllCustomersEntity
import its.dart.com.mapper.toAllCustomersModel
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

    private fun getDayAsNumber(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.DAY_OF_WEEK) - 1
    }
}
