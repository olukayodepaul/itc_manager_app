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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import its.dart.com.presentation.ui.components.CButton
import its.dart.com.presentation.ui.components.CustomTextFields
import its.dart.com.presentation.ui.components.DropdownLists
import its.dart.com.presentation.ui.components.DropdownListsNoneIcon
import its.dart.com.presentation.ui.components.SuccessDialog
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.robotoFamily


@Composable
fun PackPlacement(
    navController: NavHostController,
    userId: String,
    userName: String,
    identifier: String,
) {

    Scaffold(
        topBar = {
            ToolBar(
                title = userName,
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
            navController = navController
        )
    }
}


@Composable
fun PackPlacementContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
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
            Spacer(modifier = Modifier.height(10.dp))
            Handler()
            Spacer(modifier = Modifier.height(10.dp))
            Tgtsup()
            Spacer(modifier = Modifier.height(10.dp))
            Tgtmtl()
            Spacer(modifier = Modifier.height(10.dp))
            exec()
            Spacer(modifier = Modifier.height(10.dp))
            QtyBought()
            Spacer(modifier = Modifier.height(10.dp))
            BikeSales()
            Spacer(modifier = Modifier.height(10.dp))
            SalesManName()
            Spacer(modifier = Modifier.height(10.dp))
            CButton(
                onClick = { showDialog = true },
                buttonState = true,
                text = "POST"
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
    fun     PosId() {
    var outletName by remember { mutableStateOf("") }
    CustomTextFields(
        label = "Outlet PosId",
        value = outletName,
        onValueChange = { outletName = it }
    )
}

@Composable
fun Handler() {
    val options = listOf("Select", "Yes", "No",)
    var selectedOption by remember { mutableStateOf(options.first()) }
    DropdownListsNoneIcon(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = { selectedOption = it },
        label = "Personal Brand"
    )
}

@Composable
fun     Tgtsup() {
    var outletName by remember { mutableStateOf("") }
    CustomTextFields(
        label = "TGT SUP",
        value = outletName,
        onValueChange = { outletName = it }
    )
}

@Composable
fun     Tgtmtl() {
    var outletName by remember { mutableStateOf("") }
    CustomTextFields(
        label = "TGT MTL",
        value = outletName,
        onValueChange = { outletName = it }
    )
}

@Composable
fun     exec() {
    var outletName by remember { mutableStateOf("") }
    CustomTextFields(
        label = "EXC",
        value = outletName,
        onValueChange = { outletName = it }
    )
}

@Composable
fun     QtyBought() {
    var outletName by remember { mutableStateOf("") }
    CustomTextFields(
        label = "Qty Bought",
        value = outletName,
        onValueChange = { outletName = it }
    )
}

@Composable
fun BikeSales() {
    val options = listOf("Select", "Yes", "No",)
    var selectedOption by remember { mutableStateOf(options.first()) }
    DropdownListsNoneIcon(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = { selectedOption = it },
        label = "Bike Sales"
    )
}

@Composable
fun     SalesManName() {
    var outletName by remember { mutableStateOf("") }
    CustomTextFields(
        label = "Sales Man's Name",
        value = outletName,
        onValueChange = { outletName = it }
    )
}

