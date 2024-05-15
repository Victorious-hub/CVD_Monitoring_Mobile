package com.example.cvd_monitoring.presentation.treatment.medications

import com.example.cvd_monitoring.domain.model.treatment.Medication

data class MedicationListState(
    val isLoading: Boolean = false,
    val medications: List<Medication> = emptyList(),
    val error: String = ""
)