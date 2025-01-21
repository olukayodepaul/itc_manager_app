package its.dart.com.presentation.viewmodel

import androidx.lifecycle.ViewModel
import its.dart.com.domain.use_cases_intr.LoginUserCases
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val getLoginUserCases: LoginUserCases
) : ViewModel() {



}