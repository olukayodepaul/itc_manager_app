package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.domain.repository.remote.model.AllCustomersModel
import its.dart.com.domain.usecases.CustomerByRepUseCases
import its.dart.com.presentation.viewmodel.state.GenericState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerByRepViewModel @Inject constructor(private val cloud :  CustomerByRepUseCases) : ViewModel() {

    private val _customersState = MutableStateFlow<GenericState<List<AllCustomersModel>>>(GenericState.Loading)
    val customersState: StateFlow<GenericState<List<AllCustomersModel>>> = _customersState.asStateFlow()

    suspend fun getCustomers(userId: String) {
        viewModelScope.launch() {
            _customersState.value = GenericState.Loading
            try {
                val result = cloud.invokeCustomer(userId)
                result.onSuccess { customersModel ->
                    _customersState.value = GenericState.Success(customersModel.data)
                }.onFailure { exception ->
                    _customersState.value = GenericState.Failure(exception)
                }
            } catch (e: Exception) {
                _customersState.value = GenericState.Failure(e)
            }
        }
    }

}

//@HiltViewModel
//class CustomerByRepViewModel @Inject constructor(private val cloud: CustomerByRepUseCases) : ViewModel() {
//
//    private val _customersState = MutableStateFlow<CustomerRepState>(CustomerRepState.Loading)
//    val customersState: StateFlow<CustomerRepState> = _customersState.asStateFlow()
//
//    private val _customersList = mutableStateListOf<AllCustomersModel>()
//    val customersList: List<AllCustomersModel> get() = _customersList
//
//    private var currentPage = 1
//    private val pageSize = 10
//    private var isLoading = false
//    private var hasMoreData = true
//
//    fun getCustomers(userId: String) {
//        if (isLoading || !hasMoreData) return
//
//        isLoading = true
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val result = cloud.invokeCustomer(userId, currentPage, pageSize)
//                result.onSuccess { customersModel ->
//                    if (customersModel.data.isNotEmpty()) {
//                        _customersList.addAll(customersModel.data)
//                        currentPage++
//                    } else {
//                        hasMoreData = false
//                    }
//                    _customersState.value = CustomerRepState.Success(_customersList)
//                }.onFailure { exception ->
//                    _customersState.value = CustomerRepState.Failure(exception)
//                }
//            } catch (e: Exception) {
//                _customersState.value = CustomerRepState.Failure(e)
//            } finally {
//                isLoading = false
//            }
//        }
//    }
//}



//@HiltViewModel
//class CustomerByRepViewModel @Inject constructor(private val cloud :  CustomerByRepUseCases) : ViewModel() {
//
//    private val _customersState = MutableStateFlow<CustomerRepState>(CustomerRepState.Loading)
//    val customersState: StateFlow<CustomerRepState> = _customersState.asStateFlow()
//
//    suspend fun getCustomers(userId: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            _customersState.value = CustomerRepState.Loading
//            try {
//                val result = cloud.invokeCustomer(userId)
//                result.onSuccess { customersModel ->
//                    _customersState.value = CustomerRepState.Success(customersModel.data)
//                }.onFailure { exception ->
//                    _customersState.value = CustomerRepState.Failure(exception)
//                }
//            } catch (e: Exception) {
//                _customersState.value = CustomerRepState.Failure(e)
//            }
//        }
//    }
//
//}



