package com.example.cvd_monitoring.presentation.doctors.schedule.components

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.cvd_monitoring.R
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.domain.model.analysis.Schedule
import com.example.cvd_monitoring.presentation.doctors.schedule.ScheduleViewModel
import com.example.cvd_monitoring.presentation.doctors.schedule.calendar.CalendarDataSource
import com.example.cvd_monitoring.presentation.doctors.schedule.calendar.CalendarUiModel
import com.example.cvd_monitoring.utils.Constants
import com.example.cvd_monitoring.utils.Constants.BASE_IP
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleListItemScreen(
    schedule: Schedule,
    onDismiss : () -> Unit,
    viewModel: ScheduleViewModel = hiltViewModel(),
    onClickAppointmentCreate : () -> Unit
) {
    val timeList = mutableListOf<String>()
    var selectedTextTime by remember {
        mutableStateOf("Available Time")
    }
    var isExpandedTimeType by remember {
        mutableStateOf(false)
    }
    val isFocused by remember { mutableStateOf(false) }
    val dataSource = CalendarDataSource()
    var calendarUiModel by remember { mutableStateOf(dataSource.getData(lastSelectedDate = dataSource.today)) }
    var showAppointmentDialog by remember {
        mutableStateOf(false)
    }
    var curDate: String
    val image =
        rememberImagePainter(data = "http://${BASE_IP}:8000${schedule.doctor.profileImage}")
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    if (showAppointmentDialog){
        AlertDialog(
            onDismissRequest = { onDismiss() },
            buttons = { },
            modifier = Modifier.height(300.dp).fillMaxWidth(),

            title = {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Header(
                        data = calendarUiModel,
                        onPrevClickListener = { startDate ->
                            val finalStartDate = startDate.minusDays(1)
                            calendarUiModel = dataSource.getData(startDate = finalStartDate, lastSelectedDate = calendarUiModel.selectedDate.date)
                        },
                        onNextClickListener = { endDate ->
                            val finalStartDate = endDate.plusDays(2)
                            calendarUiModel = dataSource.getData(startDate = finalStartDate, lastSelectedDate = calendarUiModel.selectedDate.date)
                        },

                        )
                    Content(data = calendarUiModel, onDateClickListener = { date ->
                        calendarUiModel = calendarUiModel.copy(
                            selectedDate = date,
                            visibleDates = calendarUiModel.visibleDates.map {
                                it.copy(
                                    isSelected = it.date.isEqual(date.date)
                                )
                            },
                        )
                        selectedTextTime = "Available Time"
                        viewModel.appointmentTimeState.value.text = ""
                        viewModel.appointmentDateState.value.text = date.toString()
                    }, schedule = schedule)
                    Spacer(modifier = Modifier.height(10.dp))
                    ExposedDropdownMenuBox(
                        expanded = isExpandedTimeType,
                        onExpandedChange = {isExpandedTimeType = !isExpandedTimeType}
                    ) {
                        TextField(
                            value = selectedTextTime,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedTimeType) } ,
                            colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color.Red,
                            unfocusedIndicatorColor = if (isFocused) Color.Red else Color.Black,
                            cursorColor = Color.Red,
                            )
                        )

                        ExposedDropdownMenu(
                            expanded = isExpandedTimeType,
                            onDismissRequest = { isExpandedTimeType = false }) {
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
                    Spacer(modifier = Modifier.height(10.dp))
                    Row (
                        horizontalArrangement = Arrangement.Center,
                    ){
                        Button(
                            onClick = {
                                if (viewModel.appointmentTimeState.value.text.isEmpty())
                                {
                                    Toast.makeText(context, "Please, choose your appointment time", Toast.LENGTH_SHORT).show()
                                }
                                else if (viewModel.appointmentDateState.value.text.isEmpty())
                                {
                                    Toast.makeText(context, "Please, choose your appointment date", Toast.LENGTH_SHORT).show()
                                }
                                else{
                                    onClickAppointmentCreate()
                                    Toast.makeText(context, "Appointment has been created successfully", Toast.LENGTH_SHORT).show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFa5051f),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text("Create")
                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        Button(
                            onClick = { showAppointmentDialog = false },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFa5051f),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text(text = "Close")
                        }
                    }
                }
                val chosenKey = calendarUiModel.selectedDate.date.toString()
                if (schedule.availableTime.containsKey(chosenKey)) {
                    viewModel.appointmentDateState.value.text = chosenKey
                    timeList.clear()
                    timeList.addAll(schedule.availableTime[chosenKey]!!)
                }else{
                    timeList.clear()
                }


            }
        )
    }


    Box(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth()
                .height(190.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.3f),
                    painter = image,
                    contentDescription = "Account Image"
                )
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.6f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxWidth()
                            .padding(bottom = 3.dp)
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
                        text = schedule.doctor.user.firstName ?: "No info",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    )
                    Divider(
                        modifier = Modifier.padding(top = 3.dp, bottom = 8.dp),
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
                        text = schedule.doctor.user.lastName ?: "No info",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    )
                    Divider(
                        modifier = Modifier.padding(top = 3.dp, bottom = 8.dp),
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
                        text = schedule.doctor.user.email ?: "No info",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    )
                    Divider(
                        modifier = Modifier.padding(top = 3.dp, bottom = 8.dp),
                        color = Color.Gray
                    )

                    Button(
                        onClick = {
                            showAppointmentDialog = true
                        },
                        modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(
                            Color.LightGray
                        )
                    ){
                        Row(
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Home Button Icon",
                                tint = Color.Black
                            )
                            Text(
                                text = "Details",
                                color = Color.Black
                            )
                        }
                    }

                }
            }
        }

    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Header(
    data: CalendarUiModel,
    onPrevClickListener: (LocalDate) -> Unit,
    onNextClickListener: (LocalDate) -> Unit,
) {
    Row {
        Text(
            text = if (data.selectedDate.isToday) {
                "Today"
            } else {
                data.selectedDate.date.format(
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                )
            },
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        IconButton(onClick = { onPrevClickListener(data.startDate.date) }) {
            Icon(
                imageVector = Icons.Filled.ChevronLeft,
                contentDescription = "Back"
            )
        }
        IconButton(onClick = {onNextClickListener(data.endDate.date) }) {
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = "Next"
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Content(
    data: CalendarUiModel,
    onDateClickListener: (CalendarUiModel.Date) -> Unit,
    schedule: Schedule,
) {
    LazyRow {
        items(items = data.visibleDates) { date ->
            val hasAvailableTime = schedule.availableTime.containsKey(date.date.toString())
            val lst = schedule.availableTime[date.date.toString()]
            val color: Color = if (lst?.isNotEmpty() == true) {
                Color(0xFFa5051f)
            }else{
                MaterialTheme.colorScheme.secondary
            }
            ContentItem(
                    date = date,
                    onDateClickListener,
                    backgroundColor = color
                )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentItem(
    date: CalendarUiModel.Date,
    onClickListener: (CalendarUiModel.Date) -> Unit,
    backgroundColor: Color
) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .clickable {
                onClickListener(date)
            },

        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        ),
    ) {
        Column(
            modifier = Modifier
                .width(46.dp)
                .height(48.dp)
                .padding(4.dp)
        ) {
            Text(
                text = date.day,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = date.date.dayOfMonth.toString(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }

}
