package its.dart.com.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import its.dart.com.presentation.ui.screens.CustomerByRep
import its.dart.com.presentation.ui.screens.MainScreen
import its.dart.com.presentation.ui.screens.LoginScreen
import its.dart.com.presentation.ui.screens.SurveyScreen
import its.dart.com.presentation.ui.screens.Screen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomePage.route
    ) {
        composable(route = Screen.HomePage.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Screen.MainPage.route) {
            MainScreen(navController = navController)
        }

        composable(
            route = Screen.CustomersScreen.route,
            arguments = listOf(
                navArgument("userId") { type = NavType.StringType },
                navArgument("userName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            CustomerByRep(navController, userId, userName)
        }

        composable(
            route = Screen.SurveyScreen.route,
            arguments = listOf(
                navArgument("userId") { type = NavType.StringType },
                navArgument("userName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            SurveyScreen(navController, userId, userName)
        }

    }
}
