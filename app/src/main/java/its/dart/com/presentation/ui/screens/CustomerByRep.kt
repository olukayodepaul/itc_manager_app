package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import its.dart.com.domain.repository.remote.model.AllCustomersModel
import its.dart.com.presentation.ui.components.CircleAvatar
import its.dart.com.presentation.ui.components.DropdownMenuWithDetails
import its.dart.com.presentation.ui.components.Failure
import its.dart.com.presentation.ui.components.LoadingIndicator
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.appColor
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.CustomerByRepViewModel
import its.dart.com.presentation.viewmodel.state.GenericState

@Composable
fun CustomerByRep(
    navController: NavHostController,
    userId: String,
    userName: String,
    viewModel: CustomerByRepViewModel = hiltViewModel()
) {
    val customersState by viewModel.customersState.collectAsState()

    LaunchedEffect(userId) {
        viewModel.getCustomers(userId)
    }

    Scaffold(
        topBar = {
            ToolBar(
                title = "${userName}",
                click = {
                    navController.popBackStack()
                },
                clickSearch = {},
                clickMenu = {},
                navigation = true,
                fontSize = 20,
                fontFamily = robotoFamily,
                letterSpacing = 0.5,
                subTitleItem = "Customers List",
                subTitle = true,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
        ) {
            when (val state = customersState) {
                is GenericState.Loading -> LoadingIndicator()
                is GenericState.Success -> CustomerList(state.data, navController)
                is GenericState.Failure -> {
                    Failure(
                        errorMessage = state.exception.message ?: "Unknown error",
                        onRetry = { }
                    )
                }
            }
        }
    }
}

@Composable
fun CustomerList(
    customers: List<AllCustomersModel>,
    navController: NavHostController
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = rememberLazyListState()
    ) {
        items(
            items = customers,
            key = { it.id }
        ) { customer ->
            ContentList(customer, navController)
        }
    }
}

@Composable
fun ContentList(
    salesRep: AllCustomersModel,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircleAvatar(
                initialName = salesRep.outletName,
                id = salesRep.id
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = salesRep.outletName,
                    fontWeight = FontWeight(400),
                    maxLines = 1,
                    fontSize = 18.sp,
                    color = appColor,
                )
                Text(
                    text = "${salesRep.outletAddress} ${salesRep.contactPhone}",
                    maxLines = 1,
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
            }
            DropdownMenuWithDetails(navController, salesRep)
        }
    }
}