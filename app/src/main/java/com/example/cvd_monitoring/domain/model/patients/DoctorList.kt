package com.example.cvd_monitoring.domain.model.patients

import com.example.cvd_monitoring.domain.model.users.DoctorUser


data class DoctorList (
    val profileImage: String,
    val user: DoctorUser
)