package com.example.cvd_monitoring.domain.model.patients

import com.example.cvd_monitoring.domain.model.users.Patient
data class PatientCard(
    val patient: Patient,
    val blood_type : String,
    val allergies: List<String>,
    val abnormal_conditions: String,
    val smoke: Double,
    val alcohol: Double,
    val active: Double,
)
