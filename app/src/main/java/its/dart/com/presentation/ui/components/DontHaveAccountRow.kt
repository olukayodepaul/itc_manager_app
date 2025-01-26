package  its.dart.com.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.dartSansFontFamily

@Composable
fun DontHaveAccountRow(
    onClick: () -> Unit = {},
) {
        Text(

            text = "Forget Password?",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = dartSansFontFamily,
                fontWeight = FontWeight(900),
                color = appColorBlack
            ),
            modifier = Modifier.clickable {
                onClick()
            }
        )
}

