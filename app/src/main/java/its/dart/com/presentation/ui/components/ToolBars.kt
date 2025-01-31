package its.dart.com.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import its.dart.com.presentation.ui.theme.appColorWhite
import its.dart.com.presentation.ui.theme.dartSansFontFamily
import its.dart.com.presentation.ui.theme.iconColor
import its.dart.com.presentation.ui.theme.robotoFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(
    title: String,
    click: () -> Unit,
    clickSearch : ()->Unit,
    clickMenu:()->Unit,
    navigation: Boolean = false,
    fontSize: Int = 13,
    fontWeight: Int = 400
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = appColorWhite,
            titleContentColor = iconColor,
            navigationIconContentColor = iconColor,
            actionIconContentColor = iconColor,
        ),
        title = {
            Text(
                text = title,
                fontWeight = FontWeight(fontWeight),
                fontSize = fontSize.sp,
                color = iconColor,
                fontFamily = robotoFamily
            )
        },
        navigationIcon = {
            if(navigation){
                IconButton(onClick = click) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
        actions = {
            IconButton(onClick = clickSearch) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
            IconButton(onClick = clickMenu) {
                Icon(Icons.Default.MoreVert, contentDescription = "More Options")
            }
        }
    )
}
