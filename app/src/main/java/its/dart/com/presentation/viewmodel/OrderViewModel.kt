package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.domain.repository.remote.model.ProductModel
import its.dart.com.domain.usecases.OrderUseCases
import its.dart.com.presentation.viewmodel.state.GenericState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrderViewModel  @Inject constructor(private val orderUserCase: OrderUseCases) : ViewModel(){

    private val _productState = MutableStateFlow<GenericState<List<ProductModel>>>(GenericState.Loading)
    val productState: StateFlow<GenericState<List<ProductModel>>> = _productState.asStateFlow()

    fun getProduct() {
        viewModelScope.launch {
            _productState.value = GenericState.Loading
            orderUserCase.fetchProduct()
                .catch { exception ->
                    _productState.value = GenericState.Failure(exception)
                }
                .collect { result ->  //observer the event with collect
                    _productState.value = GenericState.Success(result)
                }
        }
    }
}

