package com.example.cvd_monitoring.presentation.doctors.appointment


import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cvd_monitoring.R
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.presentation.ui.theme.Pink80
import com.example.cvd_monitoring.presentation.ui.theme.Purple40
import com.example.cvd_monitoring.presentation.ui.theme.Purple80
import com.example.cvd_monitoring.presentation.ui.theme.PurpleGrey80
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(
    navController: NavHostController,
    viewModel: AppointmentViewModel = hiltViewModel(),
    slug: String,
) {
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
    var showDatePicker by remember { mutableStateOf(false) }

    val timePickerState = rememberTimePickerState(
        initialHour = 12,
        initialMinute = 30,
    )
    var showTimePicker by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd")

    val image = painterResource(R.drawable.heart)
    val appointmentDateState = viewModel.appointmentDateState.value
    val appointmentTimeState = viewModel.appointmentTimeState.value

    val isFocused by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val year = currentDate.get(Calendar.YEAR)
    val month = currentDate.get(Calendar.MONTH) + 1
    val day = currentDate.get(Calendar.DAY_OF_MONTH)
    val todayDate = "$year-0$month-$day"


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
                        message = "Doctor Appointment Successful",
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
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(250.dp))
        Text(
            text = "Patient Appointment",
            modifier = Modifier.padding(bottom = 25.dp),
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
            TextField(
                value = appointmentDateState.text,
                onValueChange = { viewModel.setAppointmentDateValue(it) },
                readOnly = true,
                label = {
                    Text(
                        text = "Appointment Date",
                        color = Color.Gray,
                        style = TextStyle(
                            fontSize = 13.sp,
                        )
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

            Spacer(modifier = Modifier.width(35.dp))

            TextField(
                value = appointmentTimeState.text,
                onValueChange = { viewModel.setAppointmentTimeValue(it) },
                readOnly = true,
                label = {
                    Text(
                        text = "Appointment Time",
                        color = Color.Gray,
                        style = TextStyle(
                            fontSize = 13.sp,
                        )
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
        Spacer(modifier = Modifier.height(380.dp))
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
                modifier = Modifier.width(165.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFa5051f),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = "Date Picker")
            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {
                    showTimePicker = true
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFa5051f),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = "Time Picker")
            }

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
    ) {
        Spacer(modifier = Modifier.height(450.dp))

        Button(
            onClick = {
                if (appointmentDateState.text.isEmpty())
                {
                    Toast.makeText(context, "Date can not be empty", Toast.LENGTH_SHORT).show()
                } else if (appointmentTimeState.text.isEmpty())
                {
                    Toast.makeText(context, "Time can not empty", Toast.LENGTH_SHORT).show()
                }
                else{
                    viewModel.createAppointment(slug)
                    Toast.makeText(context, "Appointment created successfully", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFa5051f),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Create Appointment")
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
                            viewModel.appointmentDateState.value.text = dateFormatter.format(selectedDate.time)
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
                ) { Text(
                    text = "Cancel",
                ) }
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

    if (showTimePicker) {
        TimePickerDialog(
            onDismissRequest = {  },
            confirmButton = {

                TextButton(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFa5051f),
                        contentColor = Color.White
                    ),

                    onClick = {
                        val selectedHour = timePickerState.hour
                        val selectedMinute = timePickerState.minute
                        val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                        Toast.makeText(
                            context,
                            "Selected time $formattedTime saved",
                            Toast.LENGTH_SHORT
                        ).show()
                        showTimePicker = false
                        viewModel.appointmentTimeState.value.text = formattedTime
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                Spacer(modifier = Modifier.width(10.dp))
                TextButton(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFa5051f),
                        contentColor = Color.White
                    ),
                    onClick = {
                        showTimePicker = false
                    }
                ) { Text("Cancel") }
            },
            containerColor = Color.White
        )
        {
            TimePicker(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    clockDialColor = Color.White,
                    selectorColor = Pink80,
                    containerColor = PurpleGrey80,
                    clockDialUnselectedContentColor = Color.Gray,
                )
            )
        }
    }
    
}

@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    confirmButton: @Composable (() -> Unit),
    dismissButton: @Composable (() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = containerColor
                ),
            color = containerColor
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    dismissButton?.invoke()
                    confirmButton()
                }
            }
        }
    }
}

