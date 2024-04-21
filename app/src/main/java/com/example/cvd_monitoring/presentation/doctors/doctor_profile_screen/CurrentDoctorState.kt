package com.example.cvd_monitoring.presentation.doctors.doctor_profile_screen

import com.example.cvd_monitoring.domain.model.users.Doctor
data class CurrentDoctorState(
    val doctor: Doctor? = null,
    val error: String = ""
)