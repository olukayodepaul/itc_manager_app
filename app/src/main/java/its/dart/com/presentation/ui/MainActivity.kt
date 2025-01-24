package its.dart.com.presentation.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import its.dart.com.presentation.ui.screens.LoginScreen
import its.dart.com.presentation.ui.theme.JetpackComposeAuthUITheme
import its.dart.com.presentation.ui.theme.statusBar


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackComposeAuthUITheme(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = true
            ){

                val view = LocalView.current
                if(!view.isInEditMode){
                    SideEffect {
                        val window = (view.context as Activity).window
                        val statusBarColor = Color(0xFF09af00)
                        window.statusBarColor = statusBarColor.toArgb()
                    }
                }

                NavigationView()
            }
        }
    }
}

@Composable
fun NavigationView() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
    }
}
