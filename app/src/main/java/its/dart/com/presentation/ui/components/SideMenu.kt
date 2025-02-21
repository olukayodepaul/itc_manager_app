package its.dart.com.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import android.net.Uri
import androidx.compose.material.icons.filled.DataArray
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.DataSaverOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Chat
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import its.dart.com.data.repository.local.entity.PromoterEntity
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
                tint = Color.Gray.copy(alpha = 1f),
                contentDescription = "More options",
                modifier = Modifier.size(20.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentSize().
            background(Color.White) // Ensures white background
                .border(1.dp, Color.LightGray),
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
                    navController.navigate("survey/${details.id}/${details.outletName}/${details.urno}")
                }
            )
            DropdownMenuItem(
                text = { Text("Order") },
                leadingIcon = { Icon(Icons.Outlined.ShoppingCart, contentDescription = null) },
                onClick = {
                    expanded = false
                    val identifier  = "0"
                    navController.navigate("OrderProduct/${details.id}/${details.outletName}/$identifier")
                }
            )

            DropdownMenuItem(
                text = { Text("Make a Call") },
                leadingIcon = { Icon(Icons.Outlined.Call, contentDescription = null) },
                onClick = {
                    expanded = false
                    navController.navigate("add_customer/${details.outletName}")
                }
            )

            DropdownMenuItem(
                text = { Text("Chat") },
                leadingIcon = { Icon(Icons.Outlined.Chat, contentDescription = null) },
                onClick = {
                    expanded = false
                    navController.navigate("add_customer/${details.outletName}")
                }
            )
        }
    }
}

@Composable
fun DropdownMenuWithDetail(
    navController: NavHostController,
    details: PromoterEntity
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val encodedIdentifier = Uri.encode("promoter")
    val userId: String = details.id.toString()
    val userName = Uri.encode(details.outlet_name)

    Box(
        modifier = Modifier.wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                Icons.Default.MoreVert,
                tint = Color.Gray.copy(alpha = 1f),
                contentDescription = "More options",
                modifier = Modifier.size(20.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentSize()
                .background(Color.White)
                .border(1.dp, Color.LightGray),
        ) {
            DropdownMenuItem(
                text = { Text(text = "Location Finder") },
                leadingIcon = { Icon(Icons.Outlined.LocationSearching, contentDescription = null) },
                onClick = { expanded = false }
            )
            DropdownMenuItem(
                text = { Text(text = "Daily Consumer Data") },
                leadingIcon = { Icon(Icons.Filled.DataArray, contentDescription = null) },
                onClick = {
                    expanded = false
                    navController.navigate("DailyConsumerPage/$userId/$userName/$encodedIdentifier")
                }
            )
            DropdownMenuItem(
                text = { Text(text = "Daily Retail Activity") },
                leadingIcon = { Icon(Icons.Filled.DataObject, contentDescription = null) },
                onClick = {
                    expanded = false
                    navController.navigate("DailyRetailPage/$userId/$userName/$encodedIdentifier")
                }
            )
            DropdownMenuItem(
                text = { Text(text = "Pack Placement") },
                leadingIcon = { Icon(Icons.Filled.DataSaverOn, contentDescription = null) },
                onClick = {
                    expanded = false
                    navController.navigate("PackPlacementPage/$userId/$userName/$encodedIdentifier")
                }
            )
            DropdownMenuItem(
                text = { Text(text = "Order") },
                leadingIcon = { Icon(Icons.Outlined.ShoppingCart, contentDescription = "") },
                onClick = {
                    expanded = false
                    navController.navigate("OrderScreen/$userId/$userName/$encodedIdentifier")
                }
            )
        }
    }
}
