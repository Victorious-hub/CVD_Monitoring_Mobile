package com.example.cvd_monitoring.presentation.treatment.blood_analysis

import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
data class BloodAnalysisListState (
    val isLoading: Boolean = false,
    val bloodAnalysis: List<BloodAnalysis> = emptyList(),
    val error: String = ""
)