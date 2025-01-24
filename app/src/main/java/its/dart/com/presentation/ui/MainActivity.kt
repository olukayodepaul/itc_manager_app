package its.dart.com.presentation.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import its.dart.com.presentation.ui.screens.LoginScreen
import its.dart.com.presentation.ui.theme.JetpackComposeAuthUITheme
import its.dart.com.presentation.ui.theme.appColorBlack


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
                        val statusBarColor = appColorBlack
                        window.statusBarColor = statusBarColor.toArgb()
                        // Optionally, you can also make sure the status bar content is light/dark depending on the background color
                        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = statusBarColor.luminance() > 0.5
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
