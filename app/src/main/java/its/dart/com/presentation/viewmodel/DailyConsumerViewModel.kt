package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.data.repository.remote.dto.DailyActivityViewDTO
import its.dart.com.data.repository.remote.dto.DailyCustomerDTO
import its.dart.com.domain.preference.PreferenceInt
import its.dart.com.domain.repository.remote.DailyActivityInt
import its.dart.com.presentation.viewmodel.event.DailyActivityForm
import its.dart.com.presentation.viewmodel.event.DailyRetailActivityEvent
import its.dart.com.presentation.viewmodel.state.DailyConsumerState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DailyConsumerViewModel @Inject constructor(
    val remoteRepo: DailyActivityInt,
    private val sharePreference: PreferenceInt
): ViewModel(){

    private val _uiState = MutableStateFlow(DailyConsumerState())
    val uiState: StateFlow<DailyConsumerState> = _uiState.asStateFlow()

    fun onEvent(event: DailyActivityForm) {
        handleEvent(event)
    }

    private fun updateUI(update: DailyConsumerState.() -> DailyConsumerState) {
        _uiState.value = _uiState.value.update()
    }

    private fun handleEvent(event: DailyActivityForm) {
        when(event) {
            is DailyActivityForm.OnConsumerName-> updateUI{ copy(consumerName = event.consumerName)}
            is DailyActivityForm.OnPhoneNumber-> updateUI { copy(phoneNumber = event.phoneNumber) }
            is DailyActivityForm.OnGender->updateUI { copy(gender = event.gender)}
            is DailyActivityForm.OnAnySales->updateUI{copy(anySales = event.anySales)}
            is DailyActivityForm.OnPersonalBrands->updateUI { copy(personalBrands = event.personalBrands) }
            is DailyActivityForm.OnAge->updateUI { copy(age = event.age) }
            is DailyActivityForm.OnSampleSuper->updateUI {copy(sampleSuper = event.sampleSuper)}
            is DailyActivityForm.OnSampleMenthol->updateUI {copy(sampleMenthol = event.sampleMenthol)}
            is DailyActivityForm.OnSampleExec-> updateUI{copy(sampleExec = event.sampleExec)}
            is DailyActivityForm.OnSampleExecClick-> updateUI{copy(sampleExecClick = event.sampleExecClick)}
            is DailyActivityForm.OnQtySoldSuperUOM->updateUI {copy(qtySoldSuperUOM = event.qtySoldSuperUOM)}
            is DailyActivityForm.OnQtySoldSuper->updateUI {copy(qtySoldSuper = event.qtySoldSuper)}
            is DailyActivityForm.OnQtySoldMentholUOM->updateUI {copy(qtySoldMentholUOM = event.qtySoldMentholUOM)}
            is DailyActivityForm.OnQtySoldMenthol->updateUI {copy(qtySoldMenthol = event.qtySoldMenthol)}
            is DailyActivityForm.OnQtySoldExecUOM->updateUI {copy(qtySoldExecUOM = event.qtySoldExecUOM)}
            is DailyActivityForm.OnQtySoldExec->updateUI {copy(qtySoldExec = event.qtySoldExec)}
            is DailyActivityForm.OnQtySoldExecClickUOM->updateUI {copy(qtySoldExecClickUOM = event.qtySoldExecClickUOM)}
            is DailyActivityForm.OnQtySoldExecClick->updateUI {copy(qtySoldExecClick = event.qtySoldExecClick)}
            is DailyActivityForm.OnPMBLTH->updateUI {copy(PMBLTH = event.PMBLTH)}
            is DailyActivityForm.OnPMBPEN->updateUI {copy(PMBPEN = event.PMBPEN)}
            is DailyActivityForm.OnPMBWB->updateUI {copy(PMBWB = event.PMBWB)}
            is DailyActivityForm.OnComment->updateUI { copy(comment = event.comment) }
            is DailyActivityForm.OnConfirmEvent-> acknowledgement()
            is DailyActivityForm.HideOptionalDialog-> updateUI{copy(confirmDialog = false)}
            is DailyActivityForm.HideSuccessfulDialog-> updateUI{copy(success = false)}
            is DailyActivityForm.OnSetCustomerIdAndURNO -> updateUI{copy(urno = event.urno, customerId = event.customerId)}
            is DailyActivityForm.SyncDataToServer-> syncDataToServer()
            is DailyActivityForm.OnAcknowledge->updateUI { copy(acknowledge = event.acknowledge) }
            is DailyActivityForm.OnShowAndHideErrorMessage->updateUI { copy(showAndHideErrorMessage = event.disMiss) }
        }
    }

    private fun acknowledgement() {

        if(_uiState.value.acknowledge == "Not Confirm") {
            updateUI { copy(showAndHideErrorMessage = true, errorMessage = "Kindly agree acknowledge the agreement before you can proceed with submission") }
            return
        }

        updateUI{copy(confirmDialog = true, showAndHideErrorMessage = false, errorMessage = "")}

    }

    private fun syncDataToServer() = viewModelScope.launch{

        try {
            updateUI { copy(loaders = true) }
            val getServerResponse = remoteRepo.taskDailyCustomer(isBody())

            getServerResponse.onSuccess { data ->
                if (data.status == 200) {
                    updateUI { copy(loaders = false, success = true) }
                } else {
                    updateUI {
                        copy(
                            loaders = false,
                            showAndHideErrorMessage = true,
                            errorMessage = data.message
                        )
                    }
                }
            }.onFailure { error->
                updateUI {
                    copy(
                        loaders = false,
                        showAndHideErrorMessage = true,
                        errorMessage = error.message.toString()
                    )
                }
            }

        } catch (e: Exception) {
            updateUI {
                copy(
                    loaders = false,
                    showAndHideErrorMessage = true,
                    errorMessage = e.message.toString()
                )
            }
        }
    }

    private suspend fun isBody(): DailyCustomerDTO {
        val state = _uiState.value
        return withContext(Dispatchers.IO) {
            val supervisorCategoryId = sharePreference.getInt("sysCategory", 0)
            val supervisorId = sharePreference.getInt("id", 0)
            DailyCustomerDTO(
                urno = state.urno,
                supervisorCategoryId = supervisorCategoryId,
                supervisorId = supervisorId,
                customerId = state.customerId,
                consumerName = state.consumerName,
                phoneNumber = state.phoneNumber,
                gender = state.gender,
                age = state.age,
                sampleSuper = state.sampleSuper,
                sampleMenthol = state.sampleMenthol,
                sampleExec = state.sampleExec,
                sampleExecClick = state.sampleExecClick,
                anySales = state.anySales,
                personalBrands = state.personalBrands,
                qtySoldSuperUOM = state.qtySoldSuperUOM,
                qtySoldSuper = state.qtySoldSuper,
                qtySoldMentholUOM = state.qtySoldMentholUOM,
                qtySoldMenthol = state.qtySoldMenthol,
                qtySoldExecUOM = state.qtySoldExecUOM,
                qtySoldExec = state.qtySoldExec,
                qtySoldExecClickUOM = state.qtySoldExecClickUOM,
                qtySoldExecClick = state.qtySoldExecClick,
                PMBLTH = state.PMBLTH,
                PMBPEN = state.PMBPEN,
                PMBWB = state.PMBWB,
                comment = state.comment,
                acknowledge = state.acknowledge
            )
        }
    }
}