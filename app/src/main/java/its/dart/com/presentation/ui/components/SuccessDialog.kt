package its.dart.com.presentation.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SuccessDialog(
    showDialog: Boolean,
    onOkClick: () -> Unit,
    title: String = "Success",
    texts: String = "Your submission was successful!",
    confirmButtonText: String = "Ok"
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(text = title) },
            text = { Text(text=  texts) },
            confirmButton = {
                Button(onClick = onOkClick) {
                    Text(text = confirmButtonText)
                }
            }
        )
    }
}