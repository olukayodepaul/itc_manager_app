package its.dart.com.presentation.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import its.dart.com.presentation.model.UiData
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.model.getPromoterSurvey
import its.dart.com.presentation.ui.theme.appColorWhite
import its.dart.com.presentation.ui.theme.backgrondColor
import its.dart.com.presentation.ui.theme.mainGray
import its.dart.com.presentation.ui.theme.robotoFamily


@Composable
fun Promo(
    navController: NavHostController
){
    Scaffold(
        topBar = {
            ToolBar(
                title = "Promoter",
                click = {},
                clickSearch = {},
                clickMenu = {},
                fontSize = 22,
                fontFamily = robotoFamily,
                letterSpacing = -0.2
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val userName = "Musa Muhammed"
                    val userId = "userid"
                          navController.navigate("add_customer/$userName/$userId")
                },
                containerColor = mainGray,
                shape = CircleShape,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = appColorWhite,
                    contentDescription = null
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
                .verticalScroll(rememberScrollState()),
        ) {
            SurveyCards(navController)
        }
    }
}




@Composable
fun SurveyCards(
    navController: NavHostController
){
    LazyRow{
        items(getPromoterSurvey(navController).size){ index->
            CardItem(index, getPromoterSurvey(navController))
        }
    }
    Spacer(modifier = Modifier.height(30.dp))
    Text(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        text = "My Customers",
        color = mainGray,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )

}

@Composable
fun CardItem(index: Int, item: List<UiData>) {
    val eachItems = item[index]
    val lastItemPaddingEnd = if (index == item.size - 1) 16.dp else 0.dp

    Box(
        modifier = Modifier.padding(start = 16.dp, end = lastItemPaddingEnd)
    ) {
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .clickable { eachItems.onClick() }
                .fillMaxWidth()
                .background(eachItems.color)
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = eachItems.icon,
                contentDescription = null,
                tint = backgrondColor
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = eachItems.title,
                color = backgrondColor,
                fontSize = 15.sp,
                fontWeight = FontWeight.W600,
            )
        }
    }
}


