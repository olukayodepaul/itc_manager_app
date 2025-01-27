package its.dart.com.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.dart.com.domain.usecases.LoginUseCases

import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getLoginUserCases: LoginUseCases
) : ViewModel() {

    fun login(username: String, password: String) = viewModelScope.launch{

    }

}