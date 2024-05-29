package com.example.cvd_monitoring.presentation.doctors.check_cholesterol

import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.model.analysis.CholesterolAnalysis

data class CheckCholesterolState(
    val isLoading: Boolean = false,
    val cholesterolAnalysis: CholesterolAnalysis? = null,
    val error: String = ""
)