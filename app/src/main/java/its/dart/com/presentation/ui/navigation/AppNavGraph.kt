package its.dart.com.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import its.dart.com.presentation.ui.screens.ChangePassword
import its.dart.com.presentation.ui.screens.CustomerByRep
import its.dart.com.presentation.ui.screens.CustomersScreen
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

        composable(route = "MainPage") {
            MainScreen(
                navController = navController
            )
        }

        composable(
            route = "CustomersScreen/{userId}/{userName}",
            arguments = listOf(
                navArgument("userId") { type = NavType.StringType },
                navArgument("userName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            CustomerByRep(
                navController,  userId, userName
            )
        }
//
//        composable(route = "resetPasswordPage") {
//            ChangePassword(navController = navController)
//        }
    }
}