package com.example.cvd_monitoring.presentation.patients.patient_card

import com.example.cvd_monitoring.domain.model.patients.PatientCard

data class PatientCardState(
    val isLoading: Boolean = false,
    val patientCard: PatientCard? = null,
    val error: String = ""
)