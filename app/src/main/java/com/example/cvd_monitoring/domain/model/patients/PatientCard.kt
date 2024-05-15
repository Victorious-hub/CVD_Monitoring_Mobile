package com.example.cvd_monitoring.domain.model.patients

import androidx.compose.ui.text.font.FontWeight
import com.example.cvd_monitoring.data.dto.PatientDto

data class PatientCard(
    val patient: PatientDto?,
    val bloodType: String?,
    val abnormalConditions: String?,
    val smoke: Double?,
    val alcohol: Double?,
    val active: Double?,
    val weight: Double?,
    val height: Int?,
    val gender: String?,
    val birthday: String?,
    val age: Int?,
)
