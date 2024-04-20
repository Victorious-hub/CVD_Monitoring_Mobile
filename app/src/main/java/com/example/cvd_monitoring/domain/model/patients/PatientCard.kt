package com.example.cvd_monitoring.domain.model.patients

import com.example.cvd_monitoring.data.dto.PatientDto

data class PatientCard(
    val patient: PatientDto,
    val bloodType: String,
    val abnormalConditions: String,
    val smoke: Double,
    val alcohol: Double,
    val active: Double,
)
