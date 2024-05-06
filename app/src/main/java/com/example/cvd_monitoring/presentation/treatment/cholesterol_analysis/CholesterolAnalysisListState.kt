package com.example.cvd_monitoring.presentation.treatment.cholesterol_analysis

import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.model.analysis.CholesterolAnalysis

data class CholesterolAnalysisListState (
    val isLoading: Boolean = false,
    val cholesterolAnalysis: List<CholesterolAnalysis> = emptyList(),
    val error: String = ""
)