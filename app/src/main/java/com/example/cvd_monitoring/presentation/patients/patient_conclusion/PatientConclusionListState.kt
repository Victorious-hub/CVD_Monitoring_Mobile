package com.example.cvd_monitoring.presentation.patients.patient_conclusion

import com.example.cvd_monitoring.domain.model.analysis.CholesterolAnalysis
import com.example.cvd_monitoring.domain.model.analysis.PatientConclusion

data class PatientConclusionListState (
    val isLoading: Boolean = false,
    val patientConclusion: List<PatientConclusion> = emptyList(),
    val error: String = ""
)