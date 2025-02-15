package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import its.dart.com.presentation.ui.components.RowTask
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.appColorWhite
import its.dart.com.presentation.ui.theme.mainGray
import its.dart.com.presentation.ui.theme.robotoFamily


@Composable
fun Promo(
    navController: NavHostController
){
    Scaffold(
        topBar = {
            ToolBar(
                title = "Promoter/Market Seater",
                click = {},
                clickSearch = {},
                clickMenu = {},
                fontSize = 18,
                fontFamily = robotoFamily,
                letterSpacing = -0.2
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = mainGray,
                shape = CircleShape,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = appColorWhite,
                    contentDescription = null
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
                .verticalScroll(rememberScrollState()),
        ) {

        }
    }
}





@Preview(showBackground = true)
@Composable
fun PreviewComposable(){
    Promo(navController = rememberNavController())
}

