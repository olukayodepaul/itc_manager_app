package its.dart.com.presentation.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiPeople
import androidx.compose.material.icons.filled.GifBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PeopleOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
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
import its.dart.com.presentation.ui.components.CustomTextField
import its.dart.com.presentation.ui.components.DropdownLists
import its.dart.com.presentation.ui.components.ProductCheckbox
import its.dart.com.presentation.ui.components.TextFieldInput
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.mainGray
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.DailyConsumerViewModel
import its.dart.com.presentation.viewmodel.event.DailyActivityForm


@Composable
fun DailyConsumer(
    navController: NavHostController,
    userId: String,
    userName: String,
    urno: String,
    viewModel: DailyConsumerViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            ToolBar(
                title = userName,
                click = {navController.popBackStack()},
                clickSearch = {},
                clickMenu = {},
                navigation = true,
                fontSize = 20,
                fontFamily = robotoFamily,
                letterSpacing = 0.5,
                subTitle = true,
                subTitleItem = "Daily Consumer Data Form"
            )
        }
    ) { innerPadding ->
        DailyConsumerContent(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            viewModel
        )
    }
}

@Composable
fun DailyConsumerContent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: DailyConsumerViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val iuState by  viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {

            ConsumerName(
                valueCustomerName = iuState.consumerName,
                onValueChangeCustomerName = {viewModel.onEvent(DailyActivityForm.OnConsumerName(it))},
                valuePhoneNumber = iuState.phoneNumber,
                onValueChangePhoneNumber = {viewModel.onEvent(DailyActivityForm.OnPhoneNumber(it))},
                valueGender = iuState.gender,
                onValueGender = {viewModel.onEvent(DailyActivityForm.OnGender(it))},
                valueAge = iuState.age,
                onValueAge = {viewModel.onEvent(DailyActivityForm.OnAge(it))},
                valueAnySales = iuState.anySales,
                onValueAnySales = {viewModel.onEvent(DailyActivityForm.OnAnySales(it))},
                valuePersonalBrand = iuState.personalBrands,
                onValuePersonalBrand = {viewModel.onEvent(DailyActivityForm.OnPersonalBrands(it))}
            )

            SampleSku(
                checkedSuper = iuState.sampleSuper,
                onCheckedChangeSuper = {viewModel.onEvent(DailyActivityForm.OnSampleSuper(it))},
                checkedMTL = iuState.sampleMenthol,
                onCheckedChangeMTL = {viewModel.onEvent(DailyActivityForm.OnSampleMenthol(it))},
                checkedEXEC = iuState.sampleExec,
                onCheckedChangeEXEC = {viewModel.onEvent(DailyActivityForm.OnSampleExec(it))},
                checkedEXECCK = iuState.sampleExecClick,
                onCheckedChangeEXECCK = {viewModel.onEvent(DailyActivityForm.OnSampleExecClick(it))}
            )

            val options = listOf("TARGET SUPER RV", "TARGET MENTHOL RV", "EXECUTIVE", "EXECUTIVE CLICK")
            QuantitySold(
                priceLabel = options[0],
                uomValue = iuState.qtySoldSuperUOM,
                onUOMPriceChange = {viewModel.onEvent(DailyActivityForm.OnQtySoldSuperUOM(it))},
                quantityValue = iuState.qtySoldSuper,
                onQuantityChange = {viewModel.onEvent(DailyActivityForm.OnQtySoldSuper(it))}
            )

            QuantitySold(
                priceLabel = options[1],
                uomValue = iuState.qtySoldMentholUOM,
                onUOMPriceChange = {viewModel.onEvent(DailyActivityForm.OnQtySoldMentholUOM(it))},
                quantityValue = iuState.qtySoldMenthol,
                onQuantityChange = {viewModel.onEvent(DailyActivityForm.OnQtySoldMenthol(it))}
            )

            QuantitySold(
                priceLabel = options[2],
                uomValue = iuState.qtySoldExecUOM,
                onUOMPriceChange = {viewModel.onEvent(DailyActivityForm.OnQtySoldExecUOM(it))},
                quantityValue = iuState.qtySoldExec,
                onQuantityChange = {viewModel.onEvent(DailyActivityForm.OnQtySoldExec(it))}
            )

            QuantitySold(
                priceLabel = options[2],
                uomValue = iuState.qtySoldExecClickUOM,
                onUOMPriceChange = {viewModel.onEvent(DailyActivityForm.OnQtySoldExecClickUOM(it))},
                quantityValue = iuState.qtySoldExecClick,
                onQuantityChange = {viewModel.onEvent(DailyActivityForm.OnQtySoldExecClick(it))}
            )

            SampleSku(
                checkedSuper = iuState.sampleSuper,
                onCheckedChangeSuper = {viewModel.onEvent(DailyActivityForm.OnSampleSuper(it))},
                checkedMTL = iuState.sampleMenthol,
                onCheckedChangeMTL = {viewModel.onEvent(DailyActivityForm.OnSampleMenthol(it))},
                checkedEXEC = iuState.sampleExec,
                onCheckedChangeEXEC = {viewModel.onEvent(DailyActivityForm.OnSampleExec(it))},
                checkedEXECCK = iuState.sampleExecClick,
                onCheckedChangeEXECCK = {viewModel.onEvent(DailyActivityForm.OnSampleExecClick(it))}
            )

            PMS(
                checkedSuper = iuState.PMBLTH,
                onCheckedChangeSuper = {viewModel.onEvent(DailyActivityForm.OnPMBLTH(it))},
                checkedMTL = iuState.PMBPEN,
                onCheckedChangeMTL = {viewModel.onEvent(DailyActivityForm.OnPMBPEN(it))},
                checkedEXEC = iuState.PMBWB,
                onCheckedChangeEXEC = {viewModel.onEvent(DailyActivityForm.OnPMBWB(it))},
            )

            val feedback = listOf(
                "⭐⭐⭐⭐⭐ – Excellent product! Exceeded my expectations. Will definitely recommend!",
                "⭐⭐⭐⭐☆ – Very good quality, but shipping took longer than expected.",
                "⭐⭐⭐☆☆ – Decent, but I was expecting better durability for the price.",
                "⭐⭐☆☆☆ – Not satisfied. The product did not match the description.",
                "⭐☆☆☆☆ – Terrible experience! Poor customer service and faulty product."
            )

            DropdownLists(
                options = feedback,
                selectedOption = iuState.comment,
                onOptionSelected = {viewModel.onEvent(DailyActivityForm.OnComment(it))},
                label = "Comment"
            )

            CButton(
                onClick = {  },
                buttonState = true,
                text = "Post"
            )
        }

    }
}

