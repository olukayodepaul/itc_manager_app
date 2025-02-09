package its.dart.com.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import its.dart.com.presentation.ui.theme.appColorBlack


@Composable
fun RowTask(
    click:()->Unit,
    icon: ImageVector,
    title:String,
    subTitle:String,
    timeIcon: Boolean = true
){

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .clickable { click() }
                .fillMaxWidth()
                .background(
                    Color.LightGray.copy(alpha = 0.05f),
                    shape = RoundedCornerShape(1.dp)
                ) // Even lighter background
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

            if(timeIcon){
                Text(
                    text = "00:00:00",
                    color = Color.Gray.copy(alpha = 1f),
                    fontSize = 11.sp
                )
            }else{

                Icon(
                    imageVector = Icons.Filled.ArrowForwardIos,
                    contentDescription = null,
                    tint = appColorBlack,
                    modifier = Modifier.size(25.dp) // Properly sized icon
                )
            }
        }
    }
}
