package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentReturned
import androidx.compose.material.icons.filled.DataArray
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.DataSaverOn
import androidx.compose.material.icons.filled.DynamicForm
import androidx.compose.material.icons.filled.FormatAlignCenter
import androidx.compose.material.icons.filled.FormatAlignJustify
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.TimeToLeave
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import its.dart.com.presentation.ui.components.RowTask
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.robotoFamily


@Composable
fun Promo(
    navController: NavHostController
){
    Scaffold(
        topBar = {
            ToolBar(
                title = "Promoter Activities",
                click = {},
                clickSearch = {},
                clickMenu = {},
                fontSize = 18,
                fontFamily = robotoFamily,
                letterSpacing = -0.2
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
                .verticalScroll(rememberScrollState()),
        ) {
            RowTask(icon = Icons.Filled.DataArray , title = " Daily Consumer Data Form", subTitle = "Captures consumer activity, including purchasing behaviour",
                click = {navController.navigate("DailyConsumerPage")},
                timeIcon = false
            )

            RowTask(icon = Icons.Filled.DataObject , title = "Daily Retail Activity Form", subTitle = "Tracks the daily activities of the promoter, updating the system on their performance in relation to customers",
                click = { navController.navigate("DailyRetailPage")},
                timeIcon = false
            )

            RowTask(icon = Icons.Filled.DataSaverOn , title = "Pack Placement", subTitle = "Records the placement of SKUs at each customer outlet, ensuring accurate tracking of SKU distribution",
                click = {navController.navigate("PackPlacementPage")},timeIcon = false
            )
        }
    }
}