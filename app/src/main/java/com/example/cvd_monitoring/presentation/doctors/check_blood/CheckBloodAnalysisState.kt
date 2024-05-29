package com.example.cvd_monitoring.presentation.doctors.check_blood

import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.model.analysis.Diagnosis

data class CheckBloodAnalysisState(
    val isLoading: Boolean = false,
    val bloodAnalysis: BloodAnalysis? = null,
    val error: String = ""
)