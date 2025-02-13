package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdfScanner
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import its.dart.com.presentation.ui.components.CButton
import its.dart.com.presentation.ui.components.CustomTextFields
import its.dart.com.presentation.ui.components.DropdownLists
import its.dart.com.presentation.ui.components.DropdownListsNoneIcon
import its.dart.com.presentation.ui.components.ProductCheckbox
import its.dart.com.presentation.ui.components.SuccessDialog
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.robotoFamily

@Composable
fun DailyActivityForm(
    navController: NavHostController,
) {
    Scaffold(
        topBar = {
            ToolBar(
                title = "Daily Retail Activity Form",
                click = { navController.popBackStack() },
                clickSearch = {},
                clickMenu = {},
                navigation = true,
                fontSize = 20,
                fontFamily = robotoFamily,
                letterSpacing = 0.5,
            )
        }
    ) { innerPadding ->
        StockSalesForm(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun StockSalesForm(
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


            Row(
                modifier = Modifier.padding(bottom = 8.dp, start = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberedCircle(number = 1)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "General Information",
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500
                )
            }
            AddressDetails()
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.padding(bottom = 8.dp, start = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberedCircle(number = 2)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Stock Out",
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500
                )
            }

            TargetSup()
            Spacer(modifier = Modifier.height(10.dp))
            TargetMtl()
            Spacer(modifier = Modifier.height(10.dp))
            TargetEXCKS()
            Spacer(modifier = Modifier.height(10.dp))
            TargetEXCCK()
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.padding(bottom = 8.dp, start = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberedCircle(number = 3)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Reward",
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500
                )
            }

            TargetSup()
            Spacer(modifier = Modifier.height(10.dp))
            TargetMtl()
            Spacer(modifier = Modifier.height(10.dp))
            TargetEXCKS()
            Spacer(modifier = Modifier.height(10.dp))
            TargetEXCCK()

            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.padding(bottom = 8.dp, start = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberedCircle(number = 4)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Sampling",
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500
                )
            }

            SamplingEXCKS()
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.height(10.dp))
            SamplingXCCK()
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.padding(bottom = 8.dp, start = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberedCircle(number = 5)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Others",
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500
                )
            }

            ITCSalesMen()
            Spacer(modifier = Modifier.height(20.dp))

            CButton(
                onClick = { showDialog = true },
                buttonState = true,
                text = "Submit"
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
fun AddressDetails() {
    var streetName by remember { mutableStateOf("") }
    var localGov by remember { mutableStateOf("") }
    var landMark by remember { mutableStateOf("") }
    CustomTextFields(label = "Customer Name/PosID", value = streetName, onValueChange = { streetName = it })
    CustomTextFields(label = "Phone", value = localGov, onValueChange = { localGov = it })
    CustomTextFields(label = "Address", value = landMark, onValueChange = { landMark = it })
}

@Composable
fun TargetSup() {
    var tgtSup by remember { mutableStateOf("") }
    CustomTextFields(label = "TGT SUPp", value = tgtSup, onValueChange = { tgtSup = it })
}

@Composable
fun TargetMtl() {
    var tgtMtl by remember { mutableStateOf("") }
    CustomTextFields(label = "TGT MTL", value = tgtMtl, onValueChange = { tgtMtl = it })
}

@Composable
fun TargetEXCKS() {
    var tgtMtl by remember { mutableStateOf("") }
    CustomTextFields(label = "EXC KS", value = tgtMtl, onValueChange = { tgtMtl = it })
}

@Composable
fun TargetEXCCK() {
    var tgtMtl by remember { mutableStateOf("") }
    CustomTextFields(label = "EXC CK", value = tgtMtl, onValueChange = { tgtMtl = it })
}

@Composable
fun SamplingEXCKS() {
    val options = listOf("Select", "STICKS", "LIKE","DISLIKE")
    var selectedOption by remember { mutableStateOf(options.first()) }
    DropdownListsNoneIcon(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = { selectedOption = it },
        label = "SAMPLING EXC KS"
    )
}

@Composable
fun SamplingXCCK() {
    val options = listOf("Select", "STICKS", "LIKE","DISLIKE")
    var selectedOption by remember { mutableStateOf(options.first()) }
    DropdownListsNoneIcon(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = { selectedOption = it },
        label = "SAMPLING XC CK "
    )
}

@Composable
fun ITCSalesMen() {
    val options = listOf("Select", "YES", "No")
    var selectedOption by remember { mutableStateOf(options.first()) }
    DropdownListsNoneIcon(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = { selectedOption = it },
        label = "ITC SALESMAN??"
    )
}

