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


    val recivePromoItem: String = "",

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
    val bikeSales: String = "",
    val salesManID: String = "",

    //widget state
    val errorMessage: String = "",
    val showAndHideErrorMessage: Boolean = false,
    val confirmDialog: Boolean  = false,
    val success: Boolean = false,
    val loaders: Boolean = false,
    val urno: Int = 0,
    val customerId: Int = 0,

    val tTGTSuperSalesMadeUOM: String = "",
    val tTGTSuperSalesMade: String = "",

    val tTGTMLTSalesMadeUOM: String = "",
    val tTGTMLTSalesMade: String = "",

    val execKSSalesMadeUOM: String = "",
    val execKSSalesMade: String = "",

    val execCKSalesMadeUOM: String = "",
    val execCKSalesMade: String = ""

)

data class DailyRetailActivityState(
    //one
    val tTGTSuperStockOut: String = "",
    val tTGTMLTStockOut: String = "",
    val execKSStockOut: String = "",
    val execCKStockOut: String = "",

    //two
    val tTGTSuperSalesMadeUOM: String = "",
    val tTGTSuperSalesMade: String = "",

    val tTGTMLTSalesMadeUOM: String = "",
    val tTGTMLTSalesMade: String = "",

    val execKSSalesMadeUOM: String = "",
    val execKSSalesMade: String = "",

    val execCKSalesMadeUOM: String = "",
    val execCKSalesMade: String = "",

    //three
    val itcSalesMan: String = "",

    //four
    val tTGTSuperRewardUOM: String = "",
    val tTGTSuperReward: String = "",

    val tTGTMLTRewardUOM: String = "",
    val tTGTMLTReward: String = "",

    val execKSRewardUOM: String = "",
    val execKSReward: String = "",

    val execCKRewardUOM: String = "",
    val execCKReward: String = "",

    //five
    val execKSSampling: String = "",
    val execCKSampling: String = "",


    //widget state
    val errorMessage: String = "",
    val showAndHideErrorMessage: Boolean = false,
    val confirmDialog: Boolean  = false,
    val success: Boolean = false,
    val loaders: Boolean = false,
    val urno: Int = 0,
    val customerId: Int = 0
)

data class DailyConsumerState(
    val consumerName: String = "",
    val phoneNumber: String = "",
    val gender: String = "",
    val age: String = "",

    //sample sku should be list
    val sampleSuper: String = "",
    val sampleMenthol: String = "",
    var sampleExec:String = "",
    var sampleExecClick:String = "",

    //any sales
    val anySales: String = "",

    //personal brands
    val personalBrands: String = "",

    //quantity sold per sku
    val qtySoldSuperUOM: String = "",
    val qtySoldSuper: String = "",

    val qtySoldMentholUOM: String = "",
    val qtySoldMenthol: String = "",

    val qtySoldExecUOM: String = "",
    val qtySoldExec: String = "",

    val qtySoldExecClickUOM: String = "",
    val qtySoldExecClick: String = "",

    //pmb
    val PMBLTH: String = "",
    val PMBPEN: String = "",
    var PMBWB:String = "",

    var comment:String ="",

    var acknowledge:String ="Not Confirm",


    //widget state
    val errorMessage: String = "",
    val showAndHideErrorMessage: Boolean = false,
    val confirmDialog: Boolean  = false,
    val success: Boolean = false,
    val loaders: Boolean = false,
    val urno: Int = 0,
    val customerId: Int = 0

)

