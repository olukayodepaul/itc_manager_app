@file:OptIn(ExperimentalMaterial3Api::class)

package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import its.dart.com.presentation.ui.components.CButton
import its.dart.com.presentation.ui.components.LoadingDialog
import its.dart.com.presentation.ui.components.OptionalDialog
import its.dart.com.presentation.ui.components.ProductCheckbox
import its.dart.com.presentation.ui.components.SuccessDialog
import its.dart.com.presentation.ui.components.TextFieldInput
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.appColor
import its.dart.com.presentation.ui.theme.mainGray
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.SurveyViewModel
import its.dart.com.presentation.viewmodel.event.LoginViewEvent
import its.dart.com.presentation.viewmodel.event.SurveyEvent


@Composable
fun SurveyScreen(
    navController: NavHostController,
    userId: String,
    userName: String,
    urno: String,
    viewModel: SurveyViewModel = hiltViewModel()
) {
    val uiState by viewModel.surveyState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit){
        viewModel.onEvent(SurveyEvent.Urno(urno))
    }

    if(uiState.navigation) {
        navController.popBackStack()
        viewModel.onEvent(SurveyEvent.Navigation(false))
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
                subTitleItem = "Customer Survey"
            )
        }
    ) { innerPadding ->
        CustomerSurveyScreen(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            userName,
            viewModel
        )
    }
}

