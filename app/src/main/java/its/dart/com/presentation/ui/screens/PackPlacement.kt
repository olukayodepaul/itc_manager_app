package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import its.dart.com.presentation.ui.components.CButton
import its.dart.com.presentation.ui.components.CustomTextFields
import its.dart.com.presentation.ui.components.DropdownListsNoneIcon
import its.dart.com.presentation.ui.components.LoadingDialog
import its.dart.com.presentation.ui.components.ModelsBottomSheets
import its.dart.com.presentation.ui.components.SuccessDialog
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.PackPlacementViewModel
import its.dart.com.presentation.viewmodel.event.DailyRetailActivityEvent
import its.dart.com.presentation.viewmodel.event.PackPlacementEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PackPlacement(
    navController: NavHostController,
    customerId: String,
    customerName: String,
    urno: String,
    viewModel: PackPlacementViewModel = hiltViewModel()
) {

    val sheetState = rememberModalBottomSheetState()

    //change to init in the view model.
    LaunchedEffect(Unit){
        viewModel.event(PackPlacementEvent.OnSetCustomerIdAndURNO(urno.toInt(), customerId.toInt()))
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
                letterSpacing = 0.5,
                subTitle = true,
                subTitleItem = "Pack Placement",
            )
        }
    ) { innerPadding ->
        PackPlacementContent(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            sheetState = sheetState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PackPlacementContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: PackPlacementViewModel = hiltViewModel(),
    sheetState: SheetState
) {

    val scrollState = rememberScrollState()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Handler(
                handler = uiState.skuHandler,
                handlerEvent = {viewModel.event(PackPlacementEvent.SkuHandler(it))}
            )
            Spacer(modifier = Modifier.height(18.dp))
            FreePackPlacement(
                tgtSuper = uiState.freePackPlacementTGTSuper,
                tgtSuperEvent = {viewModel.event(PackPlacementEvent.FreePackPlacementTGTSuper(it))},
                tgtMtl = uiState.freePackPlacementTGTMLT,
                tgtMtlEvent = {viewModel.event(PackPlacementEvent.FreePackPlacementTGTMLT(it))},
                exe = uiState.freePackPlacementExec,
                exeEvent = {viewModel.event(PackPlacementEvent.FreePackPlacementExec(it))}
            )
            Spacer(modifier = Modifier.height(18.dp))
            Others(
                bikeSales = uiState.bikeSales,
                onBikeSales = {viewModel.event(PackPlacementEvent.BikeSales(it))},
                saleMan = uiState.salesManID,
                onSaleMan = {viewModel.event(PackPlacementEvent.SalesManID(it))},

                item = "Quantity Bought",
                uomValueSuper = uiState.tTGTSuperSalesMadeUOM,
                onUOMPriceChangeSuper = {viewModel.event(PackPlacementEvent.OnTGTSuperSalesMadeUOM(it))},
                quantityValueSuper = uiState.tTGTSuperSalesMade,
                onQuantityChangeSuper =  {viewModel.event(PackPlacementEvent.OnTGTSuperSalesMade(it))},

                uomValueMLT = uiState.tTGTMLTSalesMadeUOM,
                onUOMPriceChangeMLT = {viewModel.event(PackPlacementEvent.OnTGTMLTSalesMadeUOM(it))},
                quantityValueMLT = uiState.tTGTMLTSalesMade,
                onQuantityChangeMLT = {viewModel.event(PackPlacementEvent.OnTGTMLTSalesMade(it))},

                uomValueEXEC = uiState.execKSSalesMadeUOM,
                onUOMPriceChangeEXEC = {viewModel.event(PackPlacementEvent.OnExecKSSalesMadeUOM(it))},
                quantityValueEXEC = uiState.execKSSalesMade,
                onQuantityChangeEXEC = {viewModel.event(PackPlacementEvent.OnExecKSSalesMade(it))},

                uomValueEXECCK = uiState.execCKSalesMadeUOM,
                onUOMPriceChangeEXECCK = {viewModel.event(PackPlacementEvent.OnExecCKSalesMadeUOM(it))},
                quantityValueEXECCK = uiState.execCKSalesMade,
                onQuantityChangeEXECCK = {viewModel.event(PackPlacementEvent.OnExecCKSalesMade(it))},
            )
            Spacer(modifier = Modifier.height(30.dp))
            CButton(
                onClick = { viewModel.event(PackPlacementEvent.ShowOptionalDialog) },
                buttonState = true,
                text = "Post"
            )

            HideAndShowOptionalDialog(
                btDismissState = uiState.confirmDialog,
                btDismissEvent = {viewModel.event(PackPlacementEvent.HideOptionalDialog)},
                btConfirmEvent = {
                    viewModel.event(PackPlacementEvent.OnConfirmEvent)
                }
            )

            LoadingDialog(
                showDialog = uiState.loaders,
                onDismiss = {}
            )

            SuccessDialog(
                showDialog = uiState.success,
                onOkClick = {
                    viewModel.event(PackPlacementEvent.ShowSuccessfulDialog)
                    navController.popBackStack()
                }
            )

            ModelsBottomSheets(
                showAndHideErrorMessage = uiState.showAndHideErrorMessage,
                onSheetStateChange = {result-> viewModel.event(PackPlacementEvent.OnShowAndHideErrorMessage(result))},
                sheetState = sheetState,
                errorMessage = uiState.errorMessage
            )

        }
    }
}


