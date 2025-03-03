package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import its.dart.com.presentation.ui.components.CButton
import its.dart.com.presentation.ui.components.LoadingDialog
import its.dart.com.presentation.ui.components.ModelsBottomSheets
import its.dart.com.presentation.ui.components.SuccessDialog
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.DailyActivityViewModel
import its.dart.com.presentation.viewmodel.event.DailyRetailActivityEvent
import its.dart.com.presentation.viewmodel.event.PackPlacementEvent

@Composable
fun DailyRetailActivity(
    navController: NavHostController,
    customerId: String,
    customerName: String,
    urno: String,
    viewModel: DailyActivityViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = urno){
        viewModel.onEvent(DailyRetailActivityEvent.OnSetCustomerIdAndURNO(urno.toInt(), customerId.toInt()))
    }

    Scaffold(
        topBar = {
            ToolBar(
                title = customerName,
                click = { navController.popBackStack() },
                clickSearch = {},
                clickMenu = {},
                navigation = true,
                fontSize = 20,
                fontFamily = robotoFamily,
                subTitle = true,
                subTitleItem = "Daily Retail Activity Form",
                letterSpacing = 0.5,
            )
        }
    ) { innerPadding ->

        DailySurvayActivity(
            modifier = Modifier.padding(innerPadding),
            navController,
            viewModel
        )
    }

}

