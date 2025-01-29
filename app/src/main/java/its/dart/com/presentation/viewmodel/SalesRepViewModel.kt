package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.data.repository.local.configuration.RoomDatabaseTable
import its.dart.com.data.repository.local.entity.SalesRepsEntity
import its.dart.com.domain.repository.remote.model.RepsModel
import its.dart.com.domain.usecases.RepUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SalesRepViewModel @Inject constructor(private val repUseCase: RepUseCases): ViewModel() {

    private val _salesReps = MutableStateFlow<List<RepsModel>>(emptyList())
    val salesReps: StateFlow<List<RepsModel>> = _salesReps.asStateFlow()

    init{
        fetchSalesReps()
    }

    private fun fetchSalesReps() {
        viewModelScope.launch {
            repUseCase.fetchSalesReps().collect { repsList ->
                _salesReps.value = repsList
            }
        }
    }

}