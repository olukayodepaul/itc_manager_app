package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.data.repository.local.configuration.RoomDatabaseTable
import its.dart.com.data.repository.local.entity.SalesRepsEntity
import its.dart.com.domain.usecases.RepUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SalesRepViewModel @Inject constructor(private val repUseCase: RepUseCases): ViewModel() {



}