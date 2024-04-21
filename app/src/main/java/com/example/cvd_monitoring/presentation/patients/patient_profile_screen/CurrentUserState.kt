package com.example.cvd_monitoring.presentation.patients.patient_profile_screen

import com.example.cvd_monitoring.domain.model.users.Patient

data class CurrentUserState(
    val patient: Patient? = null,
    val error: String = ""
)
