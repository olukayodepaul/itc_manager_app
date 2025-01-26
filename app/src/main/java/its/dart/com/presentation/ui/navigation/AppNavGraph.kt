package its.dart.com.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import its.dart.com.presentation.ui.screens.ChangePassword
import its.dart.com.presentation.ui.screens.MainScreen
import its.dart.com.presentation.ui.screens.LoginScreen


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {

        composable(route = "login") {
            LoginScreen(navController = navController)
        }

        composable(route = "main") {
            MainScreen(navController = navController)
        }

        composable(route = "password") {
            ChangePassword(navController = navController)
        }

    }
}