package its.dart.com.presentation.ui.navigation

import android.view.Window
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import its.dart.com.presentation.ui.screens.AddCustomer
import its.dart.com.presentation.ui.screens.CustomerByRep
import its.dart.com.presentation.ui.screens.DailyActivityForm
import its.dart.com.presentation.ui.screens.DailyConsumer
import its.dart.com.presentation.ui.screens.MainScreen
import its.dart.com.presentation.ui.screens.LoginScreen
import its.dart.com.presentation.ui.screens.OrderScreen
import its.dart.com.presentation.ui.screens.PackPlacement
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
                navArgument("repId") { type = NavType.StringType },
                navArgument("repName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val repId = backStackEntry.arguments?.getString("repId") ?: ""
            val repName = backStackEntry.arguments?.getString("repName") ?: ""
            CustomerByRep(navController, repId, repName)
        }

        composable(
            route = Screen.SurveyScreen.route,
            arguments = listOf(
                navArgument("userId") { type = NavType.StringType },
                navArgument("userName") { type = NavType.StringType },
                navArgument("urno") { type = NavType.StringType },

            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            val urno = backStackEntry.arguments?.getString("urno") ?: ""
            SurveyScreen(navController, userId, userName, urno)
        }

        composable(
            route = Screen.AddCustomer.route,
            arguments = listOf(
                navArgument("repId") { type = NavType.StringType },
                navArgument("repName") { type = NavType.StringType },
            )
        ){backStackEntry->
            val repId = backStackEntry.arguments?.getString("repId") ?: ""
            val repName = backStackEntry.arguments?.getString("repName") ?: ""
            AddCustomer(navController = navController, repId, repName)
        }

        composable(route = Screen.WholeSellerPage.route){
            WholeSellerScreen(navController = navController)
        }

        composable(
            route = "PackPlacementPage/{userId}/{userName}/{identifier}",
            arguments = listOf(
                navArgument("userId") { type = NavType.StringType },
                navArgument("userName") { type = NavType.StringType },
                navArgument("identifier") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            val identifier = backStackEntry.arguments?.getString("identifier") ?: ""
            PackPlacement(navController, userId, userName, identifier)
        }

        composable(
            route = "DailyConsumerPage/{userId}/{userName}/{identifier}",
            arguments = listOf(
                navArgument("userId") { type = NavType.StringType },
                navArgument("userName") { type = NavType.StringType },
                navArgument("identifier") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            val identifier = backStackEntry.arguments?.getString("identifier") ?: ""
            DailyConsumer(navController, userId, userName, identifier)
        }

        composable(
            route = "DailyRetailPage/{userId}/{userName}/{identifier}",
            arguments = listOf(
                navArgument("userId") { type = NavType.StringType },
                navArgument("userName") { type = NavType.StringType },
                navArgument("identifier") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            val identifier = backStackEntry.arguments?.getString("identifier") ?: ""
            DailyActivityForm(navController, userId, userName, identifier)
        }

        composable(
            route = "OrderScreen/{userId}/{userName}/{identifier}",
            arguments = listOf(
                navArgument("userId") { type = NavType.StringType },
                navArgument("userName") { type = NavType.StringType },
                navArgument("identifier") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            val identifier = backStackEntry.arguments?.getString("identifier") ?: ""
            OrderScreen(navController, userId, userName, identifier)
        }

    }
}
