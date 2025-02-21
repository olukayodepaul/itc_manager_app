package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.data.repository.remote.dto.SurveyStateDTO
import its.dart.com.domain.preference.PreferenceInt
import its.dart.com.domain.repository.remote.SurveyInt
import its.dart.com.presentation.viewmodel.event.SurveyEvent
import its.dart.com.presentation.viewmodel.state.SurveyState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val remoteRepository: SurveyInt,
    private val sharePreference: PreferenceInt
): ViewModel() {

    private val _surveyState = MutableStateFlow(SurveyState())
    val surveyState: StateFlow<SurveyState> get() = _surveyState.asStateFlow()

    fun onEvent(event: SurveyEvent) {
        viewModelScope.launch {
            eventHandler(event)
        }
    }

    private fun eventHandler(event: SurveyEvent) {
        when (event) {
            is SurveyEvent.SalesRepVisit -> updateSurveyState { copy(salesRepVisit = event.salesRepVisit) }
            is SurveyEvent.SalesRepVisitDate -> updateSurveyState { copy(salesRepVisitDate = event.salesRepVisitDate) }
            is SurveyEvent.SalesRepVisitSequence -> updateSurveyState { copy(salesRepVisitSequence = event.salesRepVisitSequence) }
            is SurveyEvent.SalesRepVisitProactive -> updateSurveyState { copy(salesRepVisitProactive = event.salesRepVisitProactive) }
            is SurveyEvent.HowSatisfy -> updateSurveyState { copy(howSatisfy = event.howSatisfy) }
            is SurveyEvent.UnResolveIssue -> updateSurveyState { copy(unResolveIssue = event.unResolveIssue) }
            is SurveyEvent.SalesRating -> updateSurveyState { copy(salesRating = event.salesRating) }
            is SurveyEvent.SalesRepVisitResponsiveness -> updateSurveyState { copy(salesRepVisitResponsiveness = event.salesRepVisitResponsiveness) }
            is SurveyEvent.SalesPerformanceProductPurchase -> updateSurveyState { copy(salesPerformanceProductPurchase = event.salesPerformanceProductPurchase) }
            is SurveyEvent.TargetSuperPrice -> updateSurveyState { copy(targetSuperPrice = event.targetSuperPrice) }
            is SurveyEvent.TargetSuperUOM -> updateSurveyState { copy(targetSuperUOM = event.targetSuperUOM) }
            is SurveyEvent.TargetMentholPrice -> updateSurveyState { copy(targetMentholPrice = event.targetMentholPrice) }
            is SurveyEvent.TargetMentholUOM -> updateSurveyState { copy(targetMentholUOM = event.targetMentholUOM) }
            is SurveyEvent.ExecPrice -> updateSurveyState { copy(execPrice = event.execPrice) }
            is SurveyEvent.ExecUOM -> updateSurveyState { copy(execUOM = event.execUOM) }
            is SurveyEvent.ExecClickPrice -> updateSurveyState { copy(execClickPrice = event.execClickPrice) }
            is SurveyEvent.ExecClickUOM -> updateSurveyState { copy(execClickUOM = event.execClickUOM) }
            is SurveyEvent.TargetSuperPurchase -> updateSurveyState { copy(targetSuperPurchase = event.targetSuperPurchase) }
            is SurveyEvent.TargetMentholPurchase -> updateSurveyState { copy(targetMentholPurchase = event.targetMentholPurchase) }
            is SurveyEvent.ExecutivePurchase -> updateSurveyState { copy(executivePurchase = event.executivePurchase) }
            is SurveyEvent.ExecutiveClickPurchase -> updateSurveyState { copy(executiveClickPurchase = event.executiveClickPurchase) }
            is SurveyEvent.WhatCanWeToImproveService -> updateSurveyState { copy(whatCanWeToImproveService = event.whatCanWeToImproveService) }
            is SurveyEvent.WhatWudYouRecommend -> updateSurveyState { copy(whatWudYouRecommend = event.whatWudYouRecommend) }
            is SurveyEvent.Comment -> updateSurveyState { copy(comment = event.comment) }
            is SurveyEvent.ProductAvailabilitySuper -> updateSurveyState { copy(productAvailabilitySuper = event.productAvailabilitySuper) }
            is SurveyEvent.ProductAvailabilityMenthol -> updateSurveyState { copy(productAvailabilityMenthol = event.productAvailabilityMenthol) }
            is SurveyEvent.ProductAvailabilityExec -> updateSurveyState { copy(productAvailabilityExec = event.productAvailabilityExec) }
            is SurveyEvent.ProductAvailabilityExecClick -> updateSurveyState { copy(productAvailabilityExecClick = event.productAvailabilityExecClick) }
            is SurveyEvent.Urno->updateSurveyState { copy(urno = event.urno) }
            is SurveyEvent.Navigation->updateSurveyState { copy(navigation = event.navigation) }
            is SurveyEvent.OnDismiss->onDismiss()
            is SurveyEvent.OnConfirm->onConfirm()
            is SurveyEvent.OnclickButton -> onClick()
            is SurveyEvent.OnOkClick -> onOkClick()
        }
    }

    private fun onConfirm(){
        viewModelScope.launch {

            updateSurveyState { copy(loaders = true, btDismissState = false) }
            val survey = _surveyState.value

            val result = SurveyStateDTO(
                salesRepVisit = survey.salesRepVisit,
                salesRepVisitDate = survey.salesRepVisitDate,
                salesRepVisitSequence = survey.salesRepVisitSequence,
                salesRepVisitProactive = survey.salesRepVisitProactive,
                howSatisfy = survey.howSatisfy,
                unResolveIssue = survey.unResolveIssue,
                salesRating = survey.salesRating,
                salesRepVisitResponsiveness = survey.salesRepVisitResponsiveness,
                salesPerformanceProductPurchase = survey.salesPerformanceProductPurchase,
                targetSuperPrice = survey.targetSuperPrice,
                targetSuperUOM = survey.targetSuperUOM,
                targetMentholPrice = survey.targetMentholPrice,
                targetMentholUOM = survey.targetMentholUOM,
                execPrice = survey.execPrice,
                execUOM = survey.execUOM,
                execClickPrice = survey.execClickPrice,
                execClickUOM = survey.execClickUOM,
                targetSuperPurchase = survey.targetSuperPurchase,
                targetMentholPurchase = survey.targetMentholPurchase,
                executivePurchase = survey.executivePurchase,
                executiveClickPurchase = survey.executiveClickPurchase,
                whatCanWeToImproveService = survey.whatCanWeToImproveService,
                whatWudYouRecommend = survey.whatWudYouRecommend,
                comment = survey.comment,
                productAvailabilitySuper = survey.productAvailabilitySuper,
                productAvailabilityMenthol = survey.productAvailabilityMenthol,
                productAvailabilityExec = survey.productAvailabilityExec,
                productAvailabilityExecClick = survey.productAvailabilityExecClick,
                urno = survey.urno,
                categoryId = sharePreference.getInt(key="sysCategory", defaultValue = 0).toString(),
                userId = sharePreference.getInt(key="id", defaultValue = 0).toString(),
            )

            //bottom sheet for error.
            remoteRepository.taskRequest(result)
                .onSuccess { result->
                    if(result.status == 200) {
                        updateSurveyState { copy(showDialog = true) }
                    }else{
                        //error page
                    }
                    updateSurveyState { copy(loaders = false) }
                }
                .onFailure { error->
                    //error page. bottom sheet
                }
        }
    }

    private fun onDismiss(){
        updateSurveyState { copy(btDismissState = false) }
    }

    private fun onOkClick() {
        updateSurveyState { copy(showDialog = false, navigation = true) }
    }
    private fun onClick() {
        updateSurveyState { copy(btDismissState = true) }
    }

    private fun updateSurveyState(update: SurveyState.() -> SurveyState) {
        _surveyState.value = _surveyState.value.update()
    }
}
