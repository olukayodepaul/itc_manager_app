package its.dart.com.presentation.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import its.dart.com.presentation.ui.components.LoadingDialog
import its.dart.com.presentation.ui.components.ModelsBottomSheets
import its.dart.com.presentation.ui.components.SuccessDialog
import its.dart.com.presentation.ui.components.TextFieldInput
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.OrangeStart
import its.dart.com.presentation.ui.theme.appColor
import its.dart.com.presentation.ui.theme.appColorGray
import its.dart.com.presentation.ui.theme.appColorWhite
import its.dart.com.presentation.ui.theme.iconColor
import its.dart.com.presentation.ui.theme.mainGray
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.OrderViewModel
import its.dart.com.presentation.viewmodel.event.AddCustomerViewEvent
import its.dart.com.presentation.viewmodel.state.GenericState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    navController: NavHostController,
    userId: String,
    userName: String,
    urno: String,
    viewModel: OrderViewModel = hiltViewModel()
) {

    val uiState  by viewModel.order.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState()


    LaunchedEffect(Unit) {
        viewModel.getProduct()
    }

    LaunchedEffect(Unit){
        viewModel.getUiState(urno.toInt(), userId.toInt())
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

            Spacer(modifier = Modifier.height(30.dp))

            when (val state = viewModel.productState.collectAsStateWithLifecycle().value) {
                is GenericState.Loading -> {

                }
                is GenericState.Success -> {
                    OrderScreenContent(
                        userId = userId,
                        userName = userName,
                        urno = urno,
                        navController = navController,
                        product = state.data,
                        onQtyUpdate = {productId, newQty, specific -> viewModel.updateProduct(productId, newQty, specific)},
                        onClick = {viewModel.showConfirmDialog()},
                        totalInventory = viewModel.totalInv,
                        totalOrder = viewModel.totalQty
                    )
                }
                is GenericState.Failure -> {

                }
            }

            //confirm and dismiss dialog
            HideAndShowOptionalDialog(
                btDismissState = uiState.confirmDialog,
                btDismissEvent = {viewModel.onDisMissConfirm()},
                btConfirmEvent = {viewModel.onSubmit()}
            )

            LoadingDialog(
                showDialog = uiState.loaders,
                onDismiss = {}
            )

            SuccessDialog(
                showDialog = uiState.success,
                onOkClick = {
                    viewModel.disMissSuccessfulDialog()
                    navController.popBackStack()
                }
            )

            ModelsBottomSheets(
                showAndHideErrorMessage = uiState.showAndHideErrorMessage,
                onSheetStateChange = {result-> viewModel.disMissBottomSheet(result)},
                sheetState = sheetState,
                errorMessage = uiState.errorMessage
            )

        }
    }
}

@Composable
fun OrderScreenContent(
    userId: String,
    userName: String,
    urno: String,
    navController: NavHostController,
    product : List<ProductModel>,
    onQtyUpdate:(productId: Int, newQty:String,specific: Int)->Unit,
    onClick: ()->Unit,
    totalInventory: Int,
    totalOrder: Int
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            state = rememberLazyListState()
        ) {
            items(
                items = product,
                key = { it.id }
            ) { product ->
                ProductOrdered(
                    product = product,
                    onQtyUpdate =  onQtyUpdate
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(appColorGray.copy(alpha = 0.4f))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.weight(1f, false),
                text = totalOrder.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = iconColor
            )
            Text(
                modifier = Modifier.weight(1f, false),
                text = totalInventory.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = iconColor
            )
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = appColor),
                onClick = { onClick() }
            ) {
                Text(
                    fontWeight = FontWeight.W500,
                    text= "POST",
                    fontSize = 16.sp,
                    color = appColorWhite
                )
            }
        }
    }
}

@Composable
fun ProductOrdered(
    product: ProductModel,
    onQtyUpdate:(productId: Int, newQty:String,specific: Int)->Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            text = product.item,
            fontWeight = FontWeight.W900,
            fontSize = 18.sp,
            color = mainGray
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f, false) ){
            Text(
                fontWeight = FontWeight.W500,
                text= "Quantity",
                fontSize = 13.sp,
                color = mainGray
            )
            TextFieldInput(
                value = product.qty.toString(),
                onValueChange = { input ->
                    if (input.isEmpty() || input.matches(Regex("^\\d*\\.?\\d*\$"))) {
                        onQtyUpdate(product.id, input, 1)
                    }
                },
                hint = "QTY",
                isNumericOnly = true,
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        Column(modifier = Modifier.weight(1f, false)){
            Text(
                fontWeight = FontWeight.W500,
                text= "Inventory",
                fontSize = 13.sp,
                color = mainGray
            )
            TextFieldInput(
                value = product.inv.toString(),
                onValueChange = { input ->
                    if (input.isEmpty() || input.matches(Regex("^\\d*\\.?\\d*\$"))) {
                        onQtyUpdate(product.id, input, 2)
                    }
                },
                hint = "INV.",
                isNumericOnly = true,
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        Column(modifier = Modifier.weight(1f, false) ){
            Text(
                fontWeight = FontWeight.W500,
                text= "UOM",
                fontSize = 13.sp,
                color = mainGray
            )
            DropDownMenu(
                options = listOf("Case", "Pack", "Roll"),
                selectedOption = product.uom.toString(),
                onOptionSelected = { input ->
                    onQtyUpdate(product.id, input, 3)
                }
            )
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}
