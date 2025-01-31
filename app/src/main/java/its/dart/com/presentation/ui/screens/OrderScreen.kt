package its.dart.com.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import its.dart.com.presentation.ui.components.Failure
import its.dart.com.presentation.viewmodel.OrderViewModel
import its.dart.com.presentation.viewmodel.state.GenericState

@Composable
fun OrderScreen(
    navController: NavHostController,
    viewModel : OrderViewModel = hiltViewModel()
) {
    val order by  viewModel.productState.collectAsState()

    LaunchedEffect(key1 = null) {
        viewModel.getProduct()
    }

    when(val state = order){
        is GenericState.Loading->{}
        is GenericState.Failure->{}
        is GenericState.Success->{
            Failure(
                errorMessage = "${state.data[1]}"?: "Unknown error",
                onRetry = { }
            )
        }
    }
}