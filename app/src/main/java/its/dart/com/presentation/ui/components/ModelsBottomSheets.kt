package its.dart.com.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import its.dart.com.R
import its.dart.com.presentation.ui.theme.lightGray
import its.dart.com.presentation.ui.theme.mainGray
import its.dart.com.presentation.ui.theme.robotoFamily

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
            sheetState = sheetState,
            containerColor = lightGray,

        ){
            Row(
                modifier = Modifier
                    .padding(20.dp),
                verticalAlignment = Alignment.Top
            ){

                Image(
                    painter = painterResource(id = R.drawable.fmd_bad_48px),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = errorMessage,
                    fontSize = 18.sp,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W600,
                    color = mainGray
                )

            }
        }
    }
}
