package its.dart.com.presentation.ui.screens


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import its.dart.com.data.repository.local.entity.MerchantEntity
import its.dart.com.presentation.ui.components.CircleAvatar
import its.dart.com.presentation.ui.components.MerchantDropdownMenu
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.appColor
import its.dart.com.presentation.ui.theme.appColorWhite
import its.dart.com.presentation.ui.theme.mainGray
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.AddCustomerViewModel

@Composable
fun WholeSellerScreen(
    navController: NavHostController,
    viewModel: AddCustomerViewModel = hiltViewModel(),
) {

    //use the share preference
    val widgetUIState by viewModel.merchantState.collectAsStateWithLifecycle()
    val userName by viewModel.userName.collectAsStateWithLifecycle()
    val userId by viewModel.userId.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchMerchantCustomers()
    }

    Scaffold(
        topBar = {
            ToolBar(
                title ="$userName",
                click = {},
                clickSearch = {},
                clickMenu = {},
                fontSize = 22,
                subTitle = true,
                fontFamily = robotoFamily,
                letterSpacing = -0.2,
                subTitleItem = "Merchant"
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("add_customer/$userId/${userName}")
                },
                containerColor = mainGray,
                shape = CircleShape,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = appColorWhite,
                    contentDescription = null
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                state = rememberLazyListState()
            ) {
                items(
                    items = widgetUIState,
                    key = {it.id}
                ){itemList->
                    MerchantContentList(
                        customers = itemList,
                        navController = navController
                    )
                }
            }
        }
    }
}


@Composable
fun MerchantContentList(
    customers: MerchantEntity,
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
                initialName = customers.outlet_name.uppercase(),
                id = customers.id
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = customers.outlet_name.capitalize(),
                    fontWeight = FontWeight(400),
                    maxLines = 1,
                    fontSize = 18.sp,
                    color = appColor,
                )
                Text(
                    text = "${customers.outlet_address} ${customers.contact_phone}",
                    maxLines = 1,
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
            }
            MerchantDropdownMenu(navController, customers)
        }
    }
}