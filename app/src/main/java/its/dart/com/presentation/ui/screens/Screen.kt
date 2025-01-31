package its.dart.com.presentation.ui.screens

sealed class Screen(val route: String) {
    data object HomePage : Screen("homePage")
    data object MainPage : Screen("MainPage")
    data object CustomersScreen : Screen("CustomersScreen/{userId}/{userName}") {
        fun createRoute(userId: String, userName: String) = "CustomersScreen/$userId/$userName"
    }
    data object SurveyScreen : Screen("survey/{userId}/{userName}") {
        fun createRoute(userId: String, userName: String) = "survey/$userId/$userName"
    }
    data object OrderScreen : Screen("orderPage")
}