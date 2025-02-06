package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import its.dart.com.domain.repository.remote.model.ProductModel
import its.dart.com.presentation.ui.components.CButton
import its.dart.com.presentation.ui.components.CircleAvatar
import its.dart.com.presentation.ui.components.LoadingIndicator
import its.dart.com.presentation.ui.components.TextFieldInput
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.appColor
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.OrderViewModel
import its.dart.com.presentation.viewmodel.state.GenericState


@Composable
fun OrderScreen(
    navController: NavHostController,
    userId: String,
    userName: String,
    identifier: String = "0",
    viewModel: OrderViewModel = hiltViewModel(),
) {
    val order by viewModel.productState.collectAsState()

    LaunchedEffect(key1 = null) {
        viewModel.getProduct()
    }

    Scaffold(
        topBar = {
            ToolBar(
                title = userName,
                click = { navController.popBackStack() },
                clickSearch = {},
                clickMenu = {},
                navigation = true,
                subTitle = true,
                fontSize = 20,
                fontFamily = robotoFamily,
                letterSpacing = 0.5,
                subTitleItem = "Order Product"
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
        ) {
            when (val state = viewModel.productState.value) {
                is GenericState.Loading -> {
                    LoadingIndicator()
                }
                is GenericState.Success -> {
                    OrderScreenContent(
                        userId = userId,
                        userName = userName,
                        identifier = identifier,
                        order = state.data,
                        navController = navController
                    )
                }
                is GenericState.Failure -> {

                }
            }
        }
    }
}

@Composable
fun OrderScreenContent(
    userId: String,
    userName: String,
    identifier: String = "0",
    order: List<ProductModel>,
    navController: NavHostController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // Takes up all available vertical space
            state = rememberLazyListState()
        ) {
            items(
                items = order,
                key = { it.id }
            ) { customer ->
                OrderList(navController, customer)
            }
        }
        CButton(
            onClick = {},
            buttonState = true,
            text = "Post Order",
            roundState = true
        )
    }
}

@Composable
fun OrderList(
    navController: NavHostController,
    order: ProductModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircleAvatar(
                initialName = order.item,
                id = order.id
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = order.item,
                    fontWeight = FontWeight(400),
                    maxLines = 1,
                    fontSize = 18.sp,
                    color = appColor,
                )
                Text(
                    text = "${order.code}",
                    maxLines = 1,
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
            }
            Row(
                modifier = Modifier
                    .width(90.dp)
                    .padding(end = 10.dp)
            ) {
                TextFieldInput(
                    value = "",
                    onValueChange = {  },
                    hint = "Qty"
                )
            }
        }
    }
}