@Composable
fun ConsumerName(
    valueCustomerName: String,
    onValueChangeCustomerName:(String)->Unit,
    valuePhoneNumber: String,
    onValueChangePhoneNumber:(String)->Unit,
    valueGender: String,
    onValueGender:(String)->Unit,
    valueAge: String,
    onValueAge:(String)->Unit,
    valueAnySales: String,
    onValueAnySales:(String)->Unit,
    valuePersonalBrand: String,
    onValuePersonalBrand:(String)->Unit,
    ) {
    CustomTextField(
        label = "Consumer Name",
        value = valueCustomerName,
        onValueChange = {onValueChangeCustomerName(it)},
        leadingIcon = Icons.Default.Home
    )

    CustomTextField(
        label = "Phone Number",
        value = valuePhoneNumber,
        onValueChange = { input ->
            if (input.isEmpty() || input.matches(Regex("^\\d*\$"))) {
                onValueChangePhoneNumber(input)
            }
        },
        leadingIcon = Icons.Default.Home
    )

    val options = listOf("Select", "Male", "Female",)
    DropdownLists(
        options = options,
        selectedOption = valueGender,
        onOptionSelected = {onValueGender(it) },
        label = "Gender",
        leadingIcon = Icons.Default.PeopleOutline
    )

    CustomTextField(
        label = "Age",
        value = valueAge,
        onValueChange = { input ->
            if (input.isEmpty() || input.matches(Regex("^\\d*\$"))) {
                onValueAge(input)
            }
        },
        leadingIcon = Icons.Default.EmojiPeople,
        isNumericOnly = true
    )

    val anySalesOptions = listOf("yes", "No",)
    DropdownLists(
        options = anySalesOptions,
        selectedOption = valueAnySales,
        onOptionSelected = {onValueAnySales(it) },
        label = "Any Sales",
        leadingIcon = Icons.Default.PeopleOutline
    )


    val personalBrandOptions = listOf("Select", "B & H boost", "sterling blue", "B & H flavour",
        "B & H switch","pall mall excel", "bohem", "aspen" ,"B & H demi", "oris flavour", "pall mall filter","yes","oris slim"
        ,"time menthol","Royal standard","pall mall menthol","chesterfield","dorchester", "rothmans"
    )
    DropdownLists(
        options = personalBrandOptions,
        selectedOption = valuePersonalBrand,
        onOptionSelected = {  onValuePersonalBrand(it)},
        label = "Personal Brand",
        leadingIcon = Icons.Default.GifBox
    )

}



@Composable
fun SampleSku(
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
        text = "SAMPLE SKU",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 4.dp),
        fontSize = 20.sp,
        fontWeight = FontWeight.W900
    )

    Spacer(modifier = Modifier.height(16.dp))
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
fun PMS(
    checkedSuper: String,
    onCheckedChangeSuper: (String) -> Unit,
    checkedMTL: String,
    onCheckedChangeMTL: (String) -> Unit,
    checkedEXEC: String,
    onCheckedChangeEXEC: (String) -> Unit,
) {

    Text(
        text = "PMS",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 4.dp),
        fontSize = 20.sp,
        fontWeight = FontWeight.W900
    )

    Spacer(modifier = Modifier.height(16.dp))
    val options = listOf("LTH", "PEN", "WB")
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

}



@Composable
fun QuantitySold(
    priceLabel: String,
    uomValue: String,
    onUOMPriceChange: (String) -> Unit,
    quantityValue: String,
    onQuantityChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        ) {
            Text(
                text = priceLabel,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                color = mainGray
            )
        }

        Row(
            modifier = Modifier
                .width(120.dp)
                .padding(end = 3.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DropDownMenu(
                options = listOf("Case", "Pack", "Roll"),
                selectedOption = uomValue,
                onOptionSelected = { onUOMPriceChange(it) }
            )
        }

        Row(
            modifier = Modifier
                .width(80.dp)
                .padding(end = 3.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextFieldInput(
                value = quantityValue,
                onValueChange = { input ->
                    if (input.isEmpty() || input.matches(Regex("^\\d*\\.?\\d*\$"))) {
                        onQuantityChange(input)
                    }
                },
                hint = "QTY",
                isNumericOnly = true,
            )
        }
    }
}

@Composable
fun CheckBoxFeed(){

    var selected by remember { mutableStateOf<String?>(null) }
    val proOne = "agreed"

    ProductCheckbox(
        productName = "The checking is an acknowledgment that the person has entered the information correctly and agrees to be the one who entered it.",
        isChecked = selected === proOne,
        onCheckedChange = { newValue ->
            selected = if (newValue) proOne else ""
        }
    )
}