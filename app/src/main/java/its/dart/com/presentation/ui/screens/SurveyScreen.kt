package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.appColorWhite


@Composable
fun SurveyScreen(
    navController: NavHostController,
    userId: String,
    userName: String
) {

    Scaffold(
        topBar = {
            SurveyToolBar(onBackClick = { navController.popBackStack() })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {

        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurveyToolBar(
    onBackClick: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = appColorBlack,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White,
        ),
        title = {
            Text(text = "Person", fontWeight = FontWeight.Medium)
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
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
}

