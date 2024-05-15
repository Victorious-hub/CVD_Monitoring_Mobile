package com.example.cvd_monitoring.domain.model.users

data class Patient(
    val address: String? = null,
    val mobile: String? = null,
    val user: UserPatient,
    val hasCard: Boolean,
)