package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Shop
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.appColorWhite

@Composable
fun MainScreen(
    navController: NavHostController
) {
//    // Define the tab items
//    val tabItems = remember {
//        listOf(
//            TabItem(
//                title = "Attendant",
//                unselectedIcon = Icons.Outlined.AccessTime,
//                selectedIcon = Icons.Filled.AccessTimeFilled,
//                content = { AttendantScreen() }
//            ),
//            TabItem(
//                title = "Sales",
//                unselectedIcon = Icons.Outlined.Shop,
//                selectedIcon = Icons.Filled.Shop,
//                content = { SalesRepScreen() }
//            ),
//            TabItem(
//                title = "Order",
//                unselectedIcon = Icons.Outlined.ShoppingCart,
//                selectedIcon = Icons.Filled.ShoppingCart,
//                content = { CustomersScreen() }
//            ),
//        )
//    }
//
//    // Track selected tab
//    var selectedTabIndex by remember { mutableStateOf(0) }
//
//    // Scaffold layout for top content and bottom navigation
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        bottomBar = {
//            // Bottom Navigation stays fixed at the bottom
//            NavigationBar(
//                containerColor = appColorWhite
//            ) {
//                tabItems.forEachIndexed { index, item ->
//                    NavigationBarItem(
//                        selected = index == selectedTabIndex,
//                        onClick = {
//                            selectedTabIndex = index // Directly update the selected tab index
//                        },
//                        icon = {
//                            Icon(
//                                imageVector = if (index == selectedTabIndex) item.selectedIcon else item.unselectedIcon,
//                                contentDescription = item.title,
//                                tint = appColorBlack
//                            )
//                        },
//                        label = {
//                            Text(
//                                text = item.title,
//                                style = TextStyle(
//                                    fontWeight = if (index == selectedTabIndex) FontWeight.Bold else FontWeight.Normal,
//                                    color = Color(0xFF008b00),
//                                )
//                            )
//                        },
//                    )
//                }
//            }
//        }
//    ) { paddingValues ->
//        // Main content above the bottom navigation
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues) // To account for the bottom bar space
//        ) {
//            // Display content based on the current tab
//            tabItems[selectedTabIndex].content()
//        }
//    }
}

//data class TabItem(
//    val title: String,
//    val unselectedIcon: ImageVector,
//    val selectedIcon: ImageVector,
//    val content: @Composable () -> Unit
//)

