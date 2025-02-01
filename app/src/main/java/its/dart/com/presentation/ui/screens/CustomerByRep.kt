package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.BookOnline
import androidx.compose.material.icons.outlined.LocationSearching
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import its.dart.com.domain.repository.remote.model.AllCustomersModel
import its.dart.com.domain.repository.remote.model.RepsModel
import its.dart.com.presentation.ui.components.CircleAvatar
import its.dart.com.presentation.ui.components.DropdownMenuWithDetails
import its.dart.com.presentation.ui.components.Failure
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.appColor
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.dartSansFontFamily
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
                title = "Kenneth",
                click = {},
                clickSearch = {},
                clickMenu = {},
                navigation = true,
                fontSize = 20,
                searchIcon =  true,
                fontFamily = robotoFamily,
                letterSpacing = 0.5
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
fun LoadingIndicator() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
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
                id = salesRep.id // Pass the unique ID
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