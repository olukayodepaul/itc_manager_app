package  its.dart.com.presentation.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import its.dart.com.presentation.ui.theme.dartSansFontFamily
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldInput(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    type: Boolean = false,
) {

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = value,
        maxLines = 1,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hint,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = dartSansFontFamily,
                    color = Color(0xFFBEC2C2)
                )
            )
        },
        visualTransformation = if (type && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = if (type) {
            {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible)
                            Icons.Filled.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            }
        } else null,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp)),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFEEEEEE),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp)
    )
}