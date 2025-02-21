package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import its.dart.com.presentation.ui.navigation.BottomNavigation
import its.dart.com.presentation.ui.navigation.getTabItems
import its.dart.com.presentation.viewmodel.MainViewModel


@Composable
fun MainScreen(
    navController: NavHostController,
    viewMode: MainViewModel = hiltViewModel()
) {

    val tabItems = remember { getTabItems(navController) }
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }
    val getCategoryId by viewMode.userState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit){
        viewMode.updateUserId()
    }

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

