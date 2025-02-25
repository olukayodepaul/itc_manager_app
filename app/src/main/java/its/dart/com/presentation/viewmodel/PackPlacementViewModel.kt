package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.data.repository.remote.dto.PackPlacementDTO
import its.dart.com.domain.preference.PreferenceInt
import its.dart.com.domain.repository.PackPlacement
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

    private fun updatePackPlacementState(update: PackPlacementState.() -> PackPlacementState) {
        _uiState.value = _uiState.value.update()
    }

    private fun eventHandler(event: PackPlacementEvent) {
        updatePackPlacementState {
            when (event) {
                is PackPlacementEvent.SkuHandler -> copy(skuHandler = event.skuHandler)
                is PackPlacementEvent.FreePackPlacementTGTSuper -> copy(freePackPlacementTGTSuper = event.freePackPlacementTGTSuper)
                is PackPlacementEvent.FreePackPlacementTGTMLT -> copy(freePackPlacementTGTMLT = event.freePackPlacementTGTMLT)
                is PackPlacementEvent.FreePackPlacementExec -> copy(freePackPlacementExec = event.freePackPlacementExec)
                is PackPlacementEvent.QtyBought -> copy(qtyBought = event.qtyBought)
                is PackPlacementEvent.BikeSales -> copy(bikeSales = event.bikeSales)
                is PackPlacementEvent.SalesManID -> copy(salesManID = event.salesManID)
                is PackPlacementEvent.HideOptionalDialog -> copy(confirmDialog = false)
                is PackPlacementEvent.ShowSuccessfulDialog -> copy(success = true)
                is PackPlacementEvent.OnSetCustomerIdAndURNO -> copy(
                    urno = event.urno,
                    customerId = event.customerId
                )

                is PackPlacementEvent.OnShowAndHideErrorMessage -> copy(showAndHideErrorMessage = event.disMiss)
                is PackPlacementEvent.ShowOptionalDialog -> {
                    if (!isValidState(_uiState.value)) {
                        copy(
                            showAndHideErrorMessage = true,
                            errorMessage = "All fields are required! You must provide all inputs before adding a pack placement."
                        )
                    } else {
                        copy(confirmDialog = true)
                    }
                }

                is PackPlacementEvent.OnConfirmEvent -> {
                    onConfirmEvent()
                    this
                }
            }
        }
    }

    private fun onConfirmEvent() = viewModelScope.launch {
        try {
            updatePackPlacementState { copy(loaders = true) }
            val getServerResponse = remoteRepository.taskPackPlacement(isBody())

            getServerResponse.onSuccess { data ->
                if (data.status == 200) {
                    updatePackPlacementState { copy(loaders = false, success = true) }
                } else {
                    updatePackPlacementState {
                        copy(
                            loaders = false,
                            showAndHideErrorMessage = true,
                            errorMessage = data.message
                        )
                    }
                }
            }.onFailure { error ->
                updatePackPlacementState {
                    copy(
                        loaders = false,
                        showAndHideErrorMessage = true,
                        errorMessage = error.message.toString()
                    )
                }
            }
        } catch (e: Exception) {
            updatePackPlacementState {
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
                state.freePackPlacementExec.isNotBlank() &&
                state.qtyBought.isNotBlank() && state.qtyBought.all { it.isDigit() } &&
                state.bikeSales.isNotBlank() && state.bikeSales.all { it.isDigit() } &&
                state.salesManID.isNotBlank() && state.salesManID.all { it.isDigit() }
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
                qtyBought = state.qtyBought,
                bikeSales = state.bikeSales,
                salesManID = state.salesManID
            )
        }
    }

}