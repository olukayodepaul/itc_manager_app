package its.dart.com.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import its.dart.com.presentation.viewmodel.event.ChatFilter

@Composable
fun ChatFilterOptions(
    selectedFilter: ChatFilter,
    onFilterSelected: (ChatFilter) -> Unit,
) {
    val filters = listOf(
        ChatFilter.Monday,
        ChatFilter.Tuesday,
        ChatFilter.Wednesday,
        ChatFilter.Thursday,
        ChatFilter.Friday,
        ChatFilter.Saturday
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly) {
        filters.forEach { filter ->
            Text(
                text = filter.label,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onFilterSelected(filter) }
                    .background(
                        if (filter == selectedFilter) Color.Green else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp),
                color = if (filter == selectedFilter) Color.White else Color.Black
            )
        }
    }
}
