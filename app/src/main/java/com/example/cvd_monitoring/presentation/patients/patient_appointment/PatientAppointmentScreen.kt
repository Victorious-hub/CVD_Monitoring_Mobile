package com.example.cvd_monitoring.presentation.patients.patient_appointment

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.style.TextOverflow

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.presentation.auth.register_screen.isValidEmail
import kotlinx.coroutines.flow.collectLatest

import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun PatientAppointmentScreen(
    navController: NavController,
    slug: String,
    viewModel: PatientAppointmentViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = slug) {
        viewModel.getScheduleDetail(slug)
    }

    val appointmentDate = viewModel.appointmentDateState.value
    val appointmentTime = viewModel.appointmentTimeState.value
    val isFocused by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val dateList = mutableListOf<String>()
    var selectedTextDate by remember {
        mutableStateOf("Select Date")
    }
    var isExpandedDateType by remember {
        mutableStateOf(false)
    }
    viewModel.state.value.schedule?.availableTime?.let { dateList.addAll(it.keys) }

    val timeList = mutableListOf<String>()
    var selectedTextTime by remember {
        mutableStateOf("Select Time")
    }
    var isExpandedTimeType by remember {
        mutableStateOf(false)
    }
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
                    Log.d("SignUpViewModel", event.route)
                    navController.navigate(event.route)
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Login Successful",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Card(
            modifier = Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth()
                .height(280.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.6f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxWidth().padding(bottom = 3.dp)
                    ) {
                        Text(
                            text = "First Name",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                    Text(
                        text = viewModel.state.value.schedule?.doctor?.user?.firstName ?: "No info",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    )
                    Divider(
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                        color = Color.Gray
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxWidth().padding(bottom = 3.dp)
                    ) {
                        Text(
                            text = "Last Name",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                    Text(
                        text = viewModel.state.value.schedule?.doctor?.user?.lastName ?: "No info",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    )
                    Divider(
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                        color = Color.Gray
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxWidth().padding(bottom = 3.dp)
                    ) {
                        Text(
                            text = "Email",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                    Text(
                        text = viewModel.state.value.schedule?.doctor?.user?.email ?: "No info",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    )
                    Divider(
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                        color = Color.Gray
                    )

                    Card(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Available Time", // Replace with your desired label
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
                                viewModel.state.value.schedule?.availableTime?.let {
                                    items(it.toList()) { (date, timeSlots) ->
                                        Row(modifier = Modifier.padding(bottom = 4.dp)) {
                                            Text(
                                                text = date,
                                                style = TextStyle(
                                                    color = Color.Black,
                                                    fontSize = 14.sp
                                                )
                                            )
                                            Spacer(modifier = Modifier.weight(1f)) // Spacer for horizontal alignment
                                            Text(
                                                text = timeSlots.joinToString(", "),
                                                style = TextStyle(
                                                    color = Color.Gray,
                                                    fontSize = 14.sp
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ExposedDropdownMenuBox(
            expanded = isExpandedDateType,
            onExpandedChange = {isExpandedDateType = !isExpandedDateType}
        ) {
            TextField(
                value = selectedTextDate,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedDateType)},
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenu(expanded = isExpandedDateType, onDismissRequest = { isExpandedDateType = false }) {
                dateList.forEachIndexed { index, text ->
                    DropdownMenuItem(
                        onClick = {
                            selectedTextDate = dateList[index]
                            viewModel.appointmentDateState.value.text = selectedTextDate
                            isExpandedDateType = false
                            selectedTextTime = "Select Time"
                        }
                    ) {
                        Text(text = text)
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(8.dp))

        val chosenKey = viewModel.appointmentDateState.value.text
        if (viewModel.state.value.schedule?.availableTime?.containsKey(chosenKey) == true) {
            val lst = viewModel.state.value.schedule?.availableTime!![chosenKey]!!
            timeList.clear()
            timeList.addAll(lst)
        }

        ExposedDropdownMenuBox(
            expanded = isExpandedTimeType,
            onExpandedChange = {isExpandedTimeType = !isExpandedTimeType}
        ) {
            TextField(
                value = selectedTextTime,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedTimeType)},
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenu(expanded = isExpandedTimeType, onDismissRequest = { isExpandedTimeType = false }) {
                timeList.forEachIndexed { index, text ->
                    DropdownMenuItem(
                        onClick = {
                            selectedTextTime = timeList[index]
                            viewModel.appointmentTimeState.value.text = selectedTextTime
                            isExpandedTimeType = false
                        }
                    ) {
                        Text(text = text)
                    }
                }
            }

        }

        Spacer(modifier = Modifier.height(8.dp))


        Button(
            onClick = {
                if (appointmentDate.text.isEmpty())
                {
                    Toast.makeText(context, "Please, choose your appointment date", Toast.LENGTH_SHORT).show()
                } else if (appointmentTime.text.isEmpty())
                {
                    Toast.makeText(context, "Please, choose your appointment time", Toast.LENGTH_SHORT).show()
                }
                else{
                    viewModel.createAppointment(slug)
                    Toast.makeText(context, "Appointment has been created successfully", Toast.LENGTH_SHORT).show()
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

