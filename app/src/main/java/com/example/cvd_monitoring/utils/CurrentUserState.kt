package com.example.cvd_monitoring.utils

import com.example.cvd_monitoring.domain.model.users.Patient

data class CurrentUserState(
    val patient: Patient? = null,
    val error: String = ""
)