@Composable
fun DailySurvayActivity(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: DailyActivityViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {

        OutOfStock(
            checkedSuper = uiState.tTGTSuperStockOut,
            onCheckedChangeSuper = { viewModel.onEvent(DailyRetailActivityEvent.OnTGTSuperStockOut(it))},
            checkedMTL = uiState.tTGTMLTStockOut,
            onCheckedChangeMTL = { viewModel.onEvent(DailyRetailActivityEvent.OnTGTMLTStockOut(it)) },
            checkedEXEC = uiState.execKSStockOut,
            onCheckedChangeEXEC = { viewModel.onEvent(DailyRetailActivityEvent.OnExecKSStockOut(it)) },
            checkedEXECCK = uiState.execCKStockOut,
            onCheckedChangeEXECCK = { viewModel.onEvent(DailyRetailActivityEvent.OnExecCKStockOut(it)) }
        )
        Spacer(modifier = Modifier.height(13.dp))
        SalesMade(
            item = "SALES MADE",
            uomValueSuper = uiState.tTGTSuperSalesMadeUOM,
            onUOMPriceChangeSuper = {viewModel.onEvent(DailyRetailActivityEvent.OnTGTSuperSalesMadeUOM(it))},
            quantityValueSuper = uiState.tTGTSuperSalesMade,
            onQuantityChangeSuper =  {viewModel.onEvent(DailyRetailActivityEvent.OnTGTSuperSalesMade(it))},

            uomValueMLT = uiState.tTGTMLTSalesMadeUOM,
            onUOMPriceChangeMLT = {viewModel.onEvent(DailyRetailActivityEvent.OnTGTMLTSalesMadeUOM(it))},
            quantityValueMLT = uiState.tTGTMLTSalesMade,
            onQuantityChangeMLT = {viewModel.onEvent(DailyRetailActivityEvent.OnTGTMLTSalesMade(it))},

            uomValueEXEC = uiState.execKSSalesMadeUOM,
            onUOMPriceChangeEXEC = {viewModel.onEvent(DailyRetailActivityEvent.OnExecKSSalesMadeUOM(it))},
            quantityValueEXEC = uiState.execKSSalesMade,
            onQuantityChangeEXEC = {viewModel.onEvent(DailyRetailActivityEvent.OnExecKSSalesMade(it))},

            uomValueEXECCK = uiState.execCKSalesMadeUOM,
            onUOMPriceChangeEXECCK = {viewModel.onEvent(DailyRetailActivityEvent.OnExecCKSalesMadeUOM(it))},
            quantityValueEXECCK = uiState.execCKSalesMade,
            onQuantityChangeEXECCK = {viewModel.onEvent(DailyRetailActivityEvent.OnExecCKSalesMade(it))},
        )
        Spacer(modifier = Modifier.height(13.dp))
        SalesMade(
            item = "REWARD",
            uomValueSuper = uiState.tTGTSuperRewardUOM,
            onUOMPriceChangeSuper = {viewModel.onEvent(DailyRetailActivityEvent.OnTGTSuperRewardUOM(it))},
            quantityValueSuper = uiState.tTGTSuperReward,
            onQuantityChangeSuper =  {viewModel.onEvent(DailyRetailActivityEvent.OnTGTSuperReward(it))},

            uomValueMLT = uiState.tTGTMLTRewardUOM,
            onUOMPriceChangeMLT = {viewModel.onEvent(DailyRetailActivityEvent.OnTGTMLTRewardUOM(it))},
            quantityValueMLT = uiState.tTGTMLTReward,
            onQuantityChangeMLT = {viewModel.onEvent(DailyRetailActivityEvent.OnTGTMLTReward(it))},

            uomValueEXEC = uiState.execKSRewardUOM,
            onUOMPriceChangeEXEC = {viewModel.onEvent(DailyRetailActivityEvent.OnExecKSRewardUOM(it))},
            quantityValueEXEC = uiState.execKSReward,
            onQuantityChangeEXEC = {viewModel.onEvent(DailyRetailActivityEvent.OnExecKSReward(it))},

            uomValueEXECCK = uiState.execCKRewardUOM,
            onUOMPriceChangeEXECCK = {viewModel.onEvent(DailyRetailActivityEvent.OnExecCKRewardUOM(it))},
            quantityValueEXECCK = uiState.execCKReward,
            onQuantityChangeEXECCK = {viewModel.onEvent(DailyRetailActivityEvent.OnExecCKReward(it))},
        )
        Spacer(modifier = Modifier.height(13.dp))
        ItcSalesMan(
            uomValue = uiState.itcSalesMan,
            onOptionSelected = {viewModel.onEvent(DailyRetailActivityEvent.OnITCSalesMan(it))}
        )
        Spacer(modifier = Modifier.height(13.dp))
        SampleExecKS(
            item = "SAMPLING  (EXC KS)",
            uomValue = uiState.execKSSampling,
            onOptionSelected = {viewModel.onEvent(DailyRetailActivityEvent.OnExecKSSampling(it))}
        )
        Spacer(modifier = Modifier.height(13.dp))
        SampleExecKS(
            item = "SAMPLING  (EXC CK)",
            uomValue = uiState.execCKSampling,
            onOptionSelected = {viewModel.onEvent(DailyRetailActivityEvent.OnExecCKSampling(it))}
        )
        Spacer(modifier = Modifier.height(20.dp))
        CButton(
            onClick = { viewModel.onEvent(DailyRetailActivityEvent.OnConfirmEvent) },
            buttonState = true,
            text = "Post"
        )

        HideAndShowOptionalDialog(
            btDismissState = uiState.confirmDialog,
            btDismissEvent = {viewModel.onEvent(DailyRetailActivityEvent.HideOptionalDialog)},
            btConfirmEvent = {viewModel.onEvent(DailyRetailActivityEvent.SyncDataToServer)}
        )

        LoadingDialog(
            showDialog = uiState.loaders,
            onDismiss = {}
        )

        SuccessDialog(
            showDialog = uiState.success,
            onOkClick = {
                viewModel.onEvent(DailyRetailActivityEvent.HideSuccessfulDialog)
                navController.popBackStack()
            }
        )
//
//        ModelsBottomSheets(
//            showAndHideErrorMessage = uiState.showAndHideErrorMessage,
//            onSheetStateChange = {result-> viewModel.event(PackPlacementEvent.OnShowAndHideErrorMessage(result))},
//            sheetState = sheetState,
//            errorMessage = uiState.errorMessage
//        )

    }
}

@Composable
fun OutOfStock(
    checkedSuper: String,
    onCheckedChangeSuper: (String) -> Unit,
    checkedMTL: String,
    onCheckedChangeMTL: (String) -> Unit,
    checkedEXEC: String,
    onCheckedChangeEXEC: (String) -> Unit,
    checkedEXECCK: String,
    onCheckedChangeEXECCK: (String) -> Unit,
) {

    Text(
        text = "OUT OF STOCK (OOS)",
        modifier = Modifier.padding(bottom = 4.dp),
        fontSize = 15.sp,
        color = appColorBlack,
        fontWeight = FontWeight.W800,
        fontFamily = robotoFamily
    )

    val options = listOf("TARGET SUPER RV", "TARGET MENTHOL RV", "EXECUTIVE", "EXECUTIVE CLICK")
    ProductLikeToPurchase(
        option = options[0],
        checked = checkedSuper,
        onCheckedChange = { onCheckedChangeSuper(it) }
    )

    ProductLikeToPurchase(
        option = options[1],
        checked = checkedMTL,
        onCheckedChange = { onCheckedChangeMTL(it) }
    )

    ProductLikeToPurchase(
        option = options[2],
        checked = checkedEXEC,
        onCheckedChange = { onCheckedChangeEXEC(it) }
    )

    ProductLikeToPurchase(
        option = options[3],
        checked = checkedEXECCK,
        onCheckedChange = { onCheckedChangeEXECCK(it) }
    )
}

