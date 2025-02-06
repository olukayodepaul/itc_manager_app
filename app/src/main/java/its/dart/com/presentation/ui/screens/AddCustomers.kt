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
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material.icons.filled.MobileFriendly
import androidx.compose.material.icons.filled.Outlet
import androidx.compose.material.icons.filled.TypeSpecimen
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import its.dart.com.presentation.ui.components.CButton
import its.dart.com.presentation.ui.components.CustomTextField
import its.dart.com.presentation.ui.components.DropdownLists
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.AddCustomerViewModel

@Composable
fun AddCustomer(
    navController: NavHostController,
    userName: String,
    viewModel: AddCustomerViewModel = hiltViewModel()
) {
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
                subTitleItem = "Add Customer"
            )
        }
    ) { innerPadding ->
        AddCustomerContent(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun AddCustomerContent(
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

            OutletName()
            Spacer(modifier = Modifier.height(10.dp))
            ContactPerson()
            Spacer(modifier = Modifier.height(10.dp))
            MobileNumber()
            Spacer(modifier = Modifier.height(10.dp))
            Language()
            Spacer(modifier = Modifier.height(10.dp))
            OutletType()
            Spacer(modifier = Modifier.height(20.dp))
            OutletClass()
            Spacer(modifier = Modifier.height(20.dp))
            Address()
            Spacer(modifier = Modifier.height(10.dp))
            CButton(
                onClick = { showDialog = true },
                buttonState = true,
                text = "Register"
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
fun SuccessDialog(
    showDialog: Boolean,
    onOkClick: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("Success") },
            text = { Text("Your submission was successful!") },
            confirmButton = {
                Button(onClick = onOkClick) {
                    Text("OK")
                }
            }
        )
    }
}

@Composable
fun OutletName() {
    var outletName by remember { mutableStateOf("") }
    CustomTextField(
        label = "Outlet Name",
        value = outletName,
        onValueChange = { outletName = it },
        leadingIcon = Icons.Default.Home
    )
}

@Composable
fun ContactPerson() {
    var contactPerson by remember { mutableStateOf("") }
    CustomTextField(
        label = "Contact Person",
        value = contactPerson,
        onValueChange = { contactPerson = it },
        leadingIcon = Icons.Default.Contacts
    )
}

@Composable
fun MobileNumber() {
    var mobileNumber by remember { mutableStateOf("") }
    CustomTextField(
        label = "Mobile Number",
        value = mobileNumber,
        onValueChange = { mobileNumber = it },
        leadingIcon = Icons.Default.MobileFriendly
    )
}


@Composable
fun Language() {
    val options = listOf("Select", "Option 2", "Option 3", "Option 4")
    var selectedOption by remember { mutableStateOf(options.first()) }
    DropdownLists(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = { selectedOption = it },
        label = "Language",
        leadingIcon = Icons.Default.Language
    )
}

@Composable
fun OutletType() {
    val options = listOf("Select", "Option 2", "Option 3", "Option 4")
    var selectedOption by remember { mutableStateOf(options.first()) }
    DropdownLists(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = { selectedOption = it },
        label = "Outlet Type",
        leadingIcon = Icons.Default.TypeSpecimen
    )
}

@Composable
fun OutletClass() {
    val options = listOf("Select", "Option 2", "Option 3", "Option 4")
    var selectedOption by remember { mutableStateOf(options.first()) }
    DropdownLists(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = { selectedOption = it },
        label = "Outlet Class",
        leadingIcon = Icons.Default.TypeSpecimen
    )
}

@Composable
fun Address() {
    var outletAddress by remember { mutableStateOf("") }
    CustomTextField(
        label = "Address",
        value = outletAddress,
        onValueChange = { outletAddress = it },
        leadingIcon = Icons.Default.LocationSearching
    )
}

@Composable
@Preview(showBackground = true)
fun AddCustomerPreview() {
    val navController = rememberNavController()
    AddCustomer(
        navController = navController,
        userName = "userName"
    )
}


