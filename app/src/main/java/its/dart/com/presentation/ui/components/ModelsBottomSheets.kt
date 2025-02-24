package its.dart.com.presentation.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelsBottomSheets(
    showAndHideErrorMessage: Boolean,
    onSheetStateChange: (Boolean) -> Unit,
    sheetState: SheetState,
    errorMessage: String
){

    LaunchedEffect(sheetState.currentValue) {
        if (sheetState.currentValue == SheetValue.Hidden) {
            onSheetStateChange(false)
        }
    }

    if (showAndHideErrorMessage) {
        ModalBottomSheet(
            onDismissRequest = {onSheetStateChange(false)},
            sheetState = sheetState
        ){
            Text(
                text = errorMessage,
                fontSize = 20.sp
            )
        }
    }
}
