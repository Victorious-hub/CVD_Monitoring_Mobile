package com.example.cvd_monitoring.presentation.doctors.conclusion

import com.example.cvd_monitoring.domain.model.analysis.Diagnosis

data class DiagnosisState(
    val isLoading: Boolean = false,
    val diagnosis: Diagnosis? = null,
    val error: String = ""
)