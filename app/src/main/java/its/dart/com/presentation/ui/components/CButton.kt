package  its.dart.com.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import its.dart.com.presentation.ui.theme.dartSansFontFamily


@Composable
fun CButton(
    onClick: () -> Unit = {},
    text: String,
    containerColor: Color = Color(0xFF7C9A92)
) {

    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.extraLarge,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {

        Text(
            text = text,
            style = TextStyle(
                fontSize = 22.sp,
                fontFamily = dartSansFontFamily,
                fontWeight = FontWeight(500),
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