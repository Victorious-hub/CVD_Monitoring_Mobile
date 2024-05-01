package com.example.cvd_monitoring.domain.model.analysis

import com.example.cvd_monitoring.domain.model.users.DoctorUser

data class BloodAnalysis (
    val glucose: Double,
    val apHi: Int,
    val apLo: Int,
)