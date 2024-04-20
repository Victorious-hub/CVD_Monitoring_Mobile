package com.example.cvd_monitoring.presentation.treatment.patient_prescription

import com.example.cvd_monitoring.domain.model.treatment.Prescription

data class PrescriptionListState (
    val isLoading: Boolean = false,
    val prescriptions: List<Prescription> = emptyList(),
    val error: String = ""
)