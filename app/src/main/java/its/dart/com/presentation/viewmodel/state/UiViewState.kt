package its.dart.com.presentation.viewmodel.state


//generic state
sealed class GenericState<out T> {
    data object Loading : GenericState<Nothing>()
    data class Success<out T>(val data: T) : GenericState<T>()
    data class Failure(val exception: Throwable) : GenericState<Nothing>()
}

data class LoginViewState(
    val usernameTextBox: String = "",
    val passwordTextBox: String = "",
    val errorMessage: String = "",
    val dialogLoader: Boolean  = false
)

data class TaskViewState (
    val resume: String = "00:00:00",
    val clockOut: String = "00:00:00",
    val clockIn: String = "00:00:00",
    val close : String = "00:00:00",
    val dialogLoader: Boolean  = false
)

data class AddCustomerViewState (
    val outletName: String = "",
    val contactPerson: String = "",
    val mobileNumber: String = "",
    val language: Int = 0,
    val outletType: Int = 0,
    val address: String = "",
    val errorMessage: String = "",
    val dialogLoader: Boolean  = false,
    val loader: Boolean = false,
    val success: Boolean = false,
    val repId: Int = 0
)

data class SurveyState(

    //one
    val salesRepVisit:String = "",
    val salesRepVisitDate:String = "",
    val salesRepVisitSequence: String = "",
    val salesRepVisitProactive: String = "",

    //two
    val howSatisfy: String = "",
    val unResolveIssue: String = "",
    val salesRating: String = "",
    val salesRepVisitResponsiveness: String = "",

    //three
    var salesPerformanceProductPurchase: String  = "",
    var targetSuperPrice: String = "",
    var targetSuperUOM:String = "",
    var targetMentholPrice: String = "",
    var targetMentholUOM:String = "",
    var execPrice: String = "",
    var execUOM:String = "",
    var execClickPrice: String = "",
    var execClickUOM:String = "",
    var targetSuperPurchase: String = "",
    var targetMentholPurchase:String = "",
    var executivePurchase: String = "",
    var executiveClickPurchase:String = "",

    //four
    val whatCanWeToImproveService: String = "",
    val whatWudYouRecommend: String = "",
    var comment:String = "",

    //fifth
    val productAvailabilitySuper: String = "",
    val productAvailabilityMenthol: String = "",
    var productAvailabilityExec:String = "",
    var productAvailabilityExecClick:String = "",

    //Others
    val loaders: Boolean = false,
    val navigation:Boolean = false,
    val messageNotification: String = "",
    val urno: String = "",

    //Interaction
    val btDismissState:Boolean = false,
    val showDialog: Boolean = false



)



