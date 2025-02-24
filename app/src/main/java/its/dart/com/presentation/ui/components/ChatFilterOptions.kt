package its.dart.com.presentation.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import its.dart.com.presentation.ui.theme.mainGray
import its.dart.com.presentation.viewmodel.event.getPromoterSupervisor


@Composable
fun ChatFilterOptions(
    selectedId: Int,
    currentDay: Int,
    onSelectedChange: (Int) -> Unit
) {
    val options = getPromoterSupervisor()

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(options.size) { index ->
            val eachItems = options[index]
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        if (eachItems.id == selectedId) Color.Blue.copy(alpha = 0.2f)
                        else Color.LightGray.copy(alpha = 0.2f)
                    )
                    .clickable { if (currentDay in 2..7) onSelectedChange(eachItems.id) }
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = eachItems.day,
                        fontWeight = if (eachItems.id == selectedId) FontWeight.W900 else FontWeight.W600,
                        color = mainGray
                    )
                }
            }
        }
    }
}