@Composable
fun SalesMade(

    item: String,
    uomValueSuper: String,
    onUOMPriceChangeSuper: (String) -> Unit,
    quantityValueSuper: String,
    onQuantityChangeSuper: (String) -> Unit,

    uomValueMLT: String,
    onUOMPriceChangeMLT: (String) -> Unit,
    quantityValueMLT: String,
    onQuantityChangeMLT: (String) -> Unit,

    uomValueEXEC: String,
    onUOMPriceChangeEXEC: (String) -> Unit,
    quantityValueEXEC: String,
    onQuantityChangeEXEC: (String) -> Unit,

    uomValueEXECCK: String,
    onUOMPriceChangeEXECCK: (String) -> Unit,
    quantityValueEXECCK: String,
    onQuantityChangeEXECCK: (String) -> Unit,

    ) {

    Text(
        text = item,
        modifier = Modifier.padding(bottom = 4.dp),
        fontSize = 15.sp,
        color = appColorBlack,
        fontWeight = FontWeight.W800,
        fontFamily = robotoFamily
    )

    val options = listOf("TARGET SUPER RV", "TARGET MENTHOL RV", "EXECUTIVE", "EXECUTIVE CLICK")

    ProductEnter(
        priceLabel = options[0],
        uomValue = uomValueSuper,
        onUOMPriceChange = { onUOMPriceChangeSuper(it) },
        quantityValue = quantityValueSuper,
        onQuantityChange = { onQuantityChangeSuper(it) }
    )
    Spacer(modifier = Modifier.height(3.dp))
    ProductEnter(
        priceLabel = options[1],
        uomValue = uomValueMLT,
        onUOMPriceChange = { onUOMPriceChangeMLT(it) },
        quantityValue = quantityValueMLT,
        onQuantityChange = { onQuantityChangeMLT(it) }
    )
    Spacer(modifier = Modifier.height(3.dp))
    ProductEnter(
        priceLabel = options[2],
        uomValue = uomValueEXEC,
        onUOMPriceChange = { onUOMPriceChangeEXEC(it) },
        quantityValue = quantityValueEXEC,
        onQuantityChange = { onQuantityChangeEXEC(it) }
    )
    Spacer(modifier = Modifier.height(3.dp))
    ProductEnter(
        priceLabel = options[3],
        uomValue = uomValueEXECCK,
        onUOMPriceChange = { onUOMPriceChangeEXECCK(it) },
        quantityValue = quantityValueEXECCK,
        onQuantityChange = { onQuantityChangeEXECCK(it) }
    )

}


@Composable
fun ItcSalesMan(
    uomValue: String,
    onOptionSelected: (String) -> Unit
) {

    Text(
        text = "ITC SALESMAN?",
        modifier = Modifier.padding(bottom = 4.dp),
        fontSize = 15.sp,
        color = appColorBlack,
        fontWeight = FontWeight.W200,
        fontFamily = robotoFamily
    )
    DropDownMenu(
        options = listOf("YES", "NO"),
        selectedOption = uomValue,
        onOptionSelected = { onOptionSelected(it) }
    )
}

@Composable
fun SampleExecKS(
    item: String,
    uomValue: String,
    onOptionSelected: (String) -> Unit
) {

    Text(
        text = item,
        modifier = Modifier.padding(bottom = 4.dp),
        fontSize = 15.sp,
        color = appColorBlack,
        fontWeight = FontWeight.W200,
        fontFamily = robotoFamily
    )

    DropDownMenu(
        options = listOf("STICKS", "LIKE", "DISLIKE"),
        selectedOption = uomValue,
        onOptionSelected = { onOptionSelected(it) }
    )
}


