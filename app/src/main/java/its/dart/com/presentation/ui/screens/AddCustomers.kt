package its.dart.com.presentation.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material.icons.filled.MobileFriendly
import androidx.compose.material.icons.filled.TypeSpecimen
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import its.dart.com.presentation.ui.components.CButton
import its.dart.com.presentation.ui.components.CustomTextField
import its.dart.com.presentation.ui.components.DropdownLists
import its.dart.com.presentation.ui.components.OptionalDialog
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.AddCustomerViewModel
import its.dart.com.presentation.viewmodel.event.AddCustomerViewEvent

@Composable
fun AddCustomer(
    navController: NavHostController,
    userName: String,
    userId: String,
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
                fontSize = 22,
                fontFamily = robotoFamily,
                letterSpacing = -0.2,
                subTitleItem = "Add Customer",
            )
        }
    ) { innerPadding ->
        AddCustomerContent(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel
        )
    }
}

@Composable
fun AddCustomerContent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: AddCustomerViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val widgetUIState by viewModel.addCustomerState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
                .verticalScroll(scrollState)
                .imePadding()
        ) {

            Spacer(modifier = Modifier.height(30.dp))
            OutletName(
                label = widgetUIState.outletName,
                onValueChange = { viewModel.onEvent(AddCustomerViewEvent.OnOutletName(it)) }
            )
            Spacer(modifier = Modifier.height(10.dp))

            ContactPerson(
                label = widgetUIState.contactPerson,
                onValueChange = {viewModel.onEvent(AddCustomerViewEvent.OnContactPerson(it))}
            )
            Spacer(modifier = Modifier.height(10.dp))

            MobileNumber(
                label = widgetUIState.mobileNumber,
                onValueChange = {viewModel.onEvent(AddCustomerViewEvent.OnMobileNumber(it))}
            )

            Spacer(modifier = Modifier.height(10.dp))

            Language(
                selectedOption = widgetUIState.language,
                onOptionSelected = { viewModel.onEvent(AddCustomerViewEvent.OnLanguage(it)) }
            )
            Spacer(modifier = Modifier.height(15.dp))

            OutletType(
                selectedOption = widgetUIState.outletType,
                onOptionSelected = { viewModel.onEvent(AddCustomerViewEvent.OnOutletType(it)) }
            )

            Spacer(modifier = Modifier.height(15.dp))

            Address(
                label = widgetUIState.address,
                onValueChange = {viewModel.onEvent(AddCustomerViewEvent.OnAddress(it))}
            )
            Spacer(modifier = Modifier.height(10.dp))

            CButton(
                onClick = {viewModel.onEvent(AddCustomerViewEvent.OnclickConfirmButton)},
                buttonState = true,
                text = "Post"
            )

            Spacer(modifier = Modifier.height(30.dp))

            HideAndShowOptionalDialog(
                btDismissState = widgetUIState.dialogLoader,
                btDismissEvent = {viewModel.onEvent(AddCustomerViewEvent.OnclickDismissButton)},
                btConfirmEvent = {viewModel.onEvent(AddCustomerViewEvent.OnclickButton)}
            )

            ShowErrorToast(widgetUIState.errorMessage)
        }
    }
}

@Composable
fun ShowErrorToast(errorMessage: String?) {
    val context = LocalContext.current
    errorMessage?.let { message ->
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun HideAndShowOptionalDialog(
    btDismissState: Boolean,
    btDismissEvent:()->Unit,
    btConfirmEvent: () -> Unit,
    text: String =  "add 1 customer by posting to server?",
    btConfirmTitle:String  = "Post",
    btDismissTitle:String = "Cancel",
){
    OptionalDialog(
        text = text,
        btDismissEvent = {btDismissEvent()},
        btConfirmEvent = {btConfirmEvent()},
        btConfirmTitle = btConfirmTitle,
        btDismissTitle = btDismissTitle,
        btDismissState = btDismissState
    )
}

@Composable
fun OutletName(
    label: String,
    onValueChange:(String)->Unit
    ) {
    CustomTextField(
        label = "Outlet Name",
        value = label,
        onValueChange = onValueChange,
        leadingIcon = Icons.Default.Home
    )
}

@Composable
fun ContactPerson(
    label: String,
    onValueChange:(String)->Unit
) {
    CustomTextField(
        label = "Contact Person",
        value = label,
        onValueChange = onValueChange,
        leadingIcon = Icons.Default.Contacts
    )
}

@Composable
fun MobileNumber(
    label: String,
    onValueChange:(String)->Unit
) {
    CustomTextField(
        label = "Mobile Number",
        value = label,
        onValueChange = onValueChange,
        leadingIcon = Icons.Default.MobileFriendly
    )
}


@Composable
fun Language(
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit
) {

    val languageList = listOf(
        0 to "Select Language",
        1 to "Yoruba",
        2 to "Igbo",
        3 to "Hausa",
        4 to "English"
    )
    val options = languageList.map { it.second }
    val selectedLanguage = languageList.find { it.first == selectedOption }?.second ?: options.first()
    DropdownLists(
        options = options,
        selectedOption = selectedLanguage,
        onOptionSelected = { selectedLanguageName ->
            val selectedId = languageList.find { it.second == selectedLanguageName }?.first ?: 0
            onOptionSelected(selectedId)
        },
        label = "Language",
        leadingIcon = Icons.Default.Language
    )
}

@Composable
fun OutletType(
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit
) {
    val outletType = listOf(
        0 to "Select Language1",
        1 to "Grocery",
        2 to "Supermarket",
        3 to "Kiosk",
        4 to "Restaurant",
        5 to "Open Market",
        6 to "Table Top"
    )

    val options = outletType.map { it.second }
    val selectedOutletType = outletType.find { it.first == selectedOption }?.second ?: options.first()
    DropdownLists(
        options = options,
        selectedOption = selectedOutletType,
        onOptionSelected = { selectedOutletType ->
            val selectedId = outletType.find { it.second == selectedOutletType }?.first ?: 0
            onOptionSelected(selectedId)
        },
        label = "Outlet Type",
        leadingIcon = Icons.Default.TypeSpecimen
    )
}

@Composable
fun Address(
    label: String,
    onValueChange:(String)->Unit
) {
    CustomTextField(
        label = "Address",
        value = label,
        onValueChange = onValueChange ,
        leadingIcon = Icons.Default.LocationSearching
    )
}


