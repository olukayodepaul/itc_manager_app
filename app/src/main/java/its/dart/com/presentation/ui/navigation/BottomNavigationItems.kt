package its.dart.com.presentation.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CardGiftcard
import androidx.compose.material.icons.outlined.Shop
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import its.dart.com.presentation.ui.screens.AttendantScreen
import its.dart.com.presentation.ui.screens.Promo
import its.dart.com.presentation.ui.screens.SalesRepScreen
import its.dart.com.presentation.ui.screens.WholeSellerScreen

// Data class for tab items
data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val content: @Composable () -> Unit
)

// Function to return tab items based on category
fun getTabItems(navController: NavHostController, category: Int): List<TabItem> {
    return when (category) {
        4 -> listOf(
            TabItem(
                title = "Attendance",
                unselectedIcon = Icons.Outlined.AccessTime,
                selectedIcon = Icons.Filled.AccessTimeFilled,
                content = { AttendantScreen() }
            ),
            TabItem(
                title = "Supervisor",
                unselectedIcon = Icons.Outlined.Shop,
                selectedIcon = Icons.Filled.Shop,
                content = { SalesRepScreen(navController) }
            ),
            TabItem(
                title = "Promoter",
                unselectedIcon = Icons.Outlined.CardGiftcard,
                selectedIcon = Icons.Filled.CardGiftcard,
                content = { /* TODO: Implement Promoter Screen */ }
            ),
            TabItem(
                title = "Merchant",
                unselectedIcon = Icons.Outlined.ShoppingCart,
                selectedIcon = Icons.Filled.ShoppingCart,
                content = { /* TODO: Implement Merchant Screen */ }
            )
        )

        5 -> listOf(
            TabItem(
                title = "Attendance",
                unselectedIcon = Icons.Outlined.AccessTime,
                selectedIcon = Icons.Filled.AccessTimeFilled,
                content = { AttendantScreen() }
            ),
            TabItem(
                title = "Supervisor",
                unselectedIcon = Icons.Outlined.Shop,
                selectedIcon = Icons.Filled.Shop,
                content = { /* TODO: Implement Supervisor Screen */ }
            ),
            TabItem(
                title = "Promoter",
                unselectedIcon = Icons.Outlined.CardGiftcard,
                selectedIcon = Icons.Filled.CardGiftcard,
                content = { Promo(navController) }
            ),
            TabItem(
                title = "Merchant",
                unselectedIcon = Icons.Outlined.ShoppingCart,
                selectedIcon = Icons.Filled.ShoppingCart,
                content = { /* TODO: Implement Merchant Screen */ }
            )
        )

        6 -> listOf(
            TabItem(
                title = "Attendance",
                unselectedIcon = Icons.Outlined.AccessTime,
                selectedIcon = Icons.Filled.AccessTimeFilled,
                content = { AttendantScreen() }
            ),
            TabItem(
                title = "Supervisor",
                unselectedIcon = Icons.Outlined.Shop,
                selectedIcon = Icons.Filled.Shop,
                content = { /* TODO: Implement Supervisor Screen */ }
            ),
            TabItem(
                title = "Promoter",
                unselectedIcon = Icons.Outlined.CardGiftcard,
                selectedIcon = Icons.Filled.CardGiftcard,
                content = { /* TODO: Implement Promoter Screen */ }
            ),
            TabItem(
                title = "Merchant",
                unselectedIcon = Icons.Outlined.ShoppingCart,
                selectedIcon = Icons.Filled.ShoppingCart,
                content = { WholeSellerScreen(navController) }
            )
        )

        else -> emptyList() // Handle unexpected category values
    }
}
