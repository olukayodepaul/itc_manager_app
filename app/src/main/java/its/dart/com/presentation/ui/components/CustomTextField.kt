package its.dart.com.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.robotoFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: ImageVector,
    fontSize: TextUnit = 14.sp,
    fontFamily: FontFamily = robotoFamily,
    fontWeight: FontWeight = FontWeight.W600,
    modifier: Modifier = Modifier,
    isNumericOnly: Boolean = false
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            modifier = Modifier.padding(bottom = 4.dp),
            fontSize = 15.sp,
            color = appColorBlack,
            fontWeight = FontWeight.W200,
            fontFamily = robotoFamily
        )
        OutlinedTextField(
            value = value,
            onValueChange = {onValueChange(it)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color(0xFFEEEEEE),
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isNumericOnly) KeyboardType.Number else KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { /* Handle action if needed */ }
            ),
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "Leading Icon",
                    modifier = Modifier.clickable { /* Handle leading icon click if needed */ }
                )
            },
            shape = RoundedCornerShape(12.dp),
            maxLines = 1,
            textStyle = TextStyle(
                fontFamily = robotoFamily,
                fontSize = 20.sp,
                color = Color.Black
            ),

        )
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextFields(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    fontSize: TextUnit = 14.sp,
    fontFamily: FontFamily = robotoFamily,
    fontWeight: FontWeight = FontWeight.W600,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            modifier = Modifier.padding(bottom = 4.dp),
            style = TextStyle(
                fontSize = fontSize,
                fontFamily = fontFamily,
                fontWeight = fontWeight
            )
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color(0xFFEEEEEE),
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray
            ),
            shape = RoundedCornerShape(12.dp)
        )
    }
}
