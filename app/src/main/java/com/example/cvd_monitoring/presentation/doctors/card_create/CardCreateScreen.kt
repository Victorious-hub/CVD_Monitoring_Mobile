package com.example.cvd_monitoring.presentation.doctors.card_create

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
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
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.cvd_monitoring.presentation.ui.theme.Purple40
import com.example.cvd_monitoring.presentation.ui.theme.Purple80
import com.example.cvd_monitoring.presentation.ui.theme.PurpleGrey80


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CardCreateScreen(
    navController: NavHostController,
    viewModel: CardCreateViewModel = hiltViewModel(),
    slug: String,
) {

    val image = painterResource(R.drawable.heart)
    val bloodTypeState = viewModel.bloodTypeState.value
    val abnormalConditionsState = viewModel.abnormalConditionsState.value
    val smokeState = viewModel.smokeState.value
    val alcoholState = viewModel.alcoholState.value
    val activeState = viewModel.activeState.value
    val weightState = viewModel.weightState.value
    val heightState = viewModel.heightState.value
    val genderState = viewModel.genderState.value
    val birthdayState = viewModel.birthdayState.value

    val isFocused by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()

//    LaunchedEffect(key1 = true) {
//        viewModel.eventFlow.collectLatest { event ->
//            when (event) {
//                is UiEvents.SnackbarEvent -> {
//                    scaffoldState.snackbarHostState.showSnackbar(
//                        message = event.message,
//                        duration = SnackbarDuration.Short
//                    )
//                }
//                is UiEvents.NavigateEvent -> {
//                    Log.d("SignUpViewModel", event.route)
//                    navController.navigate(event.route)
//                    scaffoldState.snackbarHostState.showSnackbar(
//                        message = "Login Successful",
//                        duration = SnackbarDuration.Short
//                    )
//                }
//            }
//        }
//    }

    val bloodTypeList = listOf("Group I", "Group II", "Group III", "Group IV")
    var selectedTextBloodType by remember {
        mutableStateOf("Select Blood Type")
    }

    var isExpandedBloodType by remember {
        mutableStateOf(false)
    }

    val alcoholList = listOf("Yes", "No")
    var selectedTextAlcoholType by remember {
        mutableStateOf("Has alcohol problems")
    }

    var isExpandedAlcohol by remember {
        mutableStateOf(false)
    }

    val activeList = listOf("Yes", "No")
    var selectedTextActiveType by remember {
        mutableStateOf("Has activity problems")
    }

    var isExpandedActive by remember {
        mutableStateOf(false)
    }

    val smokeList = listOf("Yes", "No")
    var selectedTextSmokeType by remember {
        mutableStateOf("Has smoke obessey")
    }

    var isExpandedSmoke by remember {
        mutableStateOf(false)
    }

    val genderList = listOf("Male", "Female")
    var selectedGenderType by remember {
        mutableStateOf("Gender type")
    }

    var isExpandedGender by remember {
        mutableStateOf(false)
    }

    val date = remember {
        Calendar.getInstance().apply {
            set(Calendar.YEAR, 2025)
            set(Calendar.MONTH, 7)
            set(Calendar.DAY_OF_MONTH, 23)
        }.timeInMillis
    }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = date,
        yearRange = 1990..2025
    )
    var showDatePicker by remember { mutableStateOf(false) }

    val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpandedBloodType,
            onExpandedChange = {isExpandedBloodType = !isExpandedBloodType}
        ) {
            TextField(
                value = selectedTextBloodType,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedBloodType)},
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenu(expanded = isExpandedBloodType, onDismissRequest = { isExpandedBloodType = false }) {
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

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = abnormalConditionsState.text,
            onValueChange = { viewModel.setAbnormalConditionsStateValue(it) },
            label = {
                Text(
                    text = "Abnormal conditions",
                    color = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = if (isFocused) Color.Red else Color.Black,
                cursorColor = Color.Red,
            ),
        )

        ExposedDropdownMenuBox(
            expanded = isExpandedSmoke,
            onExpandedChange = {isExpandedSmoke = !isExpandedSmoke}
        ) {
            TextField(
                value = selectedTextSmokeType,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedSmoke)},
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenu(expanded = isExpandedSmoke, onDismissRequest = { isExpandedSmoke = false }) {
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
        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = isExpandedAlcohol,
            onExpandedChange = {isExpandedAlcohol = !isExpandedAlcohol}
        ) {
            TextField(
                value = selectedTextAlcoholType,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedAlcohol)},
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenu(expanded = isExpandedAlcohol, onDismissRequest = { isExpandedAlcohol = false }) {
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
        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = isExpandedActive,
            onExpandedChange = {isExpandedActive = !isExpandedActive}
        ) {
            TextField(
                value = selectedTextActiveType,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedActive)},
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenu(expanded = isExpandedActive, onDismissRequest = { isExpandedActive = false }) {
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
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = weightState.text,
            onValueChange = { viewModel.setWeightStateValue(it) },
            label = {
                Text(
                    text = "Weight",
                    color = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = if (isFocused) Color.Red else Color.Black,
                cursorColor = Color.Red,
            ),
        )

        TextField(
            value = heightState.text,
            onValueChange = { viewModel.setHeightStateValue(it) },
            label = {
                Text(
                    text = "Height",
                    color = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = if (isFocused) Color.Red else Color.Black,
                cursorColor = Color.Red,
            ),
        )


        ExposedDropdownMenuBox(
            expanded = isExpandedGender,
            onExpandedChange = {isExpandedGender = !isExpandedGender}
        ) {
            TextField(
                value = selectedGenderType,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedGender)},
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenu(expanded = isExpandedGender, onDismissRequest = { isExpandedGender = false }) {
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
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = birthdayState.text,
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
                unfocusedIndicatorColor = if (isFocused) Color.Red else Color.Black,
                cursorColor = Color.Red,
            ),
        )
        Button(
            onClick = {
                showDatePicker = true //changing the visibility state
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFa5051f),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(text = "Date Picker")
        }


        Button(
            onClick = {
                viewModel.createPatientCard(slug)
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


    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {  },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedDate = Calendar.getInstance().apply {
                            timeInMillis = datePickerState.selectedDateMillis!!
                        }
                        if (selectedDate.after(Calendar.getInstance())) {
                            Toast.makeText(
                                context,
                                "Selected date ${dateFormatter.format(selectedDate.time)} saved",
                                Toast.LENGTH_SHORT
                            ).show()
                            showDatePicker = false
                            viewModel.birthdayState.value.text = dateFormatter.format(selectedDate.time)
                        } else {
                            Toast.makeText(
                                context,
                                "Selected date should be after today, please select again",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
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
                    dayContentColor = Color.Gray, // Change to a darker color
                    selectedDayContainerColor = Purple40,
                )
            )
        }
    }
}