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
    data class QtyBought(val qtyBought: String) : PackPlacementEvent()
    data class BikeSales(val bikeSales: String): PackPlacementEvent()
    data class SalesManID(val salesManID: String): PackPlacementEvent()
    class OnShowAndHideErrorMessage(val disMiss: Boolean) : PackPlacementEvent()
    class OnSetCustomerIdAndURNO(val urno: Int, val customerId: Int) : PackPlacementEvent()
    data object OnConfirmEvent: PackPlacementEvent()
    //button event
    data object ShowOptionalDialog: PackPlacementEvent()
    data object HideOptionalDialog: PackPlacementEvent()
    data object ShowSuccessfulDialog: PackPlacementEvent()


}


