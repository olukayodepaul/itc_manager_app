package its.dart.com.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.BookOnline
import androidx.compose.material.icons.outlined.LocationSearching
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import its.dart.com.domain.repository.remote.model.AllCustomersModel


@Composable
fun DropdownMenuWithDetails(
    navController: NavHostController,
    details: AllCustomersModel
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "More options",
                modifier = Modifier.size(20.dp)
            )
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
                onClick = {
                    expanded = false
                    navController.navigate("survey/${"ts"}/${"dd"}")
                }
            )
            DropdownMenuItem(
                text = { Text("Order") },
                leadingIcon = { Icon(Icons.Outlined.ShoppingCart, contentDescription = null) },
                onClick = {
                    expanded = false
                    navController.navigate("orderPage")
                }
            )
            DropdownMenuItem(
                text = { Text("Competition") },
                leadingIcon = { Icon(Icons.Outlined.BookOnline, contentDescription = null) },
                onClick = { expanded = false }
            )
        }
    }
}
