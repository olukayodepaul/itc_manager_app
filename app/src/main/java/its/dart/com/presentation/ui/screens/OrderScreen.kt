package its.dart.com.presentation.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import its.dart.com.domain.repository.remote.model.ProductModel
import its.dart.com.presentation.ui.components.CButton
import its.dart.com.presentation.ui.components.CircleAvatar
import its.dart.com.presentation.ui.components.LoadingIndicator
import its.dart.com.presentation.ui.components.SuccessDialog
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
    identifier: String,
    viewModel: OrderViewModel = hiltViewModel()
) {

    val order by viewModel.productState.collectAsStateWithLifecycle()

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

    var showDialog by remember { mutableStateOf(false) }

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

        Column(modifier = Modifier.padding(16.dp)) {
            CButton(
                onClick = {
                    showDialog = true
                },
                buttonState = true,
                text = "Post Order",
                roundState = true
            )
        }

        SuccessDialog(
            showDialog = showDialog,
            onOkClick = {
                showDialog = false
                navController.popBackStack()
            }
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
                    fontSize = 15.sp,
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
                    .width(80.dp)
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Adds spacing between items
            ) {

                var inv by remember { mutableStateOf("") }
                TextFieldInput(
                    value = inv,
                    onValueChange = { input ->
                        // Allow only digits (0-9)
                        if (input.isEmpty() || input.matches(Regex("^\\d*\$"))) {
                            inv = input
                        }
                    },
                    hint = "Inv",
                    isNumericOnly = true
                )
            }

            Row(
                modifier = Modifier
                    .width(80.dp)
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                var qty by remember { mutableStateOf("") }
                TextFieldInput(
                    value = qty,
                    onValueChange = { input ->
                        if (input.isEmpty() || input.matches(Regex("^\\d*\\.?\\d*\$"))) {
                            qty = input
                        }
                    },
                    hint = "Qty",
                    isNumericOnly = true
                )
            }
        }
    }
}


