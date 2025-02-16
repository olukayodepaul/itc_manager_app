package its.dart.com.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import its.dart.com.presentation.ui.theme.KanitMedium
import its.dart.com.presentation.ui.theme.KanitSemiBold
import its.dart.com.presentation.ui.theme.appColor
import its.dart.com.presentation.ui.theme.appColorWhite
import its.dart.com.presentation.ui.theme.iconColor
import its.dart.com.presentation.ui.theme.lightGray
import its.dart.com.presentation.ui.theme.notosanskrboldFamily
import its.dart.com.presentation.ui.theme.otherIconColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(
    title: String,
    click: () -> Unit,
    clickSearch : ()->Unit,
    clickMenu:()->Unit,
    navigation: Boolean = false,
    searchIcon: Boolean = false,
    menuIcon: Boolean = false,
    subTitle: Boolean = false,
    fontSize: Int = 24,
    letterSpacing: Double = -2.1,
    fontFamily: FontFamily = KanitMedium,
    subTitleItem: String = ""

) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFFFFFFFF),
            titleContentColor = otherIconColor,
            navigationIconContentColor = otherIconColor,
            actionIconContentColor = otherIconColor,
        ),
        title = {
            Column {
                Text(
                    text = title,
                    fontSize = fontSize.sp,
                    color = Color(0xFF37474F),
                    fontFamily = fontFamily,
                    letterSpacing = (letterSpacing).sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.W900
                )
                if(subTitle){
                    Text(
                        text = subTitleItem,
                        fontSize = (fontSize-5).sp,
                        color = Color(0xFF37474F),
                        fontFamily = fontFamily,
                        letterSpacing = (letterSpacing).sp,
                        lineHeight = 24.sp,
                    )
                }
            }
        },

        navigationIcon = {
            if(navigation) {
                IconButton(onClick = click) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = Color.Gray.copy(alpha = 1f),
                        contentDescription = null)
                }
            }
        },

        actions = {
            if(searchIcon) {
                IconButton(onClick = clickSearch) {
                    Icon(Icons.Filled.Search,
                        tint = Color.Gray.copy(alpha = 1f),
                        contentDescription = null)
                }
            }
            if(menuIcon){
                IconButton(onClick = clickMenu) {
                    Icon(Icons.Default.MoreVert,
                        tint = Color.Gray.copy(alpha = 1f),
                        contentDescription = null)
                }
            }
        }
    )
}
