package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import its.dart.com.domain.repository.remote.model.RepsModel
import its.dart.com.presentation.ui.components.CircleAvatar
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.appColor
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.SalesRepViewModel

@Composable
fun SalesRepScreen(
    navController: NavHostController,
    viewModel: SalesRepViewModel = hiltViewModel()
) {
    val salesReps by viewModel.salesReps.collectAsState(initial = emptyList())
    val username by viewModel.userNameState.collectAsStateWithLifecycle()


    Scaffold(
        topBar = {
            ToolBar(
                title = "Supervisor",
                click = {},
                clickSearch = {},
                clickMenu = {},
                fontSize = 22,
                fontFamily = robotoFamily,
                letterSpacing = -0.2,
                subTitle = true,
                subTitleItem = "$username"
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFFFFFFFF))
        ) {


            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                state = rememberLazyListState()
            ) {
                items(
                    items = salesReps,
                    key = { it.id }
                ) { salesRep ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("CustomersScreen/${salesRep.id}/${salesRep.fullName}/${salesRep.routeName}")
                            }
                    ) {
                        Content(
                            salesRep = salesRep
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Content(
    salesRep: RepsModel
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleAvatar(
            initialName = salesRep.fullName,
            id = salesRep.id
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        ) {
            Text(
                text = salesRep.fullName,
                fontWeight = FontWeight(400),
                maxLines = 1,
                fontSize = 18.sp,
                color = appColor,
            )
            Text(
                text = "${salesRep.time ?: "00:00:00"} ${salesRep.routeName} ${salesRep.routeId}",
                maxLines = 1,
                fontSize = 14.sp,
                color = Color.Gray,
            )
        }

        Box(
            modifier = Modifier
                .wrapContentSize(Alignment.TopEnd)
        ) {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.ArrowForwardIos,
                    tint = Color.Gray.copy(alpha = 1f),
                    contentDescription = "More options",
                    modifier = Modifier.size(20.dp)
                )
            }
        }

    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    )
}
