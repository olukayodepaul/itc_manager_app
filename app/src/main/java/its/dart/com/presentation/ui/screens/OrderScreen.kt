package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import its.dart.com.domain.repository.remote.model.ProductModel
import its.dart.com.presentation.ui.components.Failure
import its.dart.com.presentation.ui.components.ToolBar
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
        is GenericState.Failure->{
            Failure(
                errorMessage = "${state.exception}"?: "Unknown error",
                onRetry = {}
            )
        }
        is GenericState.Success->{
            HeaderContent(
                state.data,
                navController
            )
        }
    }
}


@Composable
fun HeaderContent(
    order: List<ProductModel>,
    navController: NavHostController
){
    Scaffold(
        topBar = {
            ToolBar(
                title = "Order",
                click = {},
                clickSearch = {},
                clickMenu = {},
                navigation = false
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFFFFFFFF))
        ) {

        }
    }
}