@Composable
fun Handler(
    handler: String,
    handlerEvent:(String)->Unit
) {
    Row(
        modifier = Modifier.padding(bottom = 8.dp, start = 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NumberedCircle(number = 1)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Sku Handler",
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 18.sp,
            fontWeight = FontWeight.W500
        )
    }
    val options = listOf("Select", "Yes", "No",)
    var selectedOption by remember { mutableStateOf(options.first()) }
    DropdownListsNoneIcon(
        options = options,
        selectedOption = handler,
        onOptionSelected = { handlerEvent(it)},
        label = "Handler"
    )
}

@Composable
fun FreePackPlacement(
    tgtSuper:String,
    tgtSuperEvent: (String)->Unit,
    tgtMtl: String,
    tgtMtlEvent: (String)->Unit,
    exe:String,
    exeEvent:(String)->Unit
){

    Column {
        Row(
            modifier = Modifier.padding(bottom = 8.dp, start = 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberedCircle(number = 2)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Free Pack Placement",
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 18.sp,
                fontWeight = FontWeight.W500
            )
        }

        CustomTextFields(
            label = "TGT SUP",
            value = tgtSuper,
            onValueChange = { tgtSuperEvent(it) }
        )

        CustomTextFields(
            label = "TGT MTL",
            value = tgtMtl,
            onValueChange = { tgtMtlEvent(it) }
        )

        CustomTextFields(
            label = "EXC",
            value = exe,
            onValueChange = { exeEvent(it) }
        )
    }
}

@Composable
fun Others(
    bikeSales: String,
    onBikeSales: (String) ->Unit,
    saleMan: String,
    onSaleMan: (String)->Unit,

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
){

    Row(
        modifier = Modifier.padding(bottom = 8.dp, start = 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NumberedCircle(number = 3)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Other Questions",
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 18.sp,
            fontWeight = FontWeight.W500
        )
    }

    Column {

        val option = listOf("TARGET SUPER RV", "TARGET MENTHOL RV", "EXECUTIVE", "EXECUTIVE CLICK")

        ProductEnter(
            priceLabel = option[0],
            uomValue = uomValueSuper,
            onUOMPriceChange = { onUOMPriceChangeSuper(it) },
            quantityValue = quantityValueSuper,
            onQuantityChange = { onQuantityChangeSuper(it) }
        )
        Spacer(modifier = Modifier.height(3.dp))
        ProductEnter(
            priceLabel = option[1],
            uomValue = uomValueMLT,
            onUOMPriceChange = { onUOMPriceChangeMLT(it) },
            quantityValue = quantityValueMLT,
            onQuantityChange = { onQuantityChangeMLT(it) }
        )
        Spacer(modifier = Modifier.height(3.dp))
        ProductEnter(
            priceLabel = option[2],
            uomValue = uomValueEXEC,
            onUOMPriceChange = { onUOMPriceChangeEXEC(it) },
            quantityValue = quantityValueEXEC,
            onQuantityChange = { onQuantityChangeEXEC(it) }
        )
        Spacer(modifier = Modifier.height(3.dp))
        ProductEnter(
            priceLabel = option[3],
            uomValue = uomValueEXECCK,
            onUOMPriceChange = { onUOMPriceChangeEXECCK(it) },
            quantityValue = quantityValueEXECCK,
            onQuantityChange = { onQuantityChangeEXECCK(it) }
        )

        Spacer(modifier = Modifier.height(18.dp))
        val options = listOf("Select", "Yes", "No",)
        DropdownListsNoneIcon(
            options = options,
            selectedOption = bikeSales,
            onOptionSelected = { onBikeSales(it) },
            label = "Bike Sales"
        )
        Spacer(modifier = Modifier.height(18.dp))
        CustomTextFields(
            label = "Sales Man's Name",
            value = saleMan,
            onValueChange = { onSaleMan(it) }
        )
    }

}
