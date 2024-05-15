package com.example.cvd_monitoring.presentation.patients.patient_data_screen


import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon

import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.toSize
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.ui.platform.LocalDensity
import com.example.cvd_monitoring.presentation.ui.theme.Purple40
import com.example.cvd_monitoring.presentation.ui.theme.Purple80
import com.example.cvd_monitoring.presentation.ui.theme.PurpleGrey80

@SuppressLint("SimpleDateFormat")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun PatientUpdateScreen(
    navController: NavController,
    slug: String,
    viewModel: PatientUpdateViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = slug) {
        viewModel.getCurrentPatient(slug)
    }


    val inputDateFormat = SimpleDateFormat("MMM dd yyyy", Locale.US)
    val outputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val ageState = viewModel.ageState.value
    val heightState = viewModel.heightState.value
    val weightState  = viewModel.weightState.value
    val genderState  = viewModel.genderState.value
    val birthdayState  = viewModel.birthdayState.value
    val dateFormatter = android.icu.text.SimpleDateFormat("yyyy-MM-dd")

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


    val context = LocalContext.current
    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(pickedDate)
        }
    }

    val list = listOf("Male", "Female")
    var selectedText by remember {
        mutableStateOf("Select Gender")
    }

    var isExpanded by remember {
        mutableStateOf(false)
    }


    val dateDialogState = rememberMaterialDialogState()
    val isFocused by remember { mutableStateOf(false) }
    val parsedDate = inputDateFormat.parse(formattedDate)
    birthdayState.text = outputDateFormat.format(parsedDate)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Update data",
            modifier = Modifier.padding(bottom = 25.dp),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Monospace,
            )
        )
        TextField(
            value = ageState.text,
            onValueChange = { viewModel.setAgeValue(it) },
            label = {
                Text(
                    text = "Age",
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
            onValueChange = { viewModel.setHeightValue(it) },
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
        TextField(
            value = weightState.text,
            onValueChange = { viewModel.setWeightValue(it) },
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
//        TextField(
//            value = genderState.text,
//            onValueChange = { viewModel.setGenderValue(it) },
//            label = {
//                Text(
//                    text = "Gender",
//                    color = Color.Gray
//                )
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 8.dp),
//            colors = TextFieldDefaults.textFieldColors(
//                focusedIndicatorColor = Color.Red,
//                unfocusedIndicatorColor = if (isFocused) Color.Red else Color.Black,
//                cursorColor = Color.Red,
//            ),
//        )

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {isExpanded = !isExpanded}
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)},
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                list.forEachIndexed { index, text ->
                    DropdownMenuItem(
                        onClick = {
                            selectedText = list[index]
                            isExpanded = false
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
            onValueChange = { viewModel.setBirthdayValue(it) },
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
                showDatePicker = true
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
                viewModel.updatePatientData(slug)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFa5051f),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Update Data")
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { },
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
                            viewModel.birthdayState.value.text =
                                dateFormatter.format(selectedDate.time)
                            viewModel.setBirthdayValue(viewModel.birthdayState.value.text)
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
            colors = androidx.compose.material3.DatePickerDefaults.colors(
                containerColor = PurpleGrey80,
            )
        )
        {
            DatePicker(
                state = datePickerState,
                colors = androidx.compose.material3.DatePickerDefaults.colors(
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
