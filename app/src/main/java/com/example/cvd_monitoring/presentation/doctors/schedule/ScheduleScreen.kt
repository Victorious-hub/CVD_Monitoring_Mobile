package com.example.cvd_monitoring.presentation.doctors.schedule

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.cvd_monitoring.presentation.doctors.schedule.components.ScheduleListItemScreen
import com.example.cvd_monitoring.presentation.notification.components.NotificationListItem
import com.example.cvd_monitoring.presentation.treatment.medications.MedicationListViewModel
import com.example.cvd_monitoring.presentation.treatment.medications.components.MedicationListItemScreen
import kotlinx.coroutines.launch
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ScheduleScreen(
    navController: NavHostController,
    viewModel: ScheduleViewModel = hiltViewModel(),
    onClickBackToMain : () -> Unit,
) {
    LaunchedEffect(key1 = null) {
        viewModel.getScheduleList()
    }
    val state = viewModel.state.value
    if (state.schedule.isEmpty())
    {
        Text(
            text = "Schedule is empty",
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black
            ),
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .padding(16.dp),
            textAlign = TextAlign.Center,
        )
        Button(
            onClick = {
                onClickBackToMain()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 425.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Back to main",
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Arrow Right Icon",
                    tint = Color.Black,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
    else{
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.schedule) { schedule ->
                    ScheduleListItemScreen(
                        schedule = schedule,
                        onDismiss = {},
                        onClickAppointmentCreate = {
                            Log.d("", schedule.doctor.user.email.substringBefore("@"))
                            viewModel.viewModelScope.launch {
                                viewModel.createAppointment(schedule.doctor.user.email.substringBefore("@"))
                                navController.navigate("currentPatient/${viewModel.patientSlugState.value.text}/get")
                            }
                        }
                    )
                }
            }
        }
    }
}