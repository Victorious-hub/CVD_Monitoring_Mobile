package com.example.cvd_monitoring.domain.model.treatment

data class Appointment(
    val patient_slug: String,
    val appointment_date: String,
    val appointment_time: String,
)
