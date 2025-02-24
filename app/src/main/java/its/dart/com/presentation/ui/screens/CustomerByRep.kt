package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import its.dart.com.domain.repository.remote.model.AllCustomersModel
import its.dart.com.presentation.ui.components.ChatFilterOptions
import its.dart.com.presentation.ui.components.CircleAvatar
import its.dart.com.presentation.ui.components.DropdownMenuWithDetails
import its.dart.com.presentation.ui.components.Failure
import its.dart.com.presentation.ui.components.LoadingIndicator
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.appColor
import its.dart.com.presentation.ui.theme.appColorWhite
import its.dart.com.presentation.ui.theme.mainGray
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.CustomerByRepViewModel
import its.dart.com.presentation.viewmodel.state.GenericState
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerByRep(
    navController: NavHostController,
    repId: String,
    repName: String,
    routId: String,
    viewModel: CustomerByRepViewModel = hiltViewModel()
) {
    val customersState by viewModel.customersState.collectAsState()
    val selectedId by viewModel.optionState.collectAsState()
    val calendar = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

    LaunchedEffect(repId) {
        if(calendar in 2..7) {
            viewModel.getCustomers(repId.toInt(), calendar - 1)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getCurrentOptionState()
    }

    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var changeSearchAndTooBar by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if(changeSearchAndTooBar) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    query = text,
                    onQueryChange = { text = it },
                    onSearch = { active = true },
                    active = active,
                    trailingIcon = {
                                   Icon(
                                       modifier = Modifier.clickable {
                                                 text = ""
                                       },
                                       imageVector = Icons.Default.Cancel ,
                                       contentDescription = null,
                                       tint = mainGray,
                                   )
                    },
                    leadingIcon = {
                       Row(modifier  = Modifier.padding(horizontal = 10.dp)){
                           Icon(imageVector = Icons.Default.ArrowBack,
                               tint = mainGray,
                               contentDescription = null,
                               modifier  = Modifier.clickable {
                                   changeSearchAndTooBar = false
                               })

                           Spacer(modifier = Modifier.width(8.dp))

                           Icon(
                               imageVector = Icons.Default.Search,
                               tint = mainGray.copy(alpha = 0.4f),
                               contentDescription = null)
                       }
                    },
                    onActiveChange = { },
                    colors = SearchBarDefaults.colors(
                        containerColor = mainGray.copy(alpha = 0.1f),
                    ),
                    placeholder = {
                        Text(
                            style = TextStyle(
                                fontSize = 15.sp,
                                color = mainGray,
                                fontWeight = FontWeight.W900,
                                fontFamily = robotoFamily
                            ),
                            text = "Ask Ai or Search Me"
                        )
                    }
                )
                {
                    Column {

                    }
                }
            }else{
                ToolBar(
                    title = "$repName",
                    click = {
                        navController.popBackStack()
                    },
                    clickSearch = {
                        changeSearchAndTooBar = true
                    },
                    clickMenu = {},
                    navigation = true,
                    searchIcon = true,
                    fontSize = 22,
                    fontFamily = robotoFamily,
                    letterSpacing = -0.2,
                    subTitleItem = routId,
                    subTitle = true,
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("add_customer/$repId/$repName")
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            ChatFilterOptions(
                selectedId = selectedId,
                currentDay = calendar,
                onSelectedChange = { viewModel.updateOptionState(it, repId.toInt())}
            )
            Spacer(modifier = Modifier.height(10.dp))
            when (val state = customersState) {
                is GenericState.Loading -> {
                    if(calendar in 2..7){
                        LoadingIndicator()
                    }
                }
                is GenericState.Success -> {
                    CustomerList(state.data, navController)
                }
                is GenericState.Failure -> {
                    Failure(
                        errorMessage = state.exception.message ?: "Unknown error",
                        onRetry = { }
                    )
                }
            }
        }
    }
}

@Composable
fun CustomerList(
    customers: List<AllCustomersModel>,
    navController: NavHostController
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = rememberLazyListState()
    ) {
        items(
            items = customers,
            key = { it.id }
        ) { customer ->
            ContentList(customer, navController)
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Security,
                    tint = mainGray,
                    contentDescription = "More options",
                    modifier = Modifier.size(20.dp)
                )
                ClickableText(
                    text = AnnotatedString("Data are protected inline with our data policy"),
                    onClick = {},
                    style = TextStyle(
                        fontSize = 13.sp,
                        color = mainGray,
                        fontWeight = FontWeight.W300
                    ),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun ContentList(
    salesRep: AllCustomersModel,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircleAvatar(
                initialName = salesRep.outletName,
                id = salesRep.id
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = salesRep.outletName,
                    fontWeight = FontWeight(400),
                    maxLines = 1,
                    fontSize = 18.sp,
                    color = appColor,
                )
                Text(
                    text = "${salesRep.outletAddress} ${salesRep.urno} ${salesRep.contactPhone}",
                    maxLines = 1,
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
            }
            DropdownMenuWithDetails(navController, salesRep)
        }
    }
}