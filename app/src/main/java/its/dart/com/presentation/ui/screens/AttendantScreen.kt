package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentReturned
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PieChartOutline
import androidx.compose.material.icons.filled.TimeToLeave
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.PieChartOutline
import androidx.compose.material.icons.outlined.StackedLineChart
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import its.dart.com.presentation.ui.components.LoadingDialog
import its.dart.com.presentation.ui.components.RowTask
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.IconBgColor
import its.dart.com.presentation.ui.theme.IconBgColors
import its.dart.com.presentation.ui.theme.appColor
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.appColorWhite
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.TaskViewModel
import its.dart.com.presentation.viewmodel.event.TaskViewEvent


@Composable
fun AttendantScreen(
    viewModel : TaskViewModel =  hiltViewModel()
) {

    val widgetUIState by viewModel.taskUpdate.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            ToolBar(
                title = "Analytic",
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

            RowTask(icon = Icons.Filled.Timelapse , title = "Resume", subTitle = "When starting the day, the supervisor and other users are expected to mark their resumption",
                click = { viewModel.onEvent(TaskViewEvent.OnClickResume) },
                timeState = widgetUIState.resume
            )

            RowTask(icon = Icons.Filled.TimeToLeave , title = "Clock Out", subTitle = "Users must clock out when leaving for their daily tasks.",
                click = {viewModel.onEvent(TaskViewEvent.OnClickClockOut)},
                timeState = widgetUIState.clockOut
            )

            RowTask(icon = Icons.Filled.AssignmentReturned , title = "Clock In", subTitle = "Upon returning from their tasks, users should clock in to update their status",
                click = {viewModel.onEvent(TaskViewEvent.OnClickClockIn)},
                timeState = widgetUIState.clockIn
            )

            RowTask(icon = Icons.Filled.Home , title = "Close",
                subTitle = "At the end of the business day, users should finalize activities by clicking Close",
                click = {viewModel.onEvent(TaskViewEvent.OnClickClose)},
                timeState = widgetUIState.close
            )

            LoadingDialog(
                showDialog = widgetUIState.dialogLoader,
                onDismiss = {}
            )
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