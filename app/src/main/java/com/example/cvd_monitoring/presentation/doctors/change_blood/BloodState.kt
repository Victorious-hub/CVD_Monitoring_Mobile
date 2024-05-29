package com.example.cvd_monitoring.presentation.doctors.change_blood

import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis

data class BloodState(
    val isLoading: Boolean = false,
    val bloodAnalysis: BloodAnalysis? = null,
    val error: String = ""
)
