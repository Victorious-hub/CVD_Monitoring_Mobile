package com.example.cvd_monitoring.presentation.patient_card_screen

import com.example.cvd_monitoring.domain.model.users.Patient
import com.example.cvd_monitoring.domain.model.users.PatientCard

data class PatientCardState (
    val patientCard: PatientCard? = null,
    val error: String = ""
)