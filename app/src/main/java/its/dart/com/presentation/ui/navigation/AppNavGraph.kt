package its.dart.com.presentation.ui.navigation

import android.view.Window
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import its.dart.com.domain.repository.remote.model.WholeSellerModel
import its.dart.com.presentation.ui.screens.AddCustomer
import its.dart.com.presentation.ui.screens.CustomerByRep
import its.dart.com.presentation.ui.screens.DailyActivityForm
import its.dart.com.presentation.ui.screens.DailyConsumer
import its.dart.com.presentation.ui.screens.MainScreen
import its.dart.com.presentation.ui.screens.LoginScreen
import its.dart.com.presentation.ui.screens.OrderScreen
import its.dart.com.presentation.ui.screens.PackPlacement
import its.dart.com.presentation.ui.screens.Promo
import its.dart.com.presentation.ui.screens.SurveyScreen
import its.dart.com.presentation.ui.screens.Screen
import its.dart.com.presentation.ui.screens.WholeSellerScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    window: Window
) {
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

        composable(
            route = Screen.OrderScreen.route,
            arguments = listOf(
                navArgument("userId") { type = NavType.StringType },
                navArgument("userName") { type = NavType.StringType },
                navArgument("identifier") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            val identifier = backStackEntry.arguments?.getString("identifier") ?: ""
            OrderScreen(navController, userId, userName, identifier, window)
        }

        composable(
            route = Screen.AddCustomer.route,
            arguments = listOf(
                navArgument("userName") { type = NavType.StringType },
            )
        ){backStackEntry->
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            AddCustomer(navController = navController, userName)
        }

        composable(route = Screen.WholeSellerPage.route){
            WholeSellerScreen(navController = navController)
        }

        composable(route = Screen.DailyConsumerPage.route){
            DailyConsumer(navController = navController)
        }

        composable(route = Screen.DailyRetailPage.route){
            DailyActivityForm(navController = navController)
        }

        composable(route = Screen.PackPlacementPage.route){
            PackPlacement(navController = navController)
        }

    }
}
