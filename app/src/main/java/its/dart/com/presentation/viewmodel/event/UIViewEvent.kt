package its.dart.com.presentation.viewmodel.event


sealed class LoginViewEvent {
    class OnUsernameTextBox(val usernameTextBox: String) : LoginViewEvent()
    class OnPasswordTextBox(val passwordTextBox: String) : LoginViewEvent()
    data object OnClickButton: LoginViewEvent()
}

sealed class TaskViewEvent {
    data object OnClickResume: TaskViewEvent()
    data object OnClickClockOut: TaskViewEvent()
    data object OnClickClockIn: TaskViewEvent()
    data object OnClickClose: TaskViewEvent()
}

sealed class AddCustomerViewEvent{
    class OnOutletName(val outletName: String): AddCustomerViewEvent()
    class OnContactPerson(val contactPerson: String): AddCustomerViewEvent()
    class OnMobileNumber(val mobileNumber: String): AddCustomerViewEvent()
    class OnLanguage(val language: Int): AddCustomerViewEvent()
    class OnOutletType(val outletType: Int): AddCustomerViewEvent()
    class OnAddress(val address: String) : AddCustomerViewEvent()
    data object OnclickButton: AddCustomerViewEvent()
    data object OnclickDismissButton: AddCustomerViewEvent()
    data object OnclickConfirmButton: AddCustomerViewEvent()
    data object OnErrorClear: AddCustomerViewEvent()
    data object OnSuccessFulReset: AddCustomerViewEvent()
    class OnShowAndHideErrorMessage(val disMiss: Boolean) : AddCustomerViewEvent()
    class OnSearchEventMerchant(val searchState: String, val setToDefault:Int = 0): AddCustomerViewEvent()
    data object OnResetSearchMerchant: AddCustomerViewEvent()
    class OnSearchEventPromoter(val searchState: String, val setToDefault:Int = 0): AddCustomerViewEvent()
    data object OnResetSearchPromoter: AddCustomerViewEvent()
}

data class OptionData(
    val day: String,
    val id: Int
)

fun getPromoterSupervisor(): List<OptionData> {
    return listOf(
        OptionData("Monday", 1),
        OptionData("Tuesday", 2),
        OptionData("Wednesday", 3),
        OptionData("Thursday", 4),
        OptionData("Friday", 5),
        OptionData("Saturday", 6)
    )
}

sealed class SurveyEvent {
    // First
    data class SalesRepVisit(val salesRepVisit: String) : SurveyEvent()
    data class SalesRepVisitDate(val salesRepVisitDate: String) : SurveyEvent()
    data class SalesRepVisitSequence(val salesRepVisitSequence: String) : SurveyEvent()
    data class SalesRepVisitProactive(val salesRepVisitProactive: String) : SurveyEvent()

    data class OnRecivePromoItem(val recivePromoItem: String) : SurveyEvent()

    // Second Two
    data class HowSatisfy(val howSatisfy: String) : SurveyEvent()
    data class RegularVisit(val regularVisit: String) : SurveyEvent()
    data class UnResolveIssue(val unResolveIssue: String) : SurveyEvent()
    data class SalesRating(val salesRating: String) : SurveyEvent()
    data class SalesRepVisitResponsiveness(val salesRepVisitResponsiveness: String) : SurveyEvent()

    //third
    data class SalesPerformanceProductPurchase(val salesPerformanceProductPurchase: String ): SurveyEvent()
    data class TargetSuperPrice(val targetSuperPrice: String) : SurveyEvent()
    data class TargetSuperUOM(val targetSuperUOM: String) : SurveyEvent()
    data class TargetMentholPrice(val targetMentholPrice: String) : SurveyEvent()
    data class TargetMentholUOM(val targetMentholUOM: String) : SurveyEvent()
    data class ExecPrice(val execPrice: String) : SurveyEvent()
    data class ExecUOM(val execUOM: String) : SurveyEvent()
    data class ExecClickPrice(val execClickPrice: String) : SurveyEvent()
    data class ExecClickUOM(val execClickUOM: String) : SurveyEvent()
    data class TargetSuperPurchase(val targetSuperPurchase: String) : SurveyEvent()
    data class TargetMentholPurchase(val targetMentholPurchase: String) : SurveyEvent()
    data class ExecutivePurchase(val executivePurchase: String) : SurveyEvent()
    data class ExecutiveClickPurchase(val executiveClickPurchase: String) : SurveyEvent()

