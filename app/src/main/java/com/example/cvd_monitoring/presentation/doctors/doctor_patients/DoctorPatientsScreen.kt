package com.example.cvd_monitoring.presentation.doctors.doctor_patients

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cvd_monitoring.domain.model.doctors.DoctorPatients
import com.example.cvd_monitoring.presentation.Screen
import com.example.cvd_monitoring.presentation.doctors.doctor_patients.components.DoctorPatientsListItem
import com.example.cvd_monitoring.presentation.notification.NotificationViewModel
import com.example.cvd_monitoring.presentation.notification.components.NotificationListItem
import com.example.cvd_monitoring.presentation.patients.blood_analysis.components.BloodAnalysisListItem

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DoctorPatientsScreen(
    navController: NavHostController,
    viewModel: DoctorPatientsViewModel = hiltViewModel(),
    slug: String
) {
    LaunchedEffect(key1 = slug) {
        viewModel.getDoctorPatientList(slug)
    }
    val state = viewModel.state.value





    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(30.dp),

        modifier = Modifier
            .padding(4.dp)
            .fillMaxHeight()
    ) {
        state.patients?.patients?.let { patients ->
            items(patients) { patient ->
                Column(
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.Home.route)
                    }
                ) {
                    Text(
                        text = patient.userFirstName,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = patient.userEmail,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = patient.userLastName,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}