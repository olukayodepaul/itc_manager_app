package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import its.dart.com.domain.repository.remote.model.RepsModel
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.appColor
import its.dart.com.presentation.ui.theme.dartFontFamily
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.SalesRepViewModel


@Composable
fun  SalesRepScreen(
    navController: NavHostController,
    viewModel: SalesRepViewModel = hiltViewModel()
) {
    val salesReps = viewModel.salesReps.collectAsState().value

    Scaffold(
        topBar = {
            ToolBar(
                title = "SapApp",
                click = {navController.popBackStack() },
                clickSearch = {},
                clickMenu = {},
                fontSize = 22,
                fontWeight = 900,
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(items = salesReps)
                { salesRep ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("CustomersScreen/${salesRep.id}/${salesRep.fullName}")
                            } // Handle item click
                            .padding(16.dp)
                    ) {
                        ContactListItem(
                            salesRep
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun ContactListItem(
    details: RepsModel
) {
    val actionTaken = 1
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(50.dp) // Circle size
                .clip(CircleShape) // Make the container circular
                .border(
                    width = 3.dp, // Border thickness
                    color = if (actionTaken == 1) Color.Gray else Color.Transparent, // Green border if there’s a new post
                    shape = CircleShape // Make the border circular
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "${details.fullName.firstOrNull()}",
                color = appColor,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 25.sp,
                fontFamily = dartFontFamily
            )
        }

        Spacer(modifier = Modifier.width(3.dp))

        Column(modifier = Modifier.weight(1f). padding(start = 10.dp)) {
            Text(
                text = details.fullName,
                fontWeight = FontWeight(500),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = 17.sp,
                color = appColor,
                fontFamily = robotoFamily
            )
            Text(
                text = "${details.routeName} ${details.staffCode}",
                maxLines = 1,
                fontSize = 12.sp,
                color = Color.Gray,
                fontFamily = robotoFamily
            )
        }

        // Time (e.g., last message time)
        Text(
            text = "12:01",
            color = Color.Gray,
            fontFamily = robotoFamily,
            fontWeight = FontWeight(600),
            fontSize = 12.sp,

        )
    }
}

