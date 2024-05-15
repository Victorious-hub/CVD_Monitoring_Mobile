package com.example.cvd_monitoring.presentation.treatment.medications

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
import com.example.cvd_monitoring.presentation.Screen
import com.example.cvd_monitoring.presentation.treatment.medications.components.MedicationListItemScreen
import com.example.cvd_monitoring.presentation.navigation.graphs.DoctorPatientActions
import com.example.cvd_monitoring.presentation.navigation.graphs.getRouteWithSlug
import com.example.cvd_monitoring.presentation.treatment.blood_analysis.BloodAnalysisViewModel
import com.example.cvd_monitoring.presentation.treatment.blood_analysis.components.BloodAnalysisListItem
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MedicationListScreen(
    navController: NavHostController,
    viewModel: MedicationListViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = null) {
        viewModel.getMedicationList()
    }
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.medications) { medication ->
                MedicationListItemScreen(
                    medication = medication,
                    onPrescriptionCreate = {
                        viewModel.viewModelScope.launch {
                            viewModel.saveMedicationId(medication.id)
                            navController.navigate("patientPrescription/${viewModel.doctorSlugState.value.text}")
                        }
                    }
                )
            }
        }
    }
}