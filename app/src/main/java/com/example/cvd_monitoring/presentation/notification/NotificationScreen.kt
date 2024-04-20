package com.example.cvd_monitoring.presentation.notification

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cvd_monitoring.presentation.Screen
import com.example.cvd_monitoring.presentation.notification.components.NotificationListItem
import com.example.cvd_monitoring.presentation.patients.patient_data_screen.PatientUpdateViewModel
import com.example.cvd_monitoring.presentation.patients.patient_list.components.PatientListItem
import com.example.cvd_monitoring.presentation.treatment.patient_prescription.components.PrescriptionListItem

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NotificationScreen(
    navController: NavHostController,
    viewModel: NotificationViewModel = hiltViewModel(),
    slug: String
) {
    LaunchedEffect(key1 = slug) {
        viewModel.getNotificationList(slug)
    }
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.notifications) { notification ->
                NotificationListItem(
                    notification = notification,
                )
            }
        }
    }
}