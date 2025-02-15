package its.dart.com.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import its.dart.com.presentation.ui.navigation.AppNavGraph
import its.dart.com.presentation.ui.theme.JetpackComposeAuthUITheme
import its.dart.com.presentation.ui.theme.appColorWhite


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAuthUITheme(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = true
            ){
                SystemUiController()
                AppNavGraph(
                    navController = rememberNavController(),
                    window = window
                )
            }
        }
    }

    @Composable
    private fun SystemUiController(){
        val systemUiController =  rememberSystemUiController()
        SideEffect{
            systemUiController.setSystemBarsColor(appColorWhite)
            systemUiController.setNavigationBarColor(appColorWhite)
        }
    }
}


