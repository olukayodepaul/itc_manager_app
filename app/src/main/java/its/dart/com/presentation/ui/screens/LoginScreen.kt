package its.dart.com.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.PieChartOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import its.dart.com.presentation.ui.components.CButton
import its.dart.com.presentation.ui.components.TextFieldInput
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.KanitBold
import its.dart.com.presentation.ui.theme.KanitMedium
import its.dart.com.presentation.ui.theme.appColor
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.LoginViewModel
import its.dart.com.presentation.viewmodel.event.LoginUIEvent


@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val navigateToHome by viewModel.navigateToHome.collectAsState()


    LaunchedEffect(navigateToHome) {
        if (navigateToHome) {
            navController.navigate("MainPage") {
                popUpTo("homePage") { inclusive = true }
            }
            viewModel.resetNavigation()
        }
    }

    Scaffold(
        topBar = {
            ToolBar(
                title = "SapApp",
                fontSize =  23,
                click = {},
                clickSearch = {},
                clickMenu = {},
                navigation = false
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFFFFFFFF))
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp
                )
            ) {

                Spacer(modifier = Modifier.height(130.dp))

                TextFieldInput(
                    value = uiState.username,
                    onValueChange = { username -> viewModel.onEvent(LoginUIEvent.OnUsername(username)) },
                    hint = "Username"
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldInput(
                    value = uiState.password,
                    onValueChange = { password -> viewModel.onEvent(LoginUIEvent.OnPassword(password)) },
                    hint = "Password",
                    type = true
                )

                Spacer(modifier = Modifier.height(23.dp))

                CButton(
                    onClick = { viewModel.onEvent(LoginUIEvent.OnLoginClick) },
                    buttonState = uiState.buttonState,
                    text = "Login",
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Design and build by dartSpatial Nig Ltd",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

