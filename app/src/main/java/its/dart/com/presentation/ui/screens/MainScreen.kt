package its.dart.com.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.DirectionsBike
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.appColorWhite

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    navController: NavHostController
) {
    // Define the tab items
    val tabItems = remember {
        listOf(
            TabItem(
                title = "Chats",
                unselectedIcon = Icons.Outlined.AccessTime,
                selectedIcon = Icons.Filled.AccessTimeFilled,
                content = { AttendantScreen() }
            ),
            TabItem(
                title = "Sales",
                unselectedIcon = Icons.Outlined.DirectionsBike,
                selectedIcon = Icons.Filled.DirectionsBike,
                content = { SalesRepScreen() }
            ),
            TabItem(
                title = "Customers",
                unselectedIcon = Icons.Outlined.Person,
                selectedIcon = Icons.Filled.Person,
                content = { CustomersScreen() }
            ),
        )
    }

    // Track selected tab
    var selectedTabIndex by remember { mutableStateOf(0) }

    // Remember pager state
    val pagerState = rememberPagerState(
        initialPage = selectedTabIndex,
        pageCount = { tabItems.size }
    )

    // Sync the pager state with selected tab index when clicking on tab
    LaunchedEffect(selectedTabIndex) {
        pagerState.scrollToPage(selectedTabIndex) // Scroll to the selected page without animation
    }

    // Sync the selectedTabIndex when the page changes
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage // Sync selectedTabIndex when the page is swiped
    }

    // Scaffold layout for top content and bottom navigation
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            // Bottom Navigation stays fixed at the bottom
            NavigationBar(
                containerColor = appColorWhite
            ) {
                tabItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index // Directly update the selected tab index
                        },
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
                                    fontWeight = if (index == selectedTabIndex) FontWeight(1000) else FontWeight(800),
                                    color = Color(0xFF008b00),
                                )
                            )
                        },
                    )
                }
            }
        }
    ) { paddingValues ->
        // Main content above the bottom navigation
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // To account for the bottom bar space
        ) {
            // Horizontal pager for swiping
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // Ensure pager takes up all available space above the bottom bar
            ) { index ->
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    // Display content based on the current page (tab)
                    tabItems[index].content()
                }
            }
        }
    }
}

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val content: @Composable () -> Unit
)

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun AppPreview() {
    val navController = rememberNavController()
    MainScreen(navController)
}