@Composable
fun CustomerSurveyScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    userName: String,
    viewModel: SurveyViewModel = hiltViewModel()
) {
    val uiState by viewModel.surveyState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            First(viewModel = viewModel)
            Second(viewModel = viewModel)
            Third(viewModel = viewModel)
            Fourth(viewModel = viewModel)
            Fifth(viewModel = viewModel)

            LoginButton(
                click = {
                    viewModel.onEvent(SurveyEvent.OnclickButton)
                }
            )
            LoadingDialog(
                showDialog = uiState.loaders,
                onDismiss = {}
            )

            OptionalDialog(
                btDismissState = uiState.btDismissState,
                btDismissEvent = {viewModel.onEvent(SurveyEvent.OnDismiss)},
                btConfirmEvent = {viewModel.onEvent(SurveyEvent.OnConfirm)},
                text = "Post 1 survey for $userName to server",
                btConfirmTitle = "Confirm",
                btDismissTitle ="Cancel",
            )

            SuccessDialog(
                showDialog = uiState.showDialog,
                onOkClick = {viewModel.onEvent(SurveyEvent.OnOkClick)}
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun First(
    viewModel: SurveyViewModel = hiltViewModel()
) {
    val uiState by viewModel.surveyState.collectAsStateWithLifecycle()

    CircleAvaters(number = 1, text = "Rep Visit Frequency")
    Text(
        text = "How often does the sales representative visit your outlet?",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    DropDownMenu(
        options = listOf("Daily", "Weekly", "Bi-weekly", "Monthly", "Rarely"),
        selectedOption = uiState.salesRepVisit,
        onOptionSelected = { viewModel.onEvent(SurveyEvent.SalesRepVisit(it)) }
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "When was the last time the sales representative visited?",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    OutlinedTextField(
        value = uiState.salesRepVisitDate,
        onValueChange = { viewModel.onEvent(SurveyEvent.SalesRepVisitDate(it)) },
        label = { Text("Enter date") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp)),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFEEEEEE),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp)
    )
    Text(
        text = "Is the frequency of visits sufficient for your needs?",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    DropDownMenu(
        options = listOf("Yes", "No"),
        selectedOption = uiState.salesRepVisitSequence,
        onOptionSelected = { viewModel.onEvent(SurveyEvent.SalesRepVisitSequence(it)) }
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Do you feel the sales representative is proactive?",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    DropDownMenu(
        options = listOf("Yes", "No"),
        selectedOption = uiState.salesRepVisitProactive,
        onOptionSelected = { viewModel.onEvent(SurveyEvent.SalesRepVisitProactive(it)) }
    )
}

@Composable
fun Second(
    viewModel: SurveyViewModel = hiltViewModel()
) {
    val uiState by viewModel.surveyState.collectAsStateWithLifecycle()

    CircleAvaters(number = 2, text = "Customer Satisfaction")
    Text(
        text = "How satisfied are you with the service?",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    DropDownMenu(
        options = listOf(
            "Very Satisfied",
            "Satisfied",
            "Neutral",
            "Dissatisfied",
            "Very Dissatisfied"
        ),
        selectedOption = uiState.howSatisfy,
        onOptionSelected = { viewModel.onEvent(SurveyEvent.HowSatisfy(it)) }
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Are there any unresolved issues or complaints?",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    DropDownMenu(
        options = listOf("Yes", "No"),
        selectedOption = uiState.unResolveIssue,
        onOptionSelected = { viewModel.onEvent(SurveyEvent.UnResolveIssue(it)) }
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "How would you rate the sales representative's product knowledge?",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 4.dp)
    )

    DropDownMenu(
        options = listOf("Excellent", "Good", "Average", "Poor"),
        selectedOption = uiState.salesRating,
        onOptionSelected = { viewModel.onEvent(SurveyEvent.SalesRating(it)) }
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "How responsive is the sales representative?",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    DropDownMenu(
        options = listOf("Very Responsive", "Responsive", "Neutral", "Slow", "Very Slow"),
        selectedOption = uiState.salesRepVisitResponsiveness,
        onOptionSelected = { viewModel.onEvent(SurveyEvent.SalesRepVisitResponsiveness(it)) }
    )

}

@Composable
fun Third(
    viewModel: SurveyViewModel = hiltViewModel()
) {

    val uiState by viewModel.surveyState.collectAsStateWithLifecycle()

    CircleAvaters(number = 3, text = "Sales Performance")

    Text(
        text = "Which products do you purchase most frequently?",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 4.dp)
    )

    val options = listOf("TARGET SUPER RV", "TARGET MENTHOL RV", "EXECUTIVE", "EXECUTIVE CLICK")

    options.forEach { option ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.onEvent(SurveyEvent.SalesPerformanceProductPurchase(option)) }
        ) {
            Text(text = option, modifier = Modifier.weight(1f))
            RadioButton(
                selected = uiState.salesPerformanceProductPurchase == option,
                onClick = { viewModel.onEvent(SurveyEvent.SalesPerformanceProductPurchase(option)) }
            )

        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "How much of each product do you typically order?",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    Spacer(modifier = Modifier.height(25.dp))
    ProductEnter(
        priceLabel = options[0],
        uomValue = uiState.targetSuperUOM,
        onUOMPriceChange = {
             viewModel.onEvent(SurveyEvent.TargetSuperUOM(it))
        },
        quantityValue = uiState.targetSuperPrice,
        onQuantityChange = {
            viewModel.onEvent(SurveyEvent.TargetSuperPrice(it))
        }
    )
    Spacer(modifier = Modifier.height(16.dp))
    ProductEnter(
        priceLabel = options[1],
        uomValue = uiState.targetMentholUOM,
        onUOMPriceChange = {
            viewModel.onEvent(SurveyEvent.TargetMentholUOM(it))
        },
        quantityValue = uiState.targetMentholPrice,
        onQuantityChange = {
            viewModel.onEvent(SurveyEvent.TargetMentholPrice(it))
        }
    )
    Spacer(modifier = Modifier.height(16.dp))
    ProductEnter(
        priceLabel = options[2],
        uomValue = uiState.execUOM,
        onUOMPriceChange = {
            viewModel.onEvent(SurveyEvent.ExecUOM(it))
        },
        quantityValue = uiState.execPrice,
        onQuantityChange = {
            viewModel.onEvent(SurveyEvent.ExecPrice(it))
        }
    )
    Spacer(modifier = Modifier.height(16.dp))
    ProductEnter(
        priceLabel = options[3],
        uomValue = uiState.execClickUOM,
        onUOMPriceChange = {
            viewModel.onEvent(SurveyEvent.ExecClickUOM(it))
        },
        quantityValue = uiState.execClickPrice,
        onQuantityChange = {
            viewModel.onEvent(SurveyEvent.ExecClickPrice(it))
        }
    )
    Spacer(modifier = Modifier.height(25.dp))
    Text(
        text = "Are there any products you would like to order but are not currently purchasing?",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    Spacer(modifier = Modifier.height(16.dp))
    ProductLikeToPurchase(
        option = options[0],
        checked = uiState.targetSuperPurchase,
        onCheckedChange = {viewModel.onEvent(SurveyEvent.TargetSuperPurchase(it))}
    )
    Spacer(modifier = Modifier.height(16.dp))
    ProductLikeToPurchase(
        option = options[1],
        checked = uiState.targetMentholPurchase,
        onCheckedChange = {viewModel.onEvent(SurveyEvent.TargetMentholPurchase(it))}
    )
    Spacer(modifier = Modifier.height(16.dp))
    ProductLikeToPurchase(
        option = options[2],
        checked = uiState.executivePurchase,
        onCheckedChange = {viewModel.onEvent(SurveyEvent.ExecutivePurchase(it))}
    )
    Spacer(modifier = Modifier.height(16.dp))
    ProductLikeToPurchase(
        option = options[3],
        checked = uiState.executiveClickPurchase,
        onCheckedChange = {viewModel.onEvent(SurveyEvent.ExecutiveClickPurchase(it))}
    )
}


@Composable
fun Fourth(
    viewModel: SurveyViewModel = hiltViewModel()
) {

    val uiState by viewModel.surveyState.collectAsStateWithLifecycle()

    CircleAvaters(number = 4, text = "Additional Feedback")
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "What can we do to improve our service or products?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        OutlinedTextField(
            value = uiState.whatCanWeToImproveService,
            onValueChange = { viewModel.onEvent(SurveyEvent.WhatCanWeToImproveService(it)) },
            label = { Text("Enter your suggestions") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEEEEEE),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Text(
            text = "Would you recommend our products/services to others?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        DropDownMenu(
            options = listOf("Yes", "No"),
            selectedOption = uiState.whatWudYouRecommend,
            onOptionSelected = { viewModel.onEvent(SurveyEvent.WhatWudYouRecommend(it)) }
        )

        Text(
            text = "Any other comments or suggestions?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = uiState.comment,
            onValueChange = { viewModel.onEvent(SurveyEvent.Comment(it)) },
            label = { Text("Enter your suggestions") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEEEEEE),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp)
        )
    }
}

@Composable
fun Fifth(
    viewModel: SurveyViewModel = hiltViewModel()
) {
    val uiState by viewModel.surveyState.collectAsStateWithLifecycle()
    CircleAvaters(number = 5, text = "Product Availability on Sheff")
    val options = listOf("TARGET SUPER RV", "TARGET MENTHOL RV", "EXECUTIVE", "EXECUTIVE CLICK")

    ProductLikeToPurchase(
        option = options[0],
        checked = uiState.productAvailabilitySuper,
        onCheckedChange = {viewModel.onEvent(SurveyEvent.ProductAvailabilitySuper(it))}
    )
    Spacer(modifier = Modifier.height(16.dp))
    ProductLikeToPurchase(
        option = options[1],
        checked = uiState.productAvailabilityMenthol,
        onCheckedChange = {viewModel.onEvent(SurveyEvent.ProductAvailabilityMenthol(it))}
    )
    Spacer(modifier = Modifier.height(16.dp))
    ProductLikeToPurchase(
        option = options[2],
        checked = uiState.productAvailabilityExec,
        onCheckedChange = {viewModel.onEvent(SurveyEvent.ProductAvailabilityExec(it))}
    )
    Spacer(modifier = Modifier.height(16.dp))
    ProductLikeToPurchase(
        option = options[3],
        checked = uiState.productAvailabilityExecClick,
        onCheckedChange = {viewModel.onEvent(SurveyEvent.ProductAvailabilityExecClick(it))}
    )
}
@Composable
fun CircleAvaters(
    number: Int,
    text: String
) {
    Row(
        modifier = Modifier.padding(bottom = 8.dp, start = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NumberedCircle(number = number)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 18.sp,
            fontWeight = FontWeight.W500
        )
    }
}

@Composable
fun NumberedCircle(number: Int) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .background(mainGray, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number.toString(),
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {

    //find a way to save the state for configuration change
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    Box(modifier = Modifier.fillMaxWidth()) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .zIndex(1f)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }

        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp))
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = true }
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEEEEEE),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp)
        )
    }
}


@Composable
fun ProductEnter(
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
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Adds spacing between items
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
fun ProductLikeToPurchase(
    option: String,
    checked : String,
    onCheckedChange: (String)->Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = option,
            modifier = Modifier.weight(1f)
        )

        Checkbox(
            checked = checked == option,
            onCheckedChange = { isChecked ->
                val newValue = if (isChecked) option else ""
                onCheckedChange(newValue)
            }
        )
    }
}
