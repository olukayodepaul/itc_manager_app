package its.dart.com.presentation.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun OptionalDialog(
    btDismissState: Boolean,
    text: String,
    btConfirmTitle: String,
    btDismissTitle: String,

//    btDismissEvent: (Boolean) -> Unit,
//    btLeftOnClick: () -> Unit
) {
    if (btDismissState) {
        AlertDialog(
            onDismissRequest = {},
            text = { Text(text = text) },
            confirmButton = {
                Button(onClick = {

                }) {
                    Text(text = btConfirmTitle)
                }
            },
            dismissButton = {
                Button(onClick = {

                }) {
                    Text(text = btDismissTitle)
                }
            }
        )
    }
}
