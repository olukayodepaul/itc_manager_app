package its.dart.com.presentation.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import its.dart.com.data.repository.local.entity.PromoterEntity
import its.dart.com.presentation.model.UiData
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.model.getPromoterSurvey
import its.dart.com.presentation.ui.components.CircleAvatar
import its.dart.com.presentation.ui.components.DropdownMenuWithDetail
import its.dart.com.presentation.ui.theme.appColor
import its.dart.com.presentation.ui.theme.appColorWhite
import its.dart.com.presentation.ui.theme.backgrondColor
import its.dart.com.presentation.ui.theme.mainGray
import its.dart.com.presentation.ui.theme.robotoFamily
import its.dart.com.presentation.viewmodel.AddCustomerViewModel
import its.dart.com.presentation.viewmodel.event.AddCustomerViewEvent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Promo(
    navController: NavHostController,
    viewModel: AddCustomerViewModel = hiltViewModel(),
){

    //use the share preference
    val widgetUIState by viewModel.customersState.collectAsStateWithLifecycle()
    val userName by viewModel.userName.collectAsStateWithLifecycle()
    val userId by viewModel.userId.collectAsStateWithLifecycle()

    var changeSearchAndTooBar by remember { mutableStateOf(false) }
    val searchState by viewModel.search.collectAsStateWithLifecycle()
    var active by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.fetchPromoterCustomers()
    }

    Scaffold(
        topBar = {
            if (changeSearchAndTooBar) {

                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    query = searchState,
                    onQueryChange = { viewModel.onEvent(AddCustomerViewEvent.OnSearchEventPromoter(it)) },
                    onSearch = { active = true },
                    active = active,
                    trailingIcon = {
                        Icon(
                            modifier = Modifier.clickable {
                                viewModel.onEvent(AddCustomerViewEvent.OnResetSearchPromoter)
                                viewModel.onEvent(
                                    AddCustomerViewEvent.OnSearchEventPromoter(
                                        searchState = "",
                                        setToDefault = 1
                                    )
                                )
                            },
                            imageVector = Icons.Default.Cancel,
                            contentDescription = null,
                            tint = mainGray,
                        )
                    },
                    leadingIcon = {
                        Row(modifier = Modifier.padding(horizontal = 10.dp)) {
                            Icon(imageVector = Icons.Default.ArrowBack,
                                tint = mainGray,
                                contentDescription = null,
                                modifier = Modifier.clickable {
                                    changeSearchAndTooBar = false
                                    viewModel.onEvent(
                                        AddCustomerViewEvent.OnSearchEventPromoter(
                                            searchState = "",
                                            setToDefault = 1
                                        )
                                    )
                                })

                            Spacer(modifier = Modifier.width(8.dp))

                            Icon(
                                imageVector = Icons.Default.Search,
                                tint = mainGray.copy(alpha = 0.4f),
                                contentDescription = null
                            )
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
                ) {}
            } else {
                ToolBar(
                    title = "$userName",
                    click = {},
                    clickSearch = {
                        changeSearchAndTooBar = true
                    },
                    clickMenu = {},
                    fontSize = 22,
                    subTitle = true,
                    searchIcon = true,
                    fontFamily = robotoFamily,
                    letterSpacing = -0.2,
                    subTitleItem = "Promoters"
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("add_customer/$userId/${userName}")
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
        ) {
//            SurveyCards(navController)
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                state = rememberLazyListState()
            ) {
                items(
                    items = widgetUIState,
                    key = {it.id}
                ){itemList->
                    CustomerContentList(
                        salesRep = itemList,
                        navController = navController
                    )
                }
            }
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

@Composable
fun CustomerContentList(
    salesRep: PromoterEntity,
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
                initialName = salesRep.outlet_name.uppercase(),
                id = salesRep.id
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = salesRep.outlet_name.capitalize(),
                    fontWeight = FontWeight(400),
                    maxLines = 1,
                    fontSize = 18.sp,
                    color = appColor,
                )
                Text(
                    text = "${salesRep.outlet_address} ${salesRep.contact_phone}",
                    maxLines = 1,
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
            }
            DropdownMenuWithDetail(navController, salesRep)
        }
    }
}