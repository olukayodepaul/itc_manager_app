package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SalesRepScreen() {
    // Example layout for SalesRepScreen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Add padding to avoid overlaps
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Sales Representative Screen")
    }
}