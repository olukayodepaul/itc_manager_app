package its.dart.com.presentation.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.Diversity3
import androidx.compose.material.icons.filled.Reorder
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBar (
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    data object SalesRep : BottomBar(
        route = "Reps",
        title = "Reps",
        icon = Icons.Default.Diversity3
    )

    data object Attendant : BottomBar(
        route = "Attendant",
        title = "Attendant",
        icon = Icons.Default.AccessTimeFilled
    )

    data object Customers : BottomBar(
        route = "Customers",
        title = "Customers",
        icon = Icons.Default.Reorder
    )

}