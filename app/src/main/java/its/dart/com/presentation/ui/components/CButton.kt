package  its.dart.com.presentation.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import its.dart.com.presentation.ui.theme.dartSansFontFamily


@Composable
fun CButton(
    onClick: () -> Unit = {},
    text: String,
    containerColor: Color = Color(0xFF7C9A92)
) {

    val configuration = LocalConfiguration.current
    val screenWidthDp = remember { mutableStateOf(configuration.screenWidthDp) }
    val buttonHeight: Dp = if (screenWidthDp.value > 600) 56.dp else 48.dp

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ),
//        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(buttonHeight)
    ) {

        Text(
            text = text,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = dartSansFontFamily,
                fontWeight = FontWeight(700),
                color = Color.White
            )
        )

    }
}

@Preview
@Composable
fun cButtonPreview() {
    CButton(
        text = "Sign In",
        containerColor = Color(0xFF000000)
    )
}