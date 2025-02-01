package its.dart.com.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import its.dart.com.presentation.ui.theme.appColorWhite
import its.dart.com.presentation.ui.theme.backgrondColor


@Composable
fun CircleAvatar(
    initialName: String,
    id: Int
) {
    val randomColor = remember(id) {generateRandomColor()}

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(45.dp)
            .background(randomColor)
            .border(
                width = 0.dp,
                color = backgrondColor,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${initialName.firstOrNull()}",
            color = appColorWhite,
            fontSize = 23.sp,
            fontWeight = FontWeight.W600
        )
    }
}