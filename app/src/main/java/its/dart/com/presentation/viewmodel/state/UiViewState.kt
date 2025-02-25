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
    val showAndHideErrorMessage: Boolean = false,
    val confirmDialog: Boolean  = false,
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
    val regularVisit: String = "",
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
    val urno: String = "",
    val repId: Int = 0,

    val errorMessage: String = "",
    val showAndHideErrorMessage: Boolean = false,

    //Interaction
    val btDismissState:Boolean = false,
    val showDialog: Boolean = false
)

data class OrderState(
    //Others
    val loaders: Boolean = false,
    val navigation:Boolean = false,
    val urno: Int = 0,
    val repId: Int = 0,
    val errorMessage: String = "",
    val showAndHideErrorMessage: Boolean = false,
    val confirmDialog: Boolean  = false,
    val success: Boolean = false,
)


data class PackPlacementState(
    //data stat
    //ome
    val skuHandler: String = "",

    //two
    val freePackPlacementTGTSuper: String = "",
    val freePackPlacementTGTMLT: String = "",
    val freePackPlacementExec: String = "",

    //three
    val qtyBought: String = "",
    val bikeSales: String = "",
    val salesManID: String = "",

    //widget state
    val errorMessage: String = "",
    val showAndHideErrorMessage: Boolean = false,
    val confirmDialog: Boolean  = false,
    val success: Boolean = false,
    val loaders: Boolean = false,
    val urno: Int = 0,
    val customerId: Int = 0
)