    //fourth
    data class WhatCanWeToImproveService(val whatCanWeToImproveService: String): SurveyEvent()
    data class WhatWudYouRecommend(val whatWudYouRecommend: String): SurveyEvent()
    data class Comment(val comment: String): SurveyEvent()

    // Section Five
    data class ProductAvailabilitySuper(val productAvailabilitySuper: String) : SurveyEvent()
    data class ProductAvailabilityMenthol(val productAvailabilityMenthol: String) : SurveyEvent()
    data class ProductAvailabilityExec(val productAvailabilityExec: String) : SurveyEvent()
    data class ProductAvailabilityExecClick(val productAvailabilityExecClick: String) : SurveyEvent()
    data class Urno(val urno: String, val repId: Int) : SurveyEvent()
    data class Navigation(val navigation: Boolean) : SurveyEvent()
    class OnShowAndHideErrorMessage(val disMiss: Boolean) : SurveyEvent()

    //clicking view  data
    data object OnclickButton: SurveyEvent()
    data object OnDismiss: SurveyEvent()
    data object OnConfirm: SurveyEvent()

    data object OnOkClick: SurveyEvent()

}

sealed class PackPlacementEvent {
    data class SkuHandler(val skuHandler: String) : PackPlacementEvent()
    data class FreePackPlacementTGTSuper(val freePackPlacementTGTSuper: String): PackPlacementEvent()
    data class FreePackPlacementTGTMLT(val freePackPlacementTGTMLT: String): PackPlacementEvent()
    data class FreePackPlacementExec(val freePackPlacementExec: String): PackPlacementEvent()
    data class BikeSales(val bikeSales: String): PackPlacementEvent()
    data class SalesManID(val salesManID: String): PackPlacementEvent()
    class OnShowAndHideErrorMessage(val disMiss: Boolean) : PackPlacementEvent()
    class OnSetCustomerIdAndURNO(val urno: Int, val customerId: Int) : PackPlacementEvent()
    data object OnConfirmEvent: PackPlacementEvent()
    //button event
    data object ShowOptionalDialog: PackPlacementEvent()
    data object HideOptionalDialog: PackPlacementEvent()
    data object ShowSuccessfulDialog: PackPlacementEvent()
    data class OnTGTSuperSalesMadeUOM(val tTGTSuperSalesMadeUOM: String) : PackPlacementEvent()
    data class OnTGTSuperSalesMade(val tTGTSuperSalesMade: String) : PackPlacementEvent()
    data class OnTGTMLTSalesMadeUOM(val tTGTMLTSalesMadeUOM: String) : PackPlacementEvent()
    data class OnTGTMLTSalesMade(val tTGTMLTSalesMade: String) : PackPlacementEvent()
    data class OnExecKSSalesMadeUOM(val execKSSalesMadeUOM: String) : PackPlacementEvent()
    data class OnExecKSSalesMade(val execKSSalesMade: String) : PackPlacementEvent()
    data class OnExecCKSalesMadeUOM(val execCKSalesMadeUOM: String) : PackPlacementEvent()
    data class OnExecCKSalesMade(val execCKSalesMade: String) : PackPlacementEvent()
}

sealed class DailyRetailActivityEvent {

    // Stock Out Events
    data class OnTGTSuperStockOut(val tTGTSuperStockOut: String) : DailyRetailActivityEvent()
    data class OnTGTMLTStockOut(val tTGTMLTStockOut: String) : DailyRetailActivityEvent()
    data class OnExecKSStockOut(val execKSStockOut: String) : DailyRetailActivityEvent()
    data class OnExecCKStockOut(val execCKStockOut: String) : DailyRetailActivityEvent()

    // Sales Made Events
    data class OnTGTSuperSalesMadeUOM(val tTGTSuperSalesMadeUOM: String) : DailyRetailActivityEvent()
    data class OnTGTSuperSalesMade(val tTGTSuperSalesMade: String) : DailyRetailActivityEvent()

    data class OnTGTMLTSalesMadeUOM(val tTGTMLTSalesMadeUOM: String) : DailyRetailActivityEvent()
    data class OnTGTMLTSalesMade(val tTGTMLTSalesMade: String) : DailyRetailActivityEvent()

    data class OnExecKSSalesMadeUOM(val execKSSalesMadeUOM: String) : DailyRetailActivityEvent()
    data class OnExecKSSalesMade(val execKSSalesMade: String) : DailyRetailActivityEvent()

