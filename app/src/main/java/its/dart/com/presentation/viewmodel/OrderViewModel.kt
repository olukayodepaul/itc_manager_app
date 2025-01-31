package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import its.dart.com.domain.repository.remote.model.ProductModel
import its.dart.com.domain.usecases.OrderUseCases
import its.dart.com.presentation.viewmodel.state.GenericState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class OrderViewModel  @Inject constructor(private val orderUserCase: OrderUseCases) : ViewModel(){

    private val _productState = MutableStateFlow<GenericState<List<ProductModel>>>(GenericState.Loading)
    val productState: StateFlow<GenericState<List<ProductModel>>> = _productState.asStateFlow()




}

