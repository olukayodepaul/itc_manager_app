package its.dart.com.presentation.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdfScanner
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.BusAlert
import androidx.compose.material.icons.filled.EmojiPeople
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.GifBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MobileFriendly
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.PeopleOutline
import androidx.compose.material.icons.filled.PestControlRodent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import its.dart.com.presentation.ui.components.CButton
import its.dart.com.presentation.ui.components.CustomTextField
import its.dart.com.presentation.ui.components.DropdownLists
import its.dart.com.presentation.ui.components.ProductCheckbox
import its.dart.com.presentation.ui.components.SuccessDialog
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.robotoFamily


@Composable
fun DailyConsumer(
    navController: NavHostController,
) {
    Scaffold(
        topBar = {
            ToolBar(
                title = "Daily Consumer Data Form",
                click = {navController.popBackStack()},
                clickSearch = {},
                clickMenu = {},
                navigation = true,
                fontSize = 20,
                fontFamily = robotoFamily,
                letterSpacing = 0.5,
            )
        }
    ) { innerPadding ->
        DailyConsumerContent(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun DailyConsumerContent(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {

            ConsumerName()
            Spacer(modifier = Modifier.height(10.dp))
            PhoneNumber()
            Spacer(modifier = Modifier.height(10.dp))
            Gender()
            Spacer(modifier = Modifier.height(10.dp))
            Age()
            Spacer(modifier = Modifier.height(20.dp))
            PersonBrand()
            Spacer(modifier = Modifier.height(20.dp))
            SampleSKU()
            Spacer(modifier = Modifier.height(20.dp))
            AnySales()
            Spacer(modifier = Modifier.height(20.dp))
            QtySold()
            Spacer(modifier = Modifier.height(40.dp))
            ConsumerFeedback()
            Spacer(modifier = Modifier.height(40.dp))
            CheckBoxFeed()
            Spacer(modifier = Modifier.height(30.dp))
            CButton(
                onClick = { showDialog = true },
                buttonState = true,
                text = "Post"
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
fun ConsumerName() {
    var outletName by remember { mutableStateOf("") }
    CustomTextField(
        label = "Consumer Name",
        value = outletName,
        onValueChange = { outletName = it },
        leadingIcon = Icons.Default.Home
    )
}

@Composable
fun PhoneNumber() {
    var mobileNumber by remember { mutableStateOf("") }
    CustomTextField(
        label = "Mobile Number",
        value = mobileNumber,
        onValueChange = { mobileNumber = it },
        leadingIcon = Icons.Default.MobileFriendly
    )
}


@Composable
fun Gender() {
    val options = listOf("Select", "Male", "Female",)
    var selectedOption by remember { mutableStateOf(options.first()) }
    DropdownLists(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = { selectedOption = it },
        label = "Gender",
        leadingIcon = Icons.Default.PeopleOutline
    )
}

@Composable
fun Age() {
    var outletName by remember { mutableStateOf("") }
    CustomTextField(
        label = "Age",
        value = outletName,
        onValueChange = { outletName = it },
        leadingIcon = Icons.Default.EmojiPeople
    )
}

@Composable
fun PersonBrand() {
    val options = listOf("Select", "B & H boost", "sterling blue", "B & H flavour",
        "B & H switch","pall mall excel", "bohem", "aspen" ,"B & H demi", "oris flavour", "pall mall filter","yes","oris slim"
        ,"time menthol","Royal standard","pall mall menthol","chesterfield","dorchester", "rothmans"
    )
    var selectedOption by remember { mutableStateOf(options.first()) }
    DropdownLists(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = { selectedOption = it },
        label = "Personal Brand",
        leadingIcon = Icons.Default.GifBox
    )
}

@Composable
fun SampleSKU() {
    var outletName by remember { mutableStateOf("") }
    CustomTextField(
        label = "Sample SKU",
        value = outletName,
        onValueChange = { outletName = it },
        leadingIcon = Icons.Default.Backpack
    )
}

@Composable
fun AnySales() {
    val options = listOf("Select", "Yes", "No",)
    var selectedOption by remember { mutableStateOf(options.first()) }
    DropdownLists(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = { selectedOption = it },
        label = "Any Sales",
        leadingIcon = Icons.Default.BusAlert
    )
}

@Composable
fun QtySold() {
    var outletName by remember { mutableStateOf("") }
    CustomTextField(
        label = "Qty Sold",
        value = outletName,
        onValueChange = { outletName = it },
        leadingIcon = Icons.Default.Money
    )
}

@Composable
fun Pen() {
    val options = listOf("Select", "TH", "PEN","WB")
    var selectedOption by remember { mutableStateOf(options.first()) }
    DropdownLists(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = { selectedOption = it },
        label = "PMS",
        leadingIcon = Icons.Default.AdfScanner
    )
}

@Composable
fun ConsumerFeedback() {
    var outletName by remember { mutableStateOf("") }
    CustomTextField(
        label = "Consumer Feedback",
        value = outletName,
        onValueChange = { outletName = it },
        leadingIcon = Icons.Default.Feedback
    )
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