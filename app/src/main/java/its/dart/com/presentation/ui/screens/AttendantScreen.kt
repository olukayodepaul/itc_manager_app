package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.AssignmentReturned
import androidx.compose.material.icons.filled.AvTimer
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PieChartOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.TimeToLeave
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.PieChartOutline
import androidx.compose.material.icons.outlined.StackedLineChart
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.IconBgColor
import its.dart.com.presentation.ui.theme.IconBgColors
import its.dart.com.presentation.ui.theme.appColor
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.appColorWhite
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.AttendantViewModel

@Composable
fun AttendantScreen(

) {

    Scaffold(
        topBar = {
            ToolBar(
                title = "Analytics",
                click = {},
                clickSearch = {},
                clickMenu = {},
                fontSize = 18,
                fontFamily = robotoFamily,
                letterSpacing = -0.2
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
                .verticalScroll(rememberScrollState()),
        ) {
            RowOfCards()
            Spacer(modifier = Modifier.height(30.dp))
            RowHeader(title = "DailyTask", fontSize = 18)
            Spacer(modifier = Modifier.height(10.dp))
            RowTask(icon = Icons.Filled.Timelapse , title = "Resume", subTitle = "Welcome to today activity", click = {})
            RowTask(icon = Icons.Filled.TimeToLeave , title = "Out for Survey", subTitle = "Take a tour with a sales representative", click = {})
            RowTask(icon = Icons.Filled.AssignmentReturned , title = "Return from Survey", subTitle = "Back from a  tour with a sales representative", click = {})
            RowTask(icon = Icons.Filled.Home , title = "Close", subTitle = "Good night and hope to see you the next day", click = {})
        }
    }
}

@Composable
fun RowHeader(
    title: String,
    fontSize:Int = 12,

){
    Text(
        modifier =  Modifier
            .padding(horizontal = 10.dp),
        maxLines = 1,
        fontSize = fontSize.sp,
        color = appColor,
        text = title,
        fontFamily = robotoFamily,
        letterSpacing = (-1.2).sp
    )
}
@Composable
fun ElevatedCardM3(
    icon: ImageVector = Icons.Filled.PieChartOutline,
    color: Color = appColorBlack,
    title: String,
    modifier: Modifier = Modifier,
    count: String
) {
    ElevatedCard(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .background(color, shape = CircleShape)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = appColorWhite,
                        modifier = Modifier.size(12.dp)
                    )
                }
                Text(text = title,
                    modifier = Modifier
                        .padding(start = 8.dp),
                    fontSize = 11.sp
                )
            }
            Text(
                text = count,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun RowOfCards() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),

    ) {
        ElevatedCardM3(
            icon = Icons.Outlined.BarChart,
            color = IconBgColors,
            title = "T.Outlet",
            modifier = Modifier.weight(1f),
            count = "0"
        )

        ElevatedCardM3(
            icon = Icons.Outlined.PieChartOutline,
            color = appColorBlack,
            title = "T.Survey",
            modifier = Modifier.weight(1f),
            count = "0"
        )

        ElevatedCardM3(
            icon = Icons.Outlined.StackedLineChart,
            color = IconBgColor,
            title = "T.Order",
            modifier = Modifier.weight(1f),
            count = "0"
        )
    }
}

@Composable
fun RowTask(
    click:()->Unit,
    icon: ImageVector,
    title:String,
    subTitle:String
){

  Box(modifier = Modifier
      .fillMaxWidth()
      .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
  ) {
      Row(
          modifier = Modifier
              .clickable {click()}
              .fillMaxWidth()
              .background(Color.LightGray.copy(alpha = 0.05f), shape = RoundedCornerShape(1.dp)) // Even lighter background
              .border(0.5.dp, Color.Gray.copy(alpha = 0.2f), shape = RoundedCornerShape(1.dp))
              .padding(horizontal = 10.dp, vertical = 10.dp),
          verticalAlignment = Alignment.CenterVertically
      ) {
          Icon(
              imageVector = icon,
              contentDescription = null,
              tint = appColorBlack,
              modifier = Modifier.size(30.dp) // Properly sized icon
          )

          Spacer(modifier = Modifier.width(12.dp)) // Space between Icon and Column

          Column(modifier = Modifier.weight(1f)) {
              Spacer(modifier = Modifier.height(3.dp))
              Text(
                  text = title,
                  color = Color.Black,
                  fontWeight = FontWeight.W600,
                  fontSize = 15.sp
              )
              Text(
                  text = subTitle,
                  color = Color.Gray.copy(alpha = 1f),
                  fontSize = 13.sp,
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis,
                  modifier = Modifier.padding(top = 0.dp)
              )
              Spacer(modifier = Modifier.height(3.dp))
          }

          Text(
              text = "00:00:00",
              color = Color.Gray.copy(alpha = 1f),
              fontSize = 11.sp
          )
      }
  }
}


//@Preview
//@Composable
//fun PreviewApp(){
//    AttendantScreen()
//}