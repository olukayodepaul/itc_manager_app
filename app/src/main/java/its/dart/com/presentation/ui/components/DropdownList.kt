package its.dart.com.presentation.ui.components



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.compose.ui.geometry.Size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import its.dart.com.presentation.ui.theme.robotoFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownLists(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector = Icons.Default.Info,
    trailingIcon: ImageVector = Icons.Default.ArrowDropDown,
    backgroundColor: Color = Color(0xFFEEEEEE),
    borderColor: Color = Color.LightGray,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    fontSize: TextUnit = 14.sp,
    fontFamily: FontFamily = robotoFamily,
    fontWeight: FontWeight = FontWeight.W600,
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    Text(
        text = label,
        modifier = Modifier.padding(bottom = 4.dp),
        style = TextStyle(
            fontSize = fontSize,
            fontFamily = fontFamily,
            fontWeight = fontWeight
        )
    )

    Box(modifier = Modifier.fillMaxWidth()) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .zIndex(1f)
                .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp)),
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }

        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp))
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "Leading Icon",
                    modifier = Modifier.clickable { /* Additional click handling if needed */ }
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = "Trailing Icon",
                    modifier = Modifier.clickable { expanded = true }
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = backgroundColor,
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor
            ),
            shape = shape
        )
    }
}
