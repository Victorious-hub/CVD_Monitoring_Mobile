package com.example.cvd_monitoring.presentation.treatment.prescription

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientPrescriptionScreen(
    navController: NavHostController,
    viewModel: PatientPrescriptionViewModel = hiltViewModel(),
    slug: String,
) {
    val image = painterResource(R.drawable.heart)
    val dosage = viewModel.dosageState.value
    val startDate = viewModel.startDateState.value
    val endDate = viewModel.endDateState.value

    val isFocused by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()

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
        yearRange = 2024..2024
    )
    val datePickerStateEnd = rememberDatePickerState(
        initialSelectedDateMillis = date,
        yearRange = 2024..2024
    )
    val year = currentDate.get(Calendar.YEAR)
    val month = currentDate.get(Calendar.MONTH) + 1
    val day = currentDate.get(Calendar.DAY_OF_MONTH)
    val todayDate = "$year-0$month-$day"
    var showDatePicker by remember { mutableStateOf(false) }

    var showDatePickerEnd by remember { mutableStateOf(false) }

    val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
    val context = LocalContext.current

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
                        message = "Prescription Successful",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
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
        modifier = Modifier.padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(250.dp))

        Text(
            text = "Patient Prescription",
            modifier = Modifier.padding(bottom = 25.dp),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Monospace,
            )
        )
        TextField(
            value = viewModel.dosageState.value.text,
            onValueChange = { viewModel.setDosageStateValue(it) },
            label = {
                Text(
                    text = "Dosage(mg)",
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
        Row{
            TextField(
                value = viewModel.startDateState.value.text,
                onValueChange = { viewModel.setStartDateValue(it) },
                readOnly = true,
                label = {
                    Text(
                        text = "Start Date",
                        color = Color.Gray,
                    )
                },
                modifier = Modifier
                    .width(150.dp)
                    .height(56.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Red,
                    unfocusedIndicatorColor = Color.Black,
                    cursorColor = Color.Red,
                ),
            )

            Spacer(modifier = Modifier.width(50.dp))

            TextField(
                value = viewModel.endDateState.value.text,
                onValueChange = {  viewModel.setEndDateStateValue(it) },
                readOnly = true,
                label = {
                    Text(
                        text = "End Date",
                        color = Color.Gray,
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
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(430.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
        ) {
            Button(
                onClick = {
                    showDatePicker = true
                },
                modifier = Modifier.width(130.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFa5051f),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = "Choose Date")
            }

            Spacer(modifier = Modifier.width(60.dp))

            Button(
                onClick = {
                    showDatePickerEnd = true
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFa5051f),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = "Choose Date")
            }

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(310.dp))
        Button(
            onClick = {
                if (dosage.text.isEmpty())
                {
                    Toast.makeText(context, "Dosage can not be empty", Toast.LENGTH_SHORT).show()
                } else if (startDate.text.isEmpty())
                {
                    Toast.makeText(context, "Start Date can not be empty", Toast.LENGTH_SHORT).show()
                }else if (endDate.text.isEmpty())
                {
                    Toast.makeText(context, "End Date can not be empty", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.createPatientPrescription(slug)
                    Toast.makeText(context, "Prescription successfully created", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFa5051f),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Create prescription")
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {  },
            confirmButton = {
                TextButton(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFa5051f),
                        contentColor = Color.White
                    ),
                    onClick = {
                        val selectedDate = Calendar.getInstance().apply {
                            timeInMillis = datePickerState.selectedDateMillis!!
                        }
                        val yearStart = selectedDate.get(Calendar.YEAR)
                        val monthStart = selectedDate.get(Calendar.MONTH) + 1
                        val dayStart = selectedDate.get(Calendar.DAY_OF_MONTH)
                        val todayDateStart = "$yearStart-0$monthStart-$dayStart"
                        if(todayDateStart == todayDate || selectedDate.after(Calendar.getInstance()))  {
                             Toast.makeText(
                                 context,
                                 "Selected date ${dateFormatter.format(selectedDate.time)} saved",
                                 Toast.LENGTH_SHORT
                             ).show()
                             showDatePicker = false
                             viewModel.startDateState.value.text = dateFormatter.format(selectedDate.time)
                        }else{
                             Toast.makeText(
                                 context,
                                 "Selected date should from today, please select again",
                                 Toast.LENGTH_SHORT
                             ).show()
                         }
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFa5051f),
                        contentColor = Color.White
                    ),
                    onClick = {
                        showDatePicker = false
                    }
                ) { Text("Cancel") }
            },
            colors = DatePickerDefaults.colors(
                containerColor = Color.White,
            )
        )
        {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    todayContentColor = Color.Gray,
                    todayDateBorderColor = Color(0xFFa5051f),
                    selectedDayContentColor = Color.Gray,
                    dayContentColor = Color.Gray, // Change to a darker color
                    selectedDayContainerColor = Color(0xFFa5051f),
                )
            )
        }
    }

    if (showDatePickerEnd) {
        DatePickerDialog(
            onDismissRequest = {  },
            confirmButton = {
                TextButton(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFa5051f),
                        contentColor = Color.White
                    ),
                    onClick = {
                        val selectedDate = Calendar.getInstance().apply {
                            timeInMillis = datePickerState.selectedDateMillis!!
                        }
                        val yearStart = selectedDate.get(Calendar.YEAR)
                        val monthStart = selectedDate.get(Calendar.MONTH) + 1
                        val dayStart = selectedDate.get(Calendar.DAY_OF_MONTH)
                        val todayDateStart = "$yearStart-0$monthStart-$dayStart"
                        if(todayDateStart == todayDate || selectedDate.after(Calendar.getInstance()))  {
                            Toast.makeText(
                                context,
                                "Selected date ${dateFormatter.format(selectedDate.time)} saved",
                                Toast.LENGTH_SHORT
                            ).show()
                            showDatePickerEnd = false
                            viewModel.endDateState.value.text = dateFormatter.format(selectedDate.time)
                        }else{
                            Toast.makeText(
                                context,
                                "Selected date should from today, please select again",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFa5051f),
                        contentColor = Color.White
                    ),
                    onClick = {
                        showDatePickerEnd = false
                    }
                ) { Text("Cancel") }
            },
            colors = DatePickerDefaults.colors(
                containerColor = Color.White,
            )
        )
        {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    todayContentColor = Color.Gray,
                    todayDateBorderColor = Color(0xFFa5051f),
                    selectedDayContentColor = Color.Gray,
                    dayContentColor = Color.Gray, // Change to a darker color
                    selectedDayContainerColor = Color(0xFFa5051f),
                )
            )
        }
    }
}