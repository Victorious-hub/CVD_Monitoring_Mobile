package com.example.cvd_monitoring.presentation.doctors.schedule

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.cvd_monitoring.presentation.doctors.schedule.components.ScheduleListItemScreen
import com.example.cvd_monitoring.presentation.treatment.medications.MedicationListViewModel
import com.example.cvd_monitoring.presentation.treatment.medications.components.MedicationListItemScreen
import kotlinx.coroutines.launch
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ScheduleScreen(
    navController: NavHostController,
    viewModel: ScheduleViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = null) {
        viewModel.getScheduleList()
    }
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.schedule) { schedule ->
                ScheduleListItemScreen(
                    schedule = schedule,
                    onClickAppointment = {
                        navController.navigate("patientAppointment/${schedule.doctor.user.email.substringBefore("@")}")
                    }
                )
            }
        }
    }
}