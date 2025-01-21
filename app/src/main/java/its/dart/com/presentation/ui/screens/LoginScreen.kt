package its.dart.com.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import its.dart.com.R
import its.dart.com.presentation.ui.theme.dartFontFamily
import its.dart.com.presentation.ui.theme.dartSansFontFamily
import its.dart.com.presentation.ui.components.CTextField
import its.dart.com.presentation.ui.components.CButton
import its.dart.com.presentation.ui.components.forgetPassword



@Composable
fun LoginScreen(
    navController: NavHostController
) {

    Surface(
        color = Color(0xFF253334),
        modifier = Modifier.fillMaxSize()
    ) {


        Box(modifier =  Modifier.fillMaxSize()){
            /// Background Image

            Text(text = "Build and designed by dartSpatial Nig Ltd",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = dartSansFontFamily,
                    color = Color.White
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp)

            )

//            Image(
//                painter = painterResource(id = R.drawable.bg),
//                contentDescription = null,
//                contentScale = ContentScale.FillBounds,
//                modifier = Modifier.fillMaxSize()
//            )

            /// Content

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {

                // Logo
                Image(painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 54.dp)
                        .height(100.dp)
                        .align(Alignment.Start)
                        .offset(x = (-20).dp)
                )

                Text(text = "Sign In",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = dartFontFamily,
                        fontWeight = FontWeight(500),
                        color = Color.White
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )

                Text("Sign In now to access your exercises and saved music.",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = dartSansFontFamily,
                        color = Color(0xB2FFFFFF)
                    ),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 24.dp)
                )


                // Text Field
                CTextField(hint = "Email Address", value = "" )

                CTextField(hint = "Password", value = "" )

                Spacer(modifier = Modifier.height(24.dp))

                CButton(
                    text = "Sign In",
                    containerColor = Color(0xFF000000)
                )

                forgetPassword(
                    onSignupTap = {
                        navController.navigate("signup")
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}