package com.example.cvd_monitoring.presentation.patients.cholesterol_analysis

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
import com.example.cvd_monitoring.presentation.patients.cholesterol_analysis.components.CholesterolAnalysisListItem

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CholesterolAnalysisScreen(
    navController: NavHostController,
    viewModel: CholesterolAnalysisViewModel = hiltViewModel(),
    slug: String
) {
    LaunchedEffect(key1 = slug) {
        viewModel.getCholesterolAnalysisList(slug)
    }
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.cholesterolAnalysis) { cholesterolAnalysis ->
                CholesterolAnalysisListItem(
                    cholesterolAnalysis = cholesterolAnalysis,
                )
            }
        }
    }
}