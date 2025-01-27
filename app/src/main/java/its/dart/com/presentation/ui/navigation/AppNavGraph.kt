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
    NavHost(
        navController = navController,
        startDestination = "homePage"
    ) {

        composable(route = "homePage") {
            LoginScreen(
                navController = navController
            )
        }

//        composable(route = "MainPage") {
//            MainScreen(
//                navController = navController
//            )
//        }
//
//        composable(route = "resetPasswordPage") {
//            ChangePassword(navController = navController)
//        }
    }
}