package its.dart.com.presentation.ui.screens

sealed class Screen(val route: String) {

    data object HomePage : Screen("homePage")

    data object MainPage : Screen("MainPage")

    data object CustomersScreen : Screen("CustomersScreen/{repId}/{repName}/{routId}") {
        fun createRoute(repId: String, repName: String, routId: String) = "CustomersScreen/$repId/$repName/$routId"
    }

    data object SurveyScreen : Screen("survey/{userId}/{userName}/{urno}") {
        fun createRoute(userId: String, userName: String, urno: String) = "survey/$userId/$userName/$urno"
    }

    data object AddCustomer : Screen("add_customer/{repId}/{repName}") {
        fun createRoute(userName: String, userId: String, repId: String, repName: String) = "add_customer/$repId/$repName"
    }

    data object WholeSellerPage : Screen("wholeSellerPage")

    data object OrderScreen : Screen("order/{userId}/{userName}/{urno}") {
        fun createRoute(userId: String, userName: String, urno: String) = "survey/$userId/$userName/$urno"
    }

}