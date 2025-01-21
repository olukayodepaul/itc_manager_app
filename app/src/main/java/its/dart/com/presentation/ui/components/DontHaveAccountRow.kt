package  its.dart.com.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import its.dart.com.presentation.ui.theme.dartSansFontFamily

@Composable
fun forgetPassword(
    onSignupTap: () -> Unit = {},
) {
    Row(
        modifier = Modifier.padding(top=12.dp, bottom = 52.dp)
    ){


        Text("Forget Password",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = dartSansFontFamily,
                fontWeight = FontWeight(400),
                color = Color.White
            ),
            modifier = Modifier.clickable {
                onSignupTap()
            }
        )
    }
}