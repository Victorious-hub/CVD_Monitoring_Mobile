package com.example.cvd_monitoring.presentation.doctors.card_update

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cvd_monitoring.R
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.presentation.ui.theme.Purple40
import com.example.cvd_monitoring.presentation.ui.theme.Purple80
import com.example.cvd_monitoring.presentation.ui.theme.PurpleGrey80
import kotlinx.coroutines.flow.collectLatest



@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun UpdateCardScreen(
    navController: NavHostController,
    viewModel: UpdateCardViewModel = hiltViewModel(),
    slug: String,
) {


    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.SnackbarEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Short
                    )
                }

                is UiEvents.NavigateEvent -> {
                    navController.navigate(event.route)
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Card Update Successful",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    val image = painterResource(R.drawable.heart)
    val bloodTypeList = listOf("Group I", "Group II", "Group III", "Group IV")
    var selectedTextBloodType by rememberSaveable {
        mutableStateOf(viewModel.alcoholState.value.text)
    }

    var isExpandedBloodType by remember {
        mutableStateOf(false)
    }

    val alcoholList = listOf("Yes", "No")
    var selectedTextAlcoholType by rememberSaveable {
        mutableStateOf(viewModel.alcoholState.value.text)
    }

    var isExpandedAlcohol by remember {
        mutableStateOf(false)
    }

    val activeList = listOf("Yes", "No")
    var selectedTextActiveType by rememberSaveable {
        mutableStateOf(viewModel.activeState.value.text)
    }

    var isExpandedActive by remember {
        mutableStateOf(false)
    }

    val smokeList = listOf("Yes", "No")
    var selectedTextSmokeType by rememberSaveable {
        mutableStateOf(viewModel.smokeState.value.text)
    }

    var isExpandedSmoke by remember {
        mutableStateOf(false)
    }

    val genderList = listOf("Male", "Female")
    var selectedGenderType by rememberSaveable {
        mutableStateOf(viewModel.genderState.value.text)
    }

    var isExpandedGender by remember {
        mutableStateOf(false)
    }
    val currentDate = remember {
        Calendar.getInstance()
    }
    val date = remember {
        Calendar.getInstance().apply {
            currentDate.get(Calendar.YEAR)
            currentDate.get(Calendar.MONTH)
            currentDate.get(Calendar.DAY_OF_MONTH)
        }.timeInMillis
    }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = date,
        yearRange = 1990..2024
    )
    var showDatePicker by remember { mutableStateOf(false) }

    val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
    val context = LocalContext.current

    LaunchedEffect(key1 = slug) {
        viewModel.getPatientCard(slug)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.25f)
            .background(
                color = Color(0xFFa5051f),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 80.dp,
                    bottomEnd = 80.dp
                )
            )
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 25.dp),
            painter = image,
            contentDescription = null

        )
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(250.dp))
        Text(
            text = "Create Patient Card",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Monospace,
            )
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
        ) {
            ExposedDropdownMenuBox(
                expanded = isExpandedAlcohol,
                onExpandedChange = { isExpandedAlcohol = !isExpandedAlcohol }
            ) {
                TextField(
                    value = selectedTextAlcoholType,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedAlcohol) },
                    modifier = Modifier
                        .width(160.dp)
                        .height(56.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Red,
                        unfocusedIndicatorColor = Color.Black,
                        cursorColor = Color.Red,
                    ),
                )

                ExposedDropdownMenu(
                    expanded = isExpandedAlcohol,
                    onDismissRequest = { isExpandedAlcohol = false }) {
                    alcoholList.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            onClick = {
                                selectedTextAlcoholType = alcoholList[index]
                                viewModel.alcoholState.value.text = selectedTextAlcoholType
                                isExpandedAlcohol = false
                            }
                        ) {
                            Text(text = text)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(40.dp))

            ExposedDropdownMenuBox(
                expanded = isExpandedActive,
                onExpandedChange = { isExpandedActive = !isExpandedActive }
            ) {
                TextField(
                    value = selectedTextActiveType,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedActive) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Red,
                        unfocusedIndicatorColor = Color.Black,
                        cursorColor = Color.Red,
                    ),
                )

                ExposedDropdownMenu(
                    expanded = isExpandedActive,
                    onDismissRequest = { isExpandedActive = false }) {
                    activeList.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            onClick = {
                                selectedTextActiveType = activeList[index]
                                viewModel.activeState.value.text = selectedTextActiveType
                                isExpandedActive = false
                            }
                        ) {
                            Text(text = text)
                        }
                    }
                }

            }
        }
    }



    Column(
    ) {
        Spacer(modifier = Modifier.height(350.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
        ) {
            TextField(
                value = viewModel.abnormalConditionsState.value.text,
                onValueChange = { viewModel.setAbnormalConditionsStateValue(it) },
                label = {
                    Text(
                        text = "Other problems",
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .width(160.dp)
                    .height(56.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Red,
                    unfocusedIndicatorColor = Color.Black,
                    cursorColor = Color.Red,
                ),
            )

            Spacer(modifier = Modifier.width(40.dp))

            TextField(
                value = viewModel.weightState.value.text,
                onValueChange = { viewModel.setWeightStateValue(it) },
                label = {
                    Text(
                        text = "Weight(kg)",
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Red,
                    unfocusedIndicatorColor = Color.Black,
                    cursorColor = Color.Red,
                ),
            )
        }
    }

    Column(
    ) {
        Spacer(modifier = Modifier.height(415.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
        ) {
            ExposedDropdownMenuBox(
                expanded = isExpandedGender,
                onExpandedChange = { isExpandedGender = !isExpandedGender }
            ) {
                TextField(
                    value = selectedGenderType,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedGender) },
                    modifier = Modifier
                        .width(160.dp)
                        .height(56.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Red,
                        unfocusedIndicatorColor = Color.Black,
                        cursorColor = Color.Red,
                    ),
                )

                ExposedDropdownMenu(
                    expanded = isExpandedGender,
                    onDismissRequest = { isExpandedGender = false }) {
                    genderList.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            onClick = {
                                selectedGenderType = genderList[index]
                                viewModel.genderState.value.text = selectedGenderType
                                isExpandedGender = false
                            }
                        ) {
                            Text(text = text)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(40.dp))

            TextField(
                value = viewModel.heightState.value.text,
                onValueChange = { viewModel.setHeightStateValue(it) },
                label = {
                    Text(
                        text = "Height(cm)",
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Red,
                    unfocusedIndicatorColor = Color.Black,
                    cursorColor = Color.Red,
                ),
            )
        }
    }

    Column(
    ) {
        Spacer(modifier = Modifier.height(480.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
        ) {
            ExposedDropdownMenuBox(
                expanded = isExpandedSmoke,
                onExpandedChange = { isExpandedSmoke = !isExpandedSmoke }
            ) {
                TextField(
                    value = selectedTextSmokeType,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedSmoke) },
                    modifier = Modifier
                        .width(160.dp)
                        .height(56.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Red,
                        unfocusedIndicatorColor = Color.Black,
                        cursorColor = Color.Red,
                    ),
                )

                ExposedDropdownMenu(
                    expanded = isExpandedSmoke,
                    onDismissRequest = { isExpandedSmoke = false }) {
                    smokeList.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            onClick = {
                                selectedTextSmokeType = smokeList[index]
                                viewModel.smokeState.value.text = selectedTextSmokeType
                                isExpandedSmoke = false
                            }
                        ) {
                            Text(text = text)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(40.dp))

            ExposedDropdownMenuBox(
                expanded = isExpandedBloodType,
                onExpandedChange = { isExpandedBloodType = !isExpandedBloodType }
            ) {
                TextField(
                    value = selectedTextBloodType,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedBloodType) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Red,
                        unfocusedIndicatorColor = Color.Black,
                        cursorColor = Color.Red,
                    ),
                )

                ExposedDropdownMenu(
                    expanded = isExpandedBloodType,
                    onDismissRequest = { isExpandedBloodType = false }) {
                    bloodTypeList.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            onClick = {
                                selectedTextBloodType = bloodTypeList[index]
                                viewModel.bloodTypeState.value.text = selectedTextBloodType
                                isExpandedBloodType = false
                            }
                        ) {
                            Text(text = text)
                        }
                    }
                }

            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
    ) {
        Spacer(modifier = Modifier.height(545.dp))
        TextField(
            value = viewModel.birthdayState.value.text,
            onValueChange = { viewModel.setBirthdayStateStateValue(it) },
            readOnly = true,
            label = {
                Text(
                    text = "Birthday",
                    color = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = Color.Black,
                cursorColor = Color.Red,
            ),
        )

        Row {
            Button(

                onClick = {
                    showDatePicker = true
                },
                modifier = Modifier.width(135.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFa5051f),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = "Choose Date")
            }

            Spacer(modifier = Modifier.width(40.dp))

            Button(
                onClick = {
                    when {
                        viewModel.bloodTypeState.value.text.isEmpty() -> {
                            Toast.makeText(
                                context,
                                "Blood Type field can not be empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        viewModel.abnormalConditionsState.value.text.isEmpty() -> {
                            Toast.makeText(
                                context,
                                "Abnormal conditions can not be empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        viewModel.smokeState.value.text.isEmpty() -> {
                            Toast.makeText(
                                context,
                                "Smoke field can not be empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        viewModel.alcoholState.value.text.isEmpty() -> {
                            Toast.makeText(
                                context,
                                "Alcohol field can not be empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        viewModel.activeState.value.text.isEmpty() -> {
                            Toast.makeText(
                                context,
                                "Active field can not be empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        viewModel.weightState.value.text.isEmpty() || !"^[-+]?\\d+(\\.\\d+)?\$".toRegex()
                            .matches(viewModel.weightState.value.text) -> {
                            Toast.makeText(
                                context,
                                "Weight field can not be empty or not a number",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        viewModel.heightState.value.text.isEmpty() || !"[0-9]+".toRegex()
                            .matches(viewModel.heightState.value.text) -> {
                            Toast.makeText(
                                context,
                                "Height field can not be empty or not a number",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        viewModel.genderState.value.text.isEmpty() -> {
                            Toast.makeText(
                                context,
                                "Gender field can not be empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        viewModel.birthdayState.value.text.isEmpty() -> {
                            Toast.makeText(
                                context,
                                "Birthday field can not be empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> viewModel.updatePatientCard(slug)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFa5051f),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Create")
            }
        }
    }


    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {  },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedDate = Calendar.getInstance().apply {
                            timeInMillis = datePickerState.selectedDateMillis!!
                        }
                        Toast.makeText(
                            context,
                            "Selected date ${dateFormatter.format(selectedDate.time)} saved",
                            Toast.LENGTH_SHORT
                        ).show()
                        showDatePicker = false
                        viewModel.birthdayState.value.text = dateFormatter.format(selectedDate.time)
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) { Text("Cancel") }
            },
            colors = DatePickerDefaults.colors(
                containerColor = PurpleGrey80,
            )
        )
        {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    todayContentColor = Purple40,
                    todayDateBorderColor = Purple80,
                    selectedDayContentColor = Purple80,
                    dayContentColor = Color.Gray,
                    selectedDayContainerColor = Purple40,
                )
            )
        }
    }
}