    data class OnExecCKSalesMadeUOM(val execCKSalesMadeUOM: String) : DailyRetailActivityEvent()
    data class OnExecCKSalesMade(val execCKSalesMade: String) : DailyRetailActivityEvent()

    // ITC SalesMan Event
    data class OnITCSalesMan(val itcSalesMan: String) : DailyRetailActivityEvent()

    // Reward Events
    data class OnTGTSuperRewardUOM(val tTGTSuperRewardUOM: String) : DailyRetailActivityEvent()
    data class OnTGTSuperReward(val tTGTSuperReward: String) : DailyRetailActivityEvent()

    data class OnTGTMLTRewardUOM(val tTGTMLTRewardUOM: String) : DailyRetailActivityEvent()
    data class OnTGTMLTReward(val tTGTMLTReward: String) : DailyRetailActivityEvent()

    data class OnExecKSRewardUOM(val execKSRewardUOM: String) : DailyRetailActivityEvent()
    data class OnExecKSReward(val execKSReward: String) : DailyRetailActivityEvent()

    data class OnExecCKRewardUOM(val execCKRewardUOM: String) : DailyRetailActivityEvent()
    data class OnExecCKReward(val execCKReward: String) : DailyRetailActivityEvent()

    // Sampling Events
    data class OnExecKSSampling(val execKSSampling: String) : DailyRetailActivityEvent()
    data class OnExecCKSampling(val execCKSampling: String) : DailyRetailActivityEvent()

    //button event
    data object OnConfirmEvent: DailyRetailActivityEvent()
    data object HideOptionalDialog: DailyRetailActivityEvent()
    data object SyncDataToServer: DailyRetailActivityEvent()
    data object HideSuccessfulDialog: DailyRetailActivityEvent()


    class OnSetCustomerIdAndURNO(val urno: Int, val customerId: Int) : DailyRetailActivityEvent()

}


sealed class DailyActivityForm{
    data class OnConsumerName(val consumerName: String) : DailyActivityForm()
    data class OnPhoneNumber(val phoneNumber: String) : DailyActivityForm()
    data class OnGender(val gender: String) : DailyActivityForm()
    data class OnAge(val age: String) : DailyActivityForm()
    data class OnAnySales(val anySales: String) : DailyActivityForm()
    data class OnPersonalBrands(val personalBrands: String) : DailyActivityForm()
    data class OnSampleSuper(val sampleSuper: String): DailyActivityForm()
    data class OnSampleMenthol(val sampleMenthol: String): DailyActivityForm()
    data class OnSampleExec(val sampleExec: String): DailyActivityForm()
    data class OnSampleExecClick(val sampleExecClick: String) : DailyActivityForm()
    data class OnQtySoldSuperUOM(val qtySoldSuperUOM: String) : DailyActivityForm()
    data class OnQtySoldSuper(val qtySoldSuper: String) : DailyActivityForm()
    data class OnQtySoldMentholUOM(val qtySoldMentholUOM: String) : DailyActivityForm()
    data class OnQtySoldMenthol(val qtySoldMenthol: String) : DailyActivityForm()
    data class OnQtySoldExecUOM(val qtySoldExecUOM: String) : DailyActivityForm()
    data class OnQtySoldExec(val qtySoldExec: String) : DailyActivityForm()
    data class OnQtySoldExecClickUOM(val qtySoldExecClickUOM: String) : DailyActivityForm()
    data class OnQtySoldExecClick(val qtySoldExecClick: String) : DailyActivityForm()
    data class OnPMBLTH(val PMBLTH: String) : DailyActivityForm()
    data class OnPMBPEN(val PMBPEN: String) : DailyActivityForm()
    data class OnPMBWB(val PMBWB: String) : DailyActivityForm()
    data class OnComment(val comment: String) : DailyActivityForm()
    data class OnAcknowledge(val acknowledge: String): DailyActivityForm()


    class OnShowAndHideErrorMessage(val disMiss: Boolean) : DailyActivityForm()

    //button event
    data object OnConfirmEvent: DailyActivityForm()
    data object HideOptionalDialog: DailyActivityForm()
    data object SyncDataToServer: DailyActivityForm()
    data object HideSuccessfulDialog: DailyActivityForm()
    class OnSetCustomerIdAndURNO(val urno: Int, val customerId: Int) : DailyActivityForm()
}
