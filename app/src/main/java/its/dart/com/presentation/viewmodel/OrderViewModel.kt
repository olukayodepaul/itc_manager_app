package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.data.repository.remote.dto.OrderBodyDTO
import its.dart.com.data.repository.remote.dto.OrderDTO
import its.dart.com.domain.preference.PreferenceInt
import its.dart.com.domain.repository.remote.OrderInt
import its.dart.com.domain.repository.remote.model.ProductModel
import its.dart.com.domain.usecases.OrderUseCases
import its.dart.com.presentation.viewmodel.state.GenericState
import its.dart.com.presentation.viewmodel.state.OrderState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel  @Inject constructor(
    private val orderUserCase: OrderUseCases,
    private val remoteRepository: OrderInt,
    private val sharePreference: PreferenceInt
) : ViewModel() {

    private val _productState =
        MutableStateFlow<GenericState<List<ProductModel>>>(GenericState.Loading)
    val productState: StateFlow<GenericState<List<ProductModel>>> = _productState.asStateFlow()

    private val _order = MutableStateFlow(OrderState())
    val order: StateFlow<OrderState> = _order.asStateFlow()

    val totalQty: Int
        get() = (_productState.value as? GenericState.Success)?.data?.sumOf {
            it.qty?.toIntOrNull() ?: 0
        } ?: 0

    val totalInv: Int
        get() = (_productState.value as? GenericState.Success)?.data?.sumOf {
            it.inv?.toIntOrNull() ?: 0
        } ?: 0

    fun getProduct() {
        viewModelScope.launch {
            _productState.value = GenericState.Loading
            orderUserCase.fetchProduct()
                .catch { exception ->
                    _productState.value = GenericState.Failure(exception)
                }
                .collect { result ->
                    _productState.value = GenericState.Success(result)
                }
        }
    }

    fun getUiState(urno: Int, repId: Int) {
        var name: Array<Int> = arrayOf(1, 2, 3, 4, 5,);

        _order.value = _order.value.copy(urno = urno, repId = repId)
    }

    fun disMissSuccessfulDialog() {
        _order.value = _order.value.copy(success = false)
    }

    fun disMissBottomSheet(dismiss: Boolean) {
        _order.value = _order.value.copy(showAndHideErrorMessage = dismiss)
    }

    fun showConfirmDialog() {
        _order.value = _order.value.copy(confirmDialog = true)
    }

    fun updateProduct(productId: Int, newQty: String, specific: Int) {
        val currentState = _productState.value
        if (currentState is GenericState.Success) {
            val currentList = currentState.data.toMutableList()
            val index = currentList.indexOfFirst { it.id == productId }
            if (index != -1) {
                currentList[index] = when (specific) {
                    1 -> currentList[index].copy(qty = newQty)
                    2 -> currentList[index].copy(inv = newQty)
                    3 -> currentList[index].copy(uom = newQty)
                    else -> currentList[index]
                }
                _productState.value = GenericState.Success(currentList)
            }
        }
    }

    fun onDisMissConfirm() {
        _order.value = _order.value.copy(confirmDialog = false)
    }


    fun onSubmit() {
        viewModelScope.launch {

            _order.value = _order.value.copy(confirmDialog = false, loaders = true,)

            val currentState = _productState.value
            if (currentState is GenericState.Success) {
                val result: List<OrderBodyDTO> = currentState.data.map { dto ->
                    OrderBodyDTO(
                        id = dto.id,
                        item = dto.item,
                        code = dto.code,
                        qty = dto.qty ?: "0",
                        inv = dto.inv ?: "0",
                        uom = dto.uom ?: "Case"
                    )
                }
                val serverRequest = OrderDTO(
                    urno = order.value.urno,
                    supervisorCategoryId = sharePreference.getInt(
                        key = "sysCategory",
                        defaultValue = 0
                    ).toString(),
                    supervisorId = sharePreference.getInt(key = "id", defaultValue = 0).toString(),
                    repId = order.value.repId,
                    body = result,
                )
                remoteRepository.taskRequest(serverRequest)
                    .onSuccess { _ ->
                        _order.value = _order.value.copy(
                            confirmDialog = false,
                            loaders = false,
                            success = true
                        )
                    }
                    .onFailure { error ->
                        _order.value = _order.value.copy(
                            showAndHideErrorMessage = true,
                            loaders = false,
                            errorMessage = error.message.toString()
                        )
                    }
            }
        }
    }
}