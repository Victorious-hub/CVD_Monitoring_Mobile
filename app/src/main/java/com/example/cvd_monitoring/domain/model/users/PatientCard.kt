package com.example.cvd_monitoring.domain.model.users

import com.example.cvd_monitoring.data.dto.Allergies
import com.example.cvd_monitoring.data.dto.Patient
import com.squareup.moshi.Json

data class PatientCard(
    val abnormalConditions: String,
    val active: Double,
    val alcohol: Double,
    val allergies: Allergies,
    val bloodType: String,
    val patient: Patient,
    val smoke: Double
)
