package  its.dart.com.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import its.dart.com.presentation.ui.theme.robotoFamily


@Composable
fun NavigationText(
    onClick: () -> Unit = {},
    text: String = "Reset Password",
    fontWeight: FontWeight = FontWeight(900),
    color: Color = Color(0xFF008b00),
    fontSize: Int = 17
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = robotoFamily,
            fontWeight = fontWeight,
            color = color
        ),
        modifier = Modifier.clickable {
            onClick()
        },
        fontSize = fontSize.sp,
    )
}

