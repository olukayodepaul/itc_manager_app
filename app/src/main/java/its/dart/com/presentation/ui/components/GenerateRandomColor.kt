package its.dart.com.presentation.ui.components

import androidx.compose.ui.graphics.Color

fun generateRandomColor(): Color {
    val hue = (0..360).random().toFloat()
    return Color.hsv(hue, 0.7f, 0.9f)
}