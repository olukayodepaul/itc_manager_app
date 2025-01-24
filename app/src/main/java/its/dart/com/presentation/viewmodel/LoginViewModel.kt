package its.dart.com.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import its.dart.com.domain.usecases.LoginUseCases

import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val getLoginUserCases: LoginUseCases
) : ViewModel() {

    //
    fun login(username: String, password: String) = viewModelScope.launch{

    }

}

//private val mutableStateFlow = MutableStateFlow<String>("")
//val stateFlow: StateFlow<String> = mutableStateFlow
//
//fun main(){
//
//    GlobalScope.launch {
//
//        stateFlow.collectLatest {
//            print(it);
//        }
//    }
//
//}