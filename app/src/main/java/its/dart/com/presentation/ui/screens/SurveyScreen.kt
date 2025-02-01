package its.dart.com.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.zIndex
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
                title = "Kenneth",
                click = {},
                clickSearch = {},
                clickMenu = {},
                navigation = true,
                fontSize = 20,
                fontFamily = robotoFamily,
                letterSpacing = 0.5
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
        ) {
            CustomerSurveyScreen()
        }
    }
}

@Composable
fun CustomerSurveyScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "Customer Survey",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Group 1: Sales Representative Visit Frequency
        Text(
            text = "Sales Representative Visit Frequency",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        VisitFrequencyQuestions()

        Spacer(modifier = Modifier.height(16.dp))

        // Group 2: Customer Satisfaction
        Text(
            text = "Customer Satisfaction",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        CustomerSatisfactionQuestions()

        Spacer(modifier = Modifier.height(16.dp))

        // Group 3: Sales Performance
        Text(
            text = "Sales Performance",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        SalesPerformanceQuestions()

        Spacer(modifier = Modifier.height(16.dp))

        // Group 4: Additional Feedback
        Text(
            text = "Additional Feedback",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        AdditionalFeedbackQuestions()

        Spacer(modifier = Modifier.height(16.dp))

        // Submit Button
        Button(
            onClick = { /* Handle survey submission */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Survey")
        }
    }
}

@Composable
fun VisitFrequencyQuestions() {
    var visitFrequency by remember { mutableStateOf("") }
    var lastVisitDate by remember { mutableStateOf("") }
    var isFrequencySufficient by remember { mutableStateOf("") }
    var isProactive by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        // Question 1: How often does the sales representative visit your outlet?
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

        // Question 2: When was the last time the sales representative visited?
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
        )

        // Question 3: Is the frequency of visits sufficient for your needs?
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

        // Question 4: Do you feel the sales representative is proactive?
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

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        // Question 1: How satisfied are you with the service?
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

        // Question 2: Are there any unresolved issues or complaints?
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

        // Question 3: How would you rate the sales representative's product knowledge?
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

        // Question 4: How responsive is the sales representative?
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

@Composable
fun SalesPerformanceQuestions() {
    var frequentProducts by remember { mutableStateOf("") }
    var productQuantity by remember { mutableStateOf("") }
    var unmetProductNeeds by remember { mutableStateOf("") }
    var monthlySpend by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        // Question 1: Which products do you purchase most frequently?
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
        )

        // Question 2: How much of each product do you typically order?
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
        )

        // Question 3: Are there any products you would like to order but are not currently purchasing?
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

        // Question 4: What is your average monthly spend on our products?
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
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun AdditionalFeedbackQuestions() {
    var improvementSuggestions by remember { mutableStateOf("") }
    var recommendation by remember { mutableStateOf("") }
    var comments by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        // Question 1: What can we do to improve our service or products?
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
        )

        // Question 2: Would you recommend our products/services to others?
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

        // Question 3: Any other comments or suggestions?
        Text(
            text = "Any other comments or suggestions?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = comments,
            onValueChange = { comments = it },
            label = { Text("Enter your comments") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@Composable
fun DropdownQuestion(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // DropdownMenu tied to the OutlinedTextField
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }, // Close dropdown when dismissed
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .zIndex(1f) // Ensure it appears on top
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false // Close dropdown after selection
                    }
                )
            }
        }

        // OutlinedTextField with clickable modifier
        OutlinedTextField(
            value = selectedOption,
            onValueChange = { }, // No-op since the field is read-only
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true } // Open dropdown on click
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = true } // Open dropdown on icon click
                )
            }
        )
    }
}
