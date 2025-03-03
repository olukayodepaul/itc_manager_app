package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.data.repository.remote.dto.PackPlacementDTO
import its.dart.com.domain.preference.PreferenceInt
import its.dart.com.domain.repository.remote.PackPlacement
import its.dart.com.presentation.viewmodel.event.PackPlacementEvent
import its.dart.com.presentation.viewmodel.state.PackPlacementState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class PackPlacementViewModel @Inject constructor(
    private val remoteRepository: PackPlacement,
    private val sharePreference: PreferenceInt
) : ViewModel() {

    private val _uiState = MutableStateFlow(PackPlacementState())
    val uiState: StateFlow<PackPlacementState> = _uiState.asStateFlow()

    fun event(event: PackPlacementEvent) {
        eventHandler(event)
    }

    private fun updateUi(update: PackPlacementState.() -> PackPlacementState) {
        _uiState.value = _uiState.value.update()
    }


    private fun eventHandler(event: PackPlacementEvent) {
            when (event) {
                is PackPlacementEvent.SkuHandler -> updateUi{copy(skuHandler = event.skuHandler)}
                is PackPlacementEvent.FreePackPlacementTGTSuper -> updateUi{copy(freePackPlacementTGTSuper = event.freePackPlacementTGTSuper)}
                is PackPlacementEvent.FreePackPlacementTGTMLT -> updateUi{copy(freePackPlacementTGTMLT = event.freePackPlacementTGTMLT)}
                is PackPlacementEvent.FreePackPlacementExec -> updateUi{copy(freePackPlacementExec = event.freePackPlacementExec)}
                is PackPlacementEvent.BikeSales -> updateUi{copy(bikeSales = event.bikeSales)}
                is PackPlacementEvent.SalesManID -> updateUi{copy(salesManID = event.salesManID)}
                is PackPlacementEvent.HideOptionalDialog -> updateUi{copy(confirmDialog = false)}
                is PackPlacementEvent.ShowSuccessfulDialog -> updateUi{copy(success = true)}
                is PackPlacementEvent.OnSetCustomerIdAndURNO -> updateUi{copy(urno = event.urno, customerId = event.customerId)}
                is PackPlacementEvent.OnShowAndHideErrorMessage -> updateUi{copy(showAndHideErrorMessage = event.disMiss)}
                is PackPlacementEvent.OnTGTSuperSalesMade -> updateUi{copy(tTGTSuperSalesMade = event.tTGTSuperSalesMade)}
                is PackPlacementEvent.OnTGTMLTSalesMade -> updateUi{copy(tTGTMLTSalesMade = event.tTGTMLTSalesMade)}
                is PackPlacementEvent.OnExecKSSalesMade ->updateUi{ copy(execKSSalesMade = event.execKSSalesMade)}
                is PackPlacementEvent.OnExecCKSalesMade -> updateUi{copy(execCKSalesMade = event.execCKSalesMade)}
                is PackPlacementEvent.OnTGTSuperSalesMadeUOM -> updateUi{copy(tTGTSuperSalesMadeUOM = event.tTGTSuperSalesMadeUOM)}
                is PackPlacementEvent.OnTGTMLTSalesMadeUOM -> updateUi{copy(tTGTMLTSalesMadeUOM = event.tTGTMLTSalesMadeUOM)}
                is PackPlacementEvent.OnExecKSSalesMadeUOM -> updateUi{copy(execKSSalesMadeUOM = event.execKSSalesMadeUOM)}
                is PackPlacementEvent.OnExecCKSalesMadeUOM -> updateUi{copy(execCKSalesMadeUOM = event.execCKSalesMadeUOM)}
                is PackPlacementEvent.ShowOptionalDialog -> {
                    if (!isValidState(_uiState.value)) {
                        updateUi{copy(
                            showAndHideErrorMessage = true,
                            errorMessage = "All fields are required! You must provide all inputs before adding a pack placement."
                        )}
                    } else {
                        updateUi{copy(confirmDialog = true, showAndHideErrorMessage = false)}
                    }
                }
                is PackPlacementEvent.OnConfirmEvent -> {
                    updateUi { copy(loaders = true, confirmDialog = false) }
                    onConfirmEvent()
                }
            }
    }

    private fun onConfirmEvent() = viewModelScope.launch {
        try {
            val getServerResponse = remoteRepository.taskPackPlacement(isBody())
            getServerResponse.onSuccess { data ->
                if (data.status == 200) {
                    updateUi { copy(loaders = false, success = true) }
                } else {
                    updateUi {
                        copy(
                            loaders = false,
                            showAndHideErrorMessage = true,
                            errorMessage = data.message
                        )
                    }
                }
            }.onFailure { error ->
                updateUi {
                    copy(
                        loaders = false,
                        showAndHideErrorMessage = true,
                        errorMessage = error.message.toString()
                    )
                }
            }
        } catch (e: Exception) {
            updateUi {
                copy(
                    loaders = false,
                    showAndHideErrorMessage = true,
                    errorMessage = e.message.toString()
                )
            }
        }
    }

    private fun isValidState(state: PackPlacementState): Boolean {
        return state.skuHandler.isNotBlank() &&
                state.freePackPlacementTGTSuper.isNotBlank() &&
                state.freePackPlacementTGTMLT.isNotBlank() &&
                state.freePackPlacementExec.isNotBlank()
    }

    private suspend fun isBody(): PackPlacementDTO {
        val state = _uiState.value
        return withContext(Dispatchers.IO) {
            val supervisorCategoryId = sharePreference.getInt("sysCategory", 0)
            val supervisorId = sharePreference.getInt("id", 0)
            PackPlacementDTO(
                urno = state.urno,
                supervisorCategoryId = supervisorCategoryId,
                supervisorId = supervisorId,
                customerId = state.customerId,
                skuHandler = state.skuHandler,
                freePackPlacementTGTSuper = state.freePackPlacementTGTSuper,
                freePackPlacementTGTMLT = state.freePackPlacementTGTMLT,
                freePackPlacementExec = state.freePackPlacementExec,
                bikeSales = state.bikeSales,
                salesManID = state.salesManID,
                tTGTSuperSalesMadeUOM = state.tTGTSuperSalesMadeUOM,
                tTGTSuperSalesMade = state.tTGTSuperSalesMade,
                tTGTMLTSalesMadeUOM = state.tTGTMLTSalesMadeUOM,
                tTGTMLTSalesMade = state.tTGTMLTSalesMade,
                execKSSalesMadeUOM = state.execKSSalesMadeUOM,
                execKSSalesMade = state.execKSSalesMade,
                execCKSalesMadeUOM = state.execCKSalesMadeUOM,
                execCKSalesMade = state.execKSSalesMade

            )
        }
    }




}