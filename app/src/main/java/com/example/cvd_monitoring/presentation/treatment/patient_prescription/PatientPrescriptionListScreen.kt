package com.example.cvd_monitoring.presentation.treatment.patient_prescription

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cvd_monitoring.presentation.patients.blood_analysis.BloodAnalysisViewModel
import com.example.cvd_monitoring.presentation.patients.blood_analysis.components.BloodAnalysisListItem
import com.example.cvd_monitoring.presentation.treatment.patient_prescription.components.PrescriptionListItem


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PatientPrescriptionListScreen(
    navController: NavHostController,
    viewModel: PatientPrescriptionListViewModel = hiltViewModel(),
    slug: String
) {
    LaunchedEffect(key1 = slug) {
        viewModel.getPrescriptionList(slug)
    }
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.prescriptions) { prescription ->
                PrescriptionListItem(
                    prescription = prescription,
                )
            }
        }
    }
}