package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.zIndex
import its.dart.com.presentation.ui.components.CButton
import its.dart.com.presentation.ui.components.ToolBar
import its.dart.com.presentation.ui.theme.robotoFamily



@Composable
fun SurveyScreen(
    navController: NavHostController,
    userId: String,
    userName: String
) {
    Scaffold(
        topBar = {
            ToolBar(
                title = userName,
                click = { navController.popBackStack() },
                clickSearch = {},
                clickMenu = {},
                navigation = true,
                subTitle = true,
                fontSize = 20,
                fontFamily = robotoFamily,
                letterSpacing = 0.5,
                subTitleItem = "Customer Survey"
            )
        }
    ) { innerPadding ->
        // Pass navController to CustomerSurveyScreen for handling submit navigation
        CustomerSurveyScreen(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun CustomerSurveyScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            // Group 1: Rep Visit Frequency
            Row(
                modifier = Modifier.padding(bottom = 8.dp, start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberedCircle(number = 1)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Rep Visit Frequency",
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500
                )
            }
            VisitFrequencyQuestions()

            Spacer(modifier = Modifier.height(16.dp))

            // Group 2: Customer Satisfaction
            Row(
                modifier = Modifier.padding(bottom = 8.dp, start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberedCircle(number = 2)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Customer Satisfaction",
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500
                )
            }
            CustomerSatisfactionQuestions()

            Spacer(modifier = Modifier.height(16.dp))

            // Group 3: Sales Performance
            Row(
                modifier = Modifier.padding(bottom = 8.dp, start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberedCircle(number = 3)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Sales Performance",
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500
                )
            }
            SalesPerformanceQuestions()

            Spacer(modifier = Modifier.height(16.dp))

            // Group 4: Additional Feedback
            Row(
                modifier = Modifier.padding(bottom = 8.dp, start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberedCircle(number = 4)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Additional Feedback",
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500
                )
            }
            AdditionalFeedbackQuestions()

            Spacer(modifier = Modifier.height(10.dp))

            // Group 5: Product Availability on Sheff
            Row(
                modifier = Modifier.padding(bottom = 8.dp, start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberedCircle(number = 5)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Product Availability on Sheff",
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500
                )
            }

            var selectedOne by remember { mutableStateOf<String?>(null) }
            var selectedTwo by remember { mutableStateOf<String?>(null) }
            var selectedThree by remember { mutableStateOf<String?>(null) }
            var selectedFour by remember { mutableStateOf<String?>(null) }
            val proOne = "040-007"
            val proTwo = "040-008"
            val proThree = "040-009"
            val proFour = "040-010"

            ProductCheckbox(
                productName = "TARGET SUPER RV",
                isChecked = selectedOne === proOne,
                onCheckedChange = { newValue ->
                    selectedOne = if (newValue) proOne else ""
                }
            )
            ProductCheckbox(
                productName = "TARGET MENTHOL RV",
                isChecked = selectedTwo === proTwo,
                onCheckedChange = { newValue ->
                    selectedTwo = if (newValue) proTwo else ""
                }
            )
            ProductCheckbox(
                productName = "EXECUTIVE",
                isChecked = selectedThree === proThree,
                onCheckedChange = { newValue ->
                    selectedThree = if (newValue) proThree else ""
                }
            )
            ProductCheckbox(
                productName = "EXECUTIVE CLICK",
                isChecked = selectedFour === proFour,
                onCheckedChange = { newValue ->
                    selectedFour = if (newValue) proFour else ""
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Submit Button: clicking will show the success dialog
            CButton(
                onClick = { showDialog = true },
                buttonState = true,
                text = "Submit Survey",
            )
        }

        // Success dialog: when OK is clicked, navigate back
        SuccessDialog(
            showDialog = showDialog,
            onDismiss = { showDialog = false },
            onNavigateBack = {
                showDialog = false
                navController.popBackStack()
            }
        )
    }
}

@Composable
fun SuccessDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onNavigateBack: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Success") },
            text = { Text("Your submission was successful!") },
            confirmButton = {
                Button(onClick = onNavigateBack) {
                    Text("OK")
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitFrequencyQuestions() {
    var visitFrequency by remember { mutableStateOf("") }
    var lastVisitDate by remember { mutableStateOf("") }
    var isFrequencySufficient by remember { mutableStateOf("") }
    var isProactive by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "How often does the sales representative visit your outlet?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        DropdownQuestion(
            options = listOf("Daily", "Weekly", "Bi-weekly", "Monthly", "Rarely"),
            selectedOption = visitFrequency,
            onOptionSelected = { visitFrequency = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "When was the last time the sales representative visited?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = lastVisitDate,
            onValueChange = { lastVisitDate = it },
            label = { Text("Enter date") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEEEEEE),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Text(
            text = "Is the frequency of visits sufficient for your needs?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        DropdownQuestion(
            options = listOf("Yes", "No"),
            selectedOption = isFrequencySufficient,
            onOptionSelected = { isFrequencySufficient = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Do you feel the sales representative is proactive?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        DropdownQuestion(
            options = listOf("Yes", "No"),
            selectedOption = isProactive,
            onOptionSelected = { isProactive = it }
        )
    }
}

@Composable
fun CustomerSatisfactionQuestions() {
    var satisfactionLevel by remember { mutableStateOf("") }
    var unresolvedIssues by remember { mutableStateOf("") }
    var productKnowledge by remember { mutableStateOf("") }
    var responsiveness by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "How satisfied are you with the service?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        DropdownQuestion(
            options = listOf("Very Satisfied", "Satisfied", "Neutral", "Dissatisfied", "Very Dissatisfied"),
            selectedOption = satisfactionLevel,
            onOptionSelected = { satisfactionLevel = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Are there any unresolved issues or complaints?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        DropdownQuestion(
            options = listOf("Yes", "No"),
            selectedOption = unresolvedIssues,
            onOptionSelected = { unresolvedIssues = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "How would you rate the sales representative's product knowledge?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        DropdownQuestion(
            options = listOf("Excellent", "Good", "Average", "Poor"),
            selectedOption = productKnowledge,
            onOptionSelected = { productKnowledge = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "How responsive is the sales representative?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        DropdownQuestion(
            options = listOf("Very Responsive", "Responsive", "Neutral", "Slow", "Very Slow"),
            selectedOption = responsiveness,
            onOptionSelected = { responsiveness = it }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesPerformanceQuestions() {
    var frequentProducts by remember { mutableStateOf("") }
    var productQuantity by remember { mutableStateOf("") }
    var unmetProductNeeds by remember { mutableStateOf("") }
    var monthlySpend by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "Which products do you purchase most frequently?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = frequentProducts,
            onValueChange = { frequentProducts = it },
            label = { Text("Enter products") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEEEEEE),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Text(
            text = "How much of each product do you typically order?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = productQuantity,
            onValueChange = { productQuantity = it },
            label = { Text("Enter quantity") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEEEEEE),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Text(
            text = "Are there any products you would like to order but are not currently purchasing?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        DropdownQuestion(
            options = listOf("Yes", "No"),
            selectedOption = unmetProductNeeds,
            onOptionSelected = { unmetProductNeeds = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "What is your average monthly spend on our products?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = monthlySpend,
            onValueChange = { monthlySpend = it },
            label = { Text("Enter amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEEEEEE),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdditionalFeedbackQuestions() {
    var improvementSuggestions by remember { mutableStateOf("") }
    var recommendation by remember { mutableStateOf("") }
    var comments by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "What can we do to improve our service or products?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = improvementSuggestions,
            onValueChange = { improvementSuggestions = it },
            label = { Text("Enter your suggestions") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEEEEEE),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Text(
            text = "Would you recommend our products/services to others?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        DropdownQuestion(
            options = listOf("Yes", "No"),
            selectedOption = recommendation,
            onOptionSelected = { recommendation = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Any other comments or suggestions?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = comments,
            onValueChange = { comments = it },
            label = { Text("Enter your comments") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEEEEEE),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownQuestion(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    Box(modifier = Modifier.fillMaxWidth()) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .zIndex(1f)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }

        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp))
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = true }
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEEEEEE),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp)
        )
    }
}

@Composable
fun ProductCheckbox(
    productName: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF008b00),
                uncheckedColor = Color.Gray,
                checkmarkColor = Color.White
            )
        )
        Text(
            text = productName,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun NumberedCircle(number: Int) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .background(Color(0xFF008b00), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number.toString(),
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
