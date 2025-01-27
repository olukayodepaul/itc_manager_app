package  its.dart.com.presentation.ui.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.dartSansFontFamily
import its.dart.com.presentation.ui.theme.robotoFamily


@Composable
fun CButton(
    onClick: () -> Unit = {},
    text: String,
    buttonState: Boolean,
    containerColor: Color = appColorBlack,
    fontWeight: FontWeight = FontWeight(600),
    fontSize: Int = 20,
) {

    val configuration = LocalConfiguration.current
    val screenWidthDp = remember { mutableStateOf(configuration.screenWidthDp) }
    val buttonHeight: Dp = if (screenWidthDp.value > 600) 56.dp else 48.dp

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(buttonHeight),
        enabled = buttonState
    ) {

        if(buttonState) {
            Text(
                text = text,
                style = TextStyle(
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight(700),
                    color = Color.White
                ),
                fontWeight= fontWeight,
                fontSize = fontSize.sp
            )
        }else{
            Text(
                text = "dc dn jcjdwnj",
                style = TextStyle(
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight(700),
                    color = Color.White
                ),
                fontWeight= fontWeight,
                fontSize = fontSize.sp
            )
        }
    }
}
