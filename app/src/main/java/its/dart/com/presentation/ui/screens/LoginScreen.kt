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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import its.dart.com.presentation.ui.components.CButton
import its.dart.com.presentation.ui.components.LoadingDialog
import its.dart.com.presentation.ui.components.TextFieldInput
import its.dart.com.presentation.ui.theme.KanitMedium
import its.dart.com.presentation.ui.theme.lightGray
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.LoginViewModel
import its.dart.com.presentation.viewmodel.event.LoginViewEvent

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val widgetUIState by viewModel.uiState.collectAsStateWithLifecycle()
    val navigateToHome by viewModel.navController.collectAsState()

    LaunchedEffect(navigateToHome) {
        if (navigateToHome) {
            navController.navigate("MainPage") {
                popUpTo("homePage") { inclusive = true }
            }
            viewModel.resetNavigation()
        }
    }

    Scaffold(
        containerColor = lightGray
    ) { innerPadding ->
        Box( modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(modifier = Modifier.height(40.dp))
                CircularLogo()
                ErrorMessage(error = widgetUIState.errorMessage)
                InputText(
                    hint= "Username",
                    type= false,
                    value = widgetUIState.usernameTextBox,
                    onValueChange = {viewModel.onEvent(LoginViewEvent.OnUsernameTextBox(it))}
                )
                Spacer(modifier = Modifier.height(10.dp))
                InputText(
                    hint = "Password",
                    type = true,
                    value =  widgetUIState.passwordTextBox,
                    onValueChange = {viewModel.onEvent(LoginViewEvent.OnPasswordTextBox(it))}
                )
                Spacer(modifier = Modifier.height(10.dp))
                LoginButton(
                    click = {
                        viewModel.onEvent(LoginViewEvent.OnClickButton)
                    }
                )
                LoadingDialog(
                    showDialog = widgetUIState.dialogLoader,
                    onDismiss = {}
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

@Composable
fun ErrorMessage(
    error: String
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 10.dp),
        text = error,
        fontFamily = robotoFamily,
        fontSize = 14.sp,
        color = Color.Red,
        textAlign = TextAlign.Start
    )
}

@Composable
fun InputText(
    hint: String,
    type: Boolean,
    value: String,
    onValueChange: (String)->Unit
) {
    TextFieldInput(
        value = value ,
        onValueChange = {onValueChange(it)},
        hint = hint,
        type = type
    )
}

@Composable
fun LoginButton(
    click: ()->Unit
) {
    CButton(
        buttonState = true,
        onClick = click,
        text = "Login",
    )
}
