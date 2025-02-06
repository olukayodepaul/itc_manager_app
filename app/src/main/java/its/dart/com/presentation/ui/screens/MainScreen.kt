package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Shop
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import its.dart.com.presentation.ui.theme.appColorBlack


@Composable
fun MainScreen(navController: NavHostController) {
    val tabItems = remember {
        listOf(
            TabItem(
                title = "Attendant",
                unselectedIcon = Icons.Outlined.AccessTime,
                selectedIcon = Icons.Filled.AccessTimeFilled,
                content = { AttendantScreen() }
            ),
            TabItem(
                title = "Sales",
                unselectedIcon = Icons.Outlined.Shop,
                selectedIcon = Icons.Filled.Shop,
                content = { SalesRepScreen(navController) }
            ),
            TabItem(
                title = "Order",
                unselectedIcon = Icons.Outlined.ShoppingCart,
                selectedIcon = Icons.Filled.ShoppingCart,
                content = { WholeSellerScreen(navController) }
            ),
        )
    }

    // Persist selected tab even after navigating away
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(containerColor = Color(0xFFF7F8F9)) {
                tabItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedTabIndex,
                        onClick = { selectedTabIndex = index }, // Keeps selection
                        icon = {
                            Icon(
                                imageVector = if (index == selectedTabIndex) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.title,
                                tint = appColorBlack
                            )
                        },
                        label = {
                            Text(
                                text = item.title,
                                style = TextStyle(
                                    fontWeight = if (index == selectedTabIndex) FontWeight.Bold else FontWeight.Normal,
                                    color = Color(0xFF008b00),
                                )
                            )
                        },
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            tabItems[selectedTabIndex].content()
        }
    }
}



data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val content: @Composable () -> Unit
)

