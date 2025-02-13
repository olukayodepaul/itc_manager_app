package its.dart.com.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import its.dart.com.presentation.ui.components.CButton
import its.dart.com.presentation.ui.components.TextFieldInput
import its.dart.com.presentation.ui.theme.KanitMedium
import its.dart.com.presentation.viewmodel.LoginViewModel
import its.dart.com.presentation.viewmodel.event.LoginUIEvent

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navigateToHome by viewModel.navigateToHome.collectAsStateWithLifecycle()

    LaunchedEffect(navigateToHome) {
        if (navigateToHome) {
            navController.navigate("MainPage") {
                popUpTo("homePage") { inclusive = true }
            }
            viewModel.resetNavigation()
        }
    }

    Scaffold { innerPadding ->
        Box( // Wrap everything in a Box for centering
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFFFFFFF)),
            contentAlignment = Alignment.Center // Centers CircularLogo
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(100.dp))
                CircularLogo()
                Spacer(modifier = Modifier.height(50.dp))

                //error message in the compose ui
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = uiState.isErrorMessageState.ifEmpty { "" },
                )

                //Dialog with a loader

                TextFieldInput(
                    value = uiState.usernameState,
                    onValueChange = { username -> viewModel.onEvent(LoginUIEvent.OnUsername(username)) },
                    hint = "Username"
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldInput(
                    value = uiState.passwordState,
                    onValueChange = { password -> viewModel.onEvent(LoginUIEvent.OnPassword(password)) },
                    hint = "Password",
                    type = true
                )

                Spacer(modifier = Modifier.height(23.dp))

                CButton(
                    buttonState = uiState.buttonState,
                    onClick = { viewModel.onEvent(LoginUIEvent.OnLoginClick) },
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

@Composable
fun CircularLogo() {
    Box(
        modifier = Modifier
            .size(70.dp)
            .clip(CircleShape)
            .background(Color(0xFF008b00)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "SapApp",
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = KanitMedium,
            letterSpacing = (-2.1).sp,
            lineHeight = 24.sp,
        )
    }
}


