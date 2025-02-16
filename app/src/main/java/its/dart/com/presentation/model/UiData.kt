package its.dart.com.presentation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.DataArray
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.DataSaverOn
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import its.dart.com.presentation.ui.theme.BlueStart
import its.dart.com.presentation.ui.theme.GreenStart
import its.dart.com.presentation.ui.theme.OrangeStart


data class UiData(
    val icon: ImageVector,
    val title: String,
    val navIcon: ImageVector,
    val onClick: () -> Unit,
    val color: Color
)

fun getPromoterSurvey(navController: NavHostController): List<UiData> {
    return listOf(
        UiData(
            icon = Icons.Filled.DataArray ,
            title = "Daily Consumer Data",
            navIcon = Icons.Filled.ArrowForwardIos,
            onClick = { navController.navigate("DailyConsumerPage") },
            color = BlueStart
        ),
        UiData(
            icon = Icons.Filled.DataObject,
            title = "Daily Retail Activity",
            navIcon = Icons.Filled.ArrowForwardIos,
            onClick = { navController.navigate("DailyRetailPage") },
            color = OrangeStart
        ),
        UiData(
            icon = Icons.Filled.DataSaverOn,
            title = "Pack Placement",
            navIcon = Icons.Filled.ArrowForwardIos,
            onClick = { navController.navigate("PackPlacementPage") },
            color = GreenStart
        )
    )
}

//val languageCache = LanguageCache().apply {
//    add(0, "Select Language")
//    add(1, "Yoruba")
//    add(2, "Igbo")
//    add(3, "Hausa")
//    add(4, "English")
//}
