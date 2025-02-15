package its.dart.com.presentation.ui.navigation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import its.dart.com.presentation.ui.theme.appColorWhite
import its.dart.com.presentation.ui.theme.mainGray


@Composable
fun BottomNavigation(
    itemList: List<TabItem>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    NavigationBar {
        Row(modifier = Modifier.background(appColorWhite)) {
            itemList.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = index == selectedTabIndex,
                    onClick = { onTabSelected(index) },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedTabIndex) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.title,
                            tint = Color(0xFF323447)
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            style = TextStyle(
                                fontWeight = if (index == selectedTabIndex) FontWeight.Bold else FontWeight.Normal,
                                color = mainGray,
                            )
                        )
                    },
                )
            }
        }
    }
}
