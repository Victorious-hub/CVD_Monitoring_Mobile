package com.example.cvd_monitoring.domain.model.users

data class Patient(
    val age: Int,
    val birthday: String? = null,
    val gender: String,
    val height: Int,
    val mobile: String? = null,
    val user: UserPatient,
    val weight: Double,
)