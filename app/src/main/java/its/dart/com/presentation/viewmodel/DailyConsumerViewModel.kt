package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import its.dart.com.presentation.viewmodel.event.DailyActivityForm
import its.dart.com.presentation.viewmodel.state.DailyConsumerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DailyConsumerViewModel : ViewModel(){

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


        }
    }



}