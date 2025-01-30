package its.dart.com.presentation.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.BookOnline
import androidx.compose.material.icons.outlined.LocationSearching
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import its.dart.com.domain.repository.remote.model.AllCustomersModel
import its.dart.com.presentation.ui.theme.appColor
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.appColorWhite
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.CustomerByRepViewModel
import its.dart.com.presentation.viewmodel.state.CustomerRepState



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
            ToolBar(onBackClick = { navController.popBackStack() })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            when (val state = customersState) {
                is CustomerRepState.Loading -> LoadingIndicator()
                is CustomerRepState.Empty -> Text("No customers available.")
                is CustomerRepState.Success -> CustomerList(state.data)
                is CustomerRepState.Failure -> {
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
fun CustomerList(customers: List<AllCustomersModel>) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(customers) { customer ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                CustomersCard(
                    details = customer
                )
            }
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
fun Failure(
    errorMessage: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Error,
            contentDescription = "Error",
            tint = Color.Red,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Failed to load customers: $errorMessage",
            color = Color.Red,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onRetry,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Retry")
        }
    }
}



@Composable
fun CustomersCard(
    details: AllCustomersModel,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = details.outletName,
                fontWeight = FontWeight(500),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = 17.sp,
                color = appColor,
                fontFamily = robotoFamily
            )
            Text(
                text = "${details.outletAddress} ${details.urno}",
                maxLines = 1,
                fontSize = 12.sp,
                color = Color.Gray,
                fontFamily = robotoFamily
            )
        }
        DropdownMenuWithDetails()
    }
}


@Composable
fun DropdownMenuWithDetails() {

    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentSize()
        ) {
            DropdownMenuItem(
                text = { Text("Location Finder") },
                leadingIcon = { Icon(Icons.Outlined.LocationSearching, contentDescription = null) },
                onClick = { expanded = false }
            )
            DropdownMenuItem(
                text = { Text("Survey") },
                leadingIcon = { Icon(Icons.Outlined.AutoStories, contentDescription = null) },
                onClick = { expanded = false }
            )
            DropdownMenuItem(
                text = { Text("Order") },
                leadingIcon = { Icon(Icons.Outlined.ShoppingCart, contentDescription = null) },
                onClick = { expanded = false }
            )
            DropdownMenuItem(
                text = { Text("Competition") },
                leadingIcon = { Icon(Icons.Outlined.BookOnline, contentDescription = null) },
                onClick = { expanded = false }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(
    onBackClick: () -> Unit)
{
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = appColorBlack,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White,
        ),
        title = {
            Text(text = "Sales Person", fontWeight = FontWeight.Medium)
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: Implement Search */ }) {
                Icon(Icons.Filled.Search, contentDescription = "Search", tint = appColorWhite)
            }
            IconButton(onClick = { /* TODO: Handle click */ }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More Options")
            }
        }
    )
}
