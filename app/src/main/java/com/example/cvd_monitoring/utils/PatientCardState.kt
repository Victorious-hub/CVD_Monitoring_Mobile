package com.example.cvd_monitoring.utils

import com.example.cvd_monitoring.domain.model.patients.PatientCard
import com.example.cvd_monitoring.domain.model.users.Patient

data class PatientCardState(
    val patientCard: PatientCard? = null,
    val error: String = ""
)
