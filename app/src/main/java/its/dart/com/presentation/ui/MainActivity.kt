package its.dart.com.presentation.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import its.dart.com.presentation.ui.navigation.AppNavGraph
import its.dart.com.presentation.ui.theme.JetpackComposeAuthUITheme
import its.dart.com.presentation.ui.theme.appColor
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.appColorWhite
import its.dart.com.presentation.ui.theme.lightGray

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAuthUITheme(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = true
            ){
                val view = LocalView.current
                if(!view.isInEditMode)
                {
                    SideEffect {
                        val statusBarColor = Color(0xFFFFFFFFF)
                        val navigationBarColor =  Color(0xFFF7F8F9)
                        window.statusBarColor = statusBarColor.toArgb()
                        window.navigationBarColor = navigationBarColor.toArgb()
                        WindowCompat.setDecorFitsSystemWindows(window, true)

                        WindowCompat.getInsetsController(window, view).apply {
                            isAppearanceLightStatusBars = statusBarColor.luminance() > 0.5
                            isAppearanceLightNavigationBars = navigationBarColor.luminance() > 0.5
                        }
                    }
                }

                AppNavGraph(
                    navController = rememberNavController()
                )
            }
        }
    }
}


