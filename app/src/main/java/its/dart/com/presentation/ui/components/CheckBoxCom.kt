package its.dart.com.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductCheckbox(
    option: String,
    checked: String,
    onCheckedChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Checkbox(
            checked = checked == "Confirm",
            onCheckedChange = {
                val newValue = if (it) "Confirm" else "Not Confirm"
                onCheckedChange(newValue)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF000000),
                uncheckedColor = Color.Gray,
                checkmarkColor = Color.White
            )
        )
        Text(
            text = option,
            fontSize = 13.sp
        )
    }
}
