package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import its.dart.com.R
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.iconColor
import its.dart.com.presentation.ui.theme.mainGray
import its.dart.com.presentation.ui.theme.robotoFamily


@Composable
fun ErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.note_stack_48px),
            contentDescription = null,
            modifier = Modifier.size(120.dp) ,
        )

        Spacer(modifier = Modifier.height(45.dp))

        Text(
            text = "401 UnAuthorize",
            color = mainGray,
            fontSize = 23.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp),
            fontFamily = robotoFamily,
            fontWeight = FontWeight.W500
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "You do not have access to this resource",
            color = iconColor,
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp),
            fontFamily = robotoFamily,
            fontWeight = FontWeight.W400
        )

        Spacer(modifier = Modifier.height(30.dp))

        ClickableText(
            text = AnnotatedString("Synchronise customers"),
            onClick = {},
            style = TextStyle(
                fontSize = 18.sp,
                color = appColorBlack,
                fontWeight = FontWeight.W600
            )
        )

    }
}