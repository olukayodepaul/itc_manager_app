package its.dart.com.presentation.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun OptionalDialog(
    btDismissState: Boolean,
    btDismissEvent: () -> Unit,
    btConfirmEvent: () -> Unit,
    text: String,
    btConfirmTitle: String,
    btDismissTitle: String,
) {
    if (btDismissState) {
        AlertDialog(
            onDismissRequest = {},
            text = { Text(text = text) },
            confirmButton = {
                Button(onClick = {
                    btConfirmEvent()
                }) {
                    Text(text = btConfirmTitle)
                }
            },
            dismissButton = {
                Button(onClick = {
                    btDismissEvent()
                }) {
                    Text(text = btDismissTitle)
                }
            }
        )
    }
}
