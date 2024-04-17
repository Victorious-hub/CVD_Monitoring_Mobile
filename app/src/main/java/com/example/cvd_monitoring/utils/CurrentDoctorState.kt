package com.example.cvd_monitoring.utils

import com.example.cvd_monitoring.domain.model.users.Doctor
data class CurrentDoctorState(
    val doctor: Doctor? = null,
    val error: String = ""
)