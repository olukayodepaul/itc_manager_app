package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.data.repository.remote.dto.DailyActivityViewDTO
import its.dart.com.domain.preference.PreferenceInt
import its.dart.com.domain.repository.remote.DailyActivityInt
import its.dart.com.presentation.viewmodel.event.DailyRetailActivityEvent
import its.dart.com.presentation.viewmodel.event.PackPlacementEvent
import its.dart.com.presentation.viewmodel.state.DailyRetailActivityState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DailyActivityViewModel @Inject constructor(
    val remoteRepo: DailyActivityInt,
    private val sharePreference: PreferenceInt
): ViewModel() {

    private val _uiState = MutableStateFlow(DailyRetailActivityState())
    val uiState: StateFlow<DailyRetailActivityState> = _uiState.asStateFlow()

    fun onEvent(event: DailyRetailActivityEvent) = viewModelScope.launch{
        eventHandler(event)
    }

    private fun updateUI(update: DailyRetailActivityState.() -> DailyRetailActivityState) {
        _uiState.value = _uiState.value.update()
    }

    private fun eventHandler(event: DailyRetailActivityEvent) {
            when (event) {
                // Stock Out Events
                is DailyRetailActivityEvent.OnTGTSuperStockOut -> updateUI{copy(tTGTSuperStockOut = event.tTGTSuperStockOut)}
                is DailyRetailActivityEvent.OnTGTMLTStockOut -> updateUI{copy(tTGTMLTStockOut = event.tTGTMLTStockOut)}
                is DailyRetailActivityEvent.OnExecKSStockOut -> updateUI{copy(execKSStockOut = event.execKSStockOut)}
                is DailyRetailActivityEvent.OnExecCKStockOut -> updateUI{copy(execCKStockOut = event.execCKStockOut)}

                // Sales Made Events
                is DailyRetailActivityEvent.OnTGTSuperSalesMade -> updateUI{copy(tTGTSuperSalesMade = event.tTGTSuperSalesMade)}
                is DailyRetailActivityEvent.OnTGTMLTSalesMade -> updateUI{copy(tTGTMLTSalesMade = event.tTGTMLTSalesMade)}
                is DailyRetailActivityEvent.OnExecKSSalesMade ->updateUI{ copy(execKSSalesMade = event.execKSSalesMade)}
                is DailyRetailActivityEvent.OnExecCKSalesMade -> updateUI{copy(execCKSalesMade = event.execCKSalesMade)}
                is DailyRetailActivityEvent.OnTGTSuperSalesMadeUOM -> updateUI{copy(tTGTSuperSalesMadeUOM = event.tTGTSuperSalesMadeUOM)}
                is DailyRetailActivityEvent.OnTGTMLTSalesMadeUOM -> updateUI{copy(tTGTMLTSalesMadeUOM = event.tTGTMLTSalesMadeUOM)}
                is DailyRetailActivityEvent.OnExecKSSalesMadeUOM -> updateUI{copy(execKSSalesMadeUOM = event.execKSSalesMadeUOM)}
                is DailyRetailActivityEvent.OnExecCKSalesMadeUOM -> updateUI{copy(execCKSalesMadeUOM = event.execCKSalesMadeUOM)}

                // ITC SalesMan Event
                is DailyRetailActivityEvent.OnITCSalesMan -> updateUI{ copy(itcSalesMan = event.itcSalesMan)}

                // Reward Events
                is DailyRetailActivityEvent.OnTGTSuperReward -> updateUI{copy(tTGTSuperReward = event.tTGTSuperReward)}
                is DailyRetailActivityEvent.OnTGTMLTReward -> updateUI{copy(tTGTMLTReward = event.tTGTMLTReward)}
                is DailyRetailActivityEvent.OnExecKSReward -> updateUI{copy(execKSReward = event.execKSReward)}
                is DailyRetailActivityEvent.OnExecCKReward -> updateUI{copy(execCKReward = event.execCKReward)}
                is DailyRetailActivityEvent.OnTGTSuperRewardUOM -> updateUI{copy(tTGTSuperRewardUOM = event.tTGTSuperRewardUOM)}
                is DailyRetailActivityEvent.OnTGTMLTRewardUOM -> updateUI{copy(tTGTMLTRewardUOM = event.tTGTMLTRewardUOM)}
                is DailyRetailActivityEvent.OnExecKSRewardUOM -> updateUI{copy(execKSRewardUOM = event.execKSRewardUOM)}
                is DailyRetailActivityEvent.OnExecCKRewardUOM -> updateUI{copy(execCKRewardUOM = event.execCKRewardUOM)}

                // Sampling Events
                is DailyRetailActivityEvent.OnExecKSSampling -> updateUI{copy(execKSSampling = event.execKSSampling)}
                is DailyRetailActivityEvent.OnExecCKSampling -> updateUI{copy(execCKSampling = event.execCKSampling)}

                //React to an event
                is DailyRetailActivityEvent.OnConfirmEvent-> updateUI{copy(confirmDialog = true)}
                is DailyRetailActivityEvent.HideOptionalDialog-> updateUI{copy(confirmDialog = false)}
                is DailyRetailActivityEvent.HideSuccessfulDialog-> updateUI{copy(success = false)}
                is DailyRetailActivityEvent.OnSetCustomerIdAndURNO -> updateUI{copy(urno = event.urno, customerId = event.customerId)}
                is DailyRetailActivityEvent.SyncDataToServer-> syncDataToServer()
            }
    }

    private fun syncDataToServer() = viewModelScope.launch{

        try {
            updateUI { copy(loaders = true) }
            val getServerResponse = remoteRepo.taskDailyRetail(isBody())

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

    private suspend fun isBody(): DailyActivityViewDTO {
        val state = _uiState.value
        return withContext(Dispatchers.IO) {
            val supervisorCategoryId = sharePreference.getInt("sysCategory", 0)
            val supervisorId = sharePreference.getInt("id", 0)
            DailyActivityViewDTO(
                urno = state.urno,
                supervisorCategoryId = supervisorCategoryId,
                supervisorId = supervisorId,
                customerId = state.customerId,
                tTGTSuperStockOut = state.tTGTSuperStockOut,
                tTGTMLTStockOut = state.tTGTMLTStockOut,
                execKSStockOut = state.execKSStockOut,
                execCKStockOut = state.execCKStockOut,
                tTGTSuperSalesMadeUOM = state.tTGTSuperSalesMadeUOM,
                tTGTSuperSalesMade = state.tTGTSuperSalesMade,
                tTGTMLTSalesMadeUOM = state.tTGTMLTSalesMadeUOM,
                tTGTMLTSalesMade = state.tTGTMLTSalesMade,
                execKSSalesMadeUOM = state.execKSSalesMadeUOM,
                execKSSalesMade = state.execKSSalesMade,
                execCKSalesMadeUOM = state.execCKSalesMadeUOM,
                execCKSalesMade = state.execCKSalesMade,
                itcSalesMan = state.itcSalesMan,
                tTGTSuperRewardUOM = state.tTGTSuperRewardUOM,
                tTGTSuperReward = state.tTGTSuperReward,
                tTGTMLTRewardUOM = state.tTGTMLTRewardUOM,
                tTGTMLTReward = state.tTGTMLTReward,
                execKSRewardUOM = state.execKSRewardUOM,
                execKSReward = state.execKSReward,
                execCKRewardUOM = state.execCKRewardUOM,
                execCKReward = state.execCKReward,
                execKSSampling = state.execKSSampling,
                execCKSampling = state.execCKSampling
            )
        }
    }
}