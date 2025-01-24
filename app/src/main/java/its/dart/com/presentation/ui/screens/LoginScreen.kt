package its.dart.com.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import its.dart.com.presentation.ui.theme.dartFontFamily
import its.dart.com.presentation.ui.theme.dartSansFontFamily


@Composable
fun LoginScreen(
    navController: NavHostController
) {

    val backgroundImage = painterResource(id = R.drawable.bg)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF09af00))
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
                        start = 20.dp
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

        }
    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}