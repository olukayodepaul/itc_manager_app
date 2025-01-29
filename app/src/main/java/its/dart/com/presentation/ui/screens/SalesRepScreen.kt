package its.dart.com.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import its.dart.com.domain.repository.remote.model.RepsModel
import its.dart.com.presentation.ui.theme.appColor
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.appColorWhite
import its.dart.com.presentation.viewmodel.SalesRepViewModel
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesRepScreen(
    viewModel: SalesRepViewModel = hiltViewModel()
) {
    val salesReps = viewModel.salesReps.collectAsState().value

    Column(modifier = Modifier.fillMaxSize()) {
        // Top App Bar
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = appColorBlack,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White,
            ),
            title = {
                Text(text = "Sales Person", fontWeight = FontWeight.Medium)
            },
            actions = {
                IconButton(onClick = { /* TODO: Implement Search */ }) {
                    Icon(Icons.Filled.Search, contentDescription = "Search", tint = appColorWhite)
                }
                IconButton(onClick = { /* TODO: Handle click */ }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More Options")
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(salesReps) { rep ->
                ContactListItem(rep) { eachItem ->
                    Log.d("Clicked on", "${eachItem.fullName}")
                }
            }
        }
    }
}

@Composable
fun ContactListItem(
    rep: RepsModel,
    onClick: (RepsModel) -> Unit
) {

    val actionTaken = 1
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(50.dp) // Circle size
                .clip(CircleShape) // Make the container circular
                .border(
                    width = 2.dp, // Border thickness
                    color = if (actionTaken == 1) appColorBlack else Color.Transparent, // Green border if there’s a new post
                    shape = CircleShape // Make the border circular
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "M", // First letter of the name or any text
                color = appColor, // Text color inside the circle
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )

        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = rep.fullName,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = "jnjcdnsacjds",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        // Time (e.g., last message time)
        Text(
            text = "time",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}

fun getRandomColor(minColorValue: Float = 0.3f, maxColorValue: Float = 0.8f): Color {
    return Color(
        Random.nextFloat() * (maxColorValue - minColorValue) + minColorValue, // Random Red value
        Random.nextFloat() * (maxColorValue - minColorValue) + minColorValue, // Random Green value
        Random.nextFloat() * (maxColorValue - minColorValue) + minColorValue  // Random Blue value
    )
}
