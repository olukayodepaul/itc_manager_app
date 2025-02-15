package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import its.dart.com.presentation.ui.navigation.BottomNavigation
import its.dart.com.presentation.ui.navigation.getTabItems


@Composable
fun MainScreen(navController: NavHostController) {

    val tabItems = remember { getTabItems(navController) }
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation(
                itemList = tabItems,
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { index -> selectedTabIndex = index }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            tabItems[selectedTabIndex].content()
        }
    }
}

