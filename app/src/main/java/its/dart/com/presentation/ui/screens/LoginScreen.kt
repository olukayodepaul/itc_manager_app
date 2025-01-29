package its.dart.com.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import its.dart.com.presentation.ui.components.CButton
import its.dart.com.presentation.ui.components.NavigationText
import its.dart.com.presentation.ui.components.TextFieldInput
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.appColorWhite
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer()
            .background(color = appColorBlack)

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(start = 12.dp, end = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {

        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                .fillMaxWidth()
                .weight(1f)
                .background(appColorWhite)
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp
                )
            ) {

                TextFieldInput(
                    value = uiState.username,
                    onValueChange = {username -> viewModel.onEvent(LoginUIEvent.OnUsername(username))},
                    hint = "Username"
                )

                Spacer(modifier = Modifier.height(16.dp))


                TextFieldInput(
                    value = uiState.password,
                    onValueChange = {password -> viewModel.onEvent(LoginUIEvent.OnPassword(password))},
                    hint = "Password",
                    type = true
                )

                Spacer(modifier = Modifier.height(23.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NavigationText(
                        onClick = {},
                    )
                }

                Spacer(modifier = Modifier.height(23.dp))

                CButton(
                    onClick = { viewModel.onEvent(LoginUIEvent.OnLoginClick) },
                    buttonState = uiState.buttonState,
                    text= "Login",
                )
            }
        }
    }
}

