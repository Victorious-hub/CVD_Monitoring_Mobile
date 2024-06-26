package com.example.cvd_monitoring.presentation.doctors.doctor_profile_screen

import com.example.cvd_monitoring.domain.model.patients.DoctorList
import com.example.cvd_monitoring.domain.model.users.Doctor
import com.example.cvd_monitoring.domain.model.users.DoctorUser
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.Flow

data class CurrentDoctorState(
    val isLoading: Boolean = false,
    val doctor: DoctorList? = null,
    val error: String = ""
)