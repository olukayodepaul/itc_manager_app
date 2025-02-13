package its.dart.com.presentation.ui.screens


import android.widget.CheckBox
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.material.textfield.TextInputEditText
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.appColorBlack
import its.dart.com.presentation.ui.theme.robotoFamily

@Composable
fun WholeSellerScreen(
    navController: NavHostController,
) {

    var counter by remember {mutableStateOf(0)}

    Scaffold(
        topBar = {
            ToolBar(
                title = "Wholesaler",
                click = { navController.popBackStack() },
                clickSearch = {},
                clickMenu = {},
                fontSize = 20,
                fontFamily = robotoFamily,
                letterSpacing = 0.5,
            )
        }
    ) { innerPadding ->
        AllWholeSeller(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )

    }
}

@Composable
fun AllWholeSeller(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {

        }

        FloatingActionButton(
            onClick = {
                val id = "123"
                val outletName = "Wholesaler"
                val quantity = 3
                navController.navigate("add_customer/${outletName}")
            },
            shape = CircleShape,
            containerColor = appColorBlack,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(70.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@Composable
fun SampleInput() {

    var inputMutable by remember {mutableStateOf("0")}
    var checkBox by remember {mutableStateOf("false")}
    var isCheck by remember {mutableStateOf(false)}
    var isArray: List<String> by remember {mutableStateOf(listOf("1", "2", "3"))}
    var counters by remember { mutableStateOf(0) }
    
    Text(
        text = "Counter $counters"
    )
    
    Button(onClick = {counters ++ }) {
        Text(text = "Counter")
    }

    //input field
    OutlinedTextField(
        value = inputMutable,
        onValueChange = {
            callBack ->
            inputMutable = callBack.ifBlank { "" }
        }
    )

    //check box
    Checkbox(
        checked = checkBox == "false",
        onCheckedChange = {
            callBack ->
            checkBox = if(callBack) "true" else "false"
        }
    )

    //this is the lis of all the student.
    DropdownMenuItem(
        text = {},
        onClick = { }
    )

}






