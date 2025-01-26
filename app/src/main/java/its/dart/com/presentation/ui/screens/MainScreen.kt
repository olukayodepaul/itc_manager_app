package its.dart.com.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    navController: NavHostController
) {
    // Define the tab items
    val tabItems = listOf(
        TabItem(
            title = "Login",
            unselectedIcon = Icons.Outlined.Lock,
            selectedIcon = Icons.Filled.Lock,
            content = { AttendantScreen() }
        ),
        TabItem(
            title = "Main",
            unselectedIcon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home,
            content = { SalesRepScreen() }
        ),
        TabItem(
            title = "Change Password",
            unselectedIcon = Icons.Outlined.AccountCircle,
            selectedIcon = Icons.Filled.AccountCircle,
            content = { CustomersScreen() }
        ),
    )

    // Track selected tab
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val pagerState = rememberPagerState {
        tabItems.size
    }

    // Handle tab index change
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }

    // Scaffold layout for top content and bottom navigation
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            // Bottom Navigation stays fixed at the bottom
            NavigationBar {
                tabItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedTabIndex) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        label = {
                            Text(text = item.title)
                        }
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
            // Horizontal pager to swap between screens
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Ensure pager takes up all available space above the bottom bar
            ) { index ->
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
