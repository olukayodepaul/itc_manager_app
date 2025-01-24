package its.dart.com.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import its.dart.com.R
import its.dart.com.presentation.ui.components.CTextField
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.appColorGray
import its.dart.com.presentation.ui.theme.appColorWhite
import its.dart.com.presentation.ui.theme.dartFontFamily
import its.dart.com.presentation.ui.theme.dartSansFontFamily
import its.dart.com.presentation.ui.theme.lightGray


@Composable
fun LoginScreen(
    navController: NavHostController
) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = appColorBlack)
//            .paint(painter = backgroundImage, contentScale = ContentScale.Crop)
    ) {

        Box(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(
                        start = 15.dp
                    ),
                text = "Sign In",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = dartSansFontFamily,
                    fontWeight = FontWeight(weight = 700),
                    color = Color.White),
            )

        }

        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 15.dp,  // Radius for the top-left corner
                        topEnd = 15.dp
                    )
                )
                .background(color = Color(0xFFFFFFFF))
                .fillMaxWidth()
                .weight(1f)
        ) {

            Column (
                Modifier.padding(
                    start = 20.dp, end = 20.dp
                ),
                verticalArrangement = Arrangement.SpaceEvenly
            ){
                Text(
                    modifier = Modifier
                        .padding(
                            top = 25.dp
                        ),
                    text = "Welcome Back!",
                    fontWeight = FontWeight(weight = 900),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = dartSansFontFamily,
                        color = Color(0xFF424242)),
                )

                Text(
                    modifier = Modifier
                        .padding(
                            top = 8.dp,
                        ),
                    fontWeight = FontWeight(weight = 300),
                    text = "To keep connected with us please login with your assign information",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = dartFontFamily,
                        color = Color(0xFF424242)),
                )

                Spacer(modifier = Modifier.height(30.dp))

                Column {
                    CTextField(
                        value = username,
                        onValueChange = { username = it },
                        hint = "Username",
                        isPassword = false
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CTextField(
                        value = password,
                        onValueChange = { password = it },
                        hint = "Password",
                        isPassword = true
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}