package com.example.cvd_monitoring.presentation.treatment.blood_analysis

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
import com.example.cvd_monitoring.presentation.notification.NotificationViewModel
import com.example.cvd_monitoring.presentation.notification.components.NotificationListItem
import com.example.cvd_monitoring.presentation.treatment.blood_analysis.components.BloodAnalysisListItem
import com.example.cvd_monitoring.presentation.treatment.patient_prescription.components.PrescriptionListItem

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BloodAnalysisScreen(
    navController: NavHostController,
    viewModel: BloodAnalysisViewModel = hiltViewModel(),
    slug: String
) {
    LaunchedEffect(key1 = slug) {
        viewModel.getBloodAnalysisList(slug)
    }
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.bloodAnalysis) { bloodAnalysis ->
                BloodAnalysisListItem(
                    bloodAnalysis = bloodAnalysis,
                )
            }
        }
    }
}