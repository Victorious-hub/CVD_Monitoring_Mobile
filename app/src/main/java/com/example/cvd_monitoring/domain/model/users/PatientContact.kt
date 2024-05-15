package com.example.cvd_monitoring.domain.model.users

data class PatientContact(
    val mobile: String,
    val address: String,
    val user: UserContact,
)
