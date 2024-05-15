package com.example.cvd_monitoring.data.dto

import com.google.gson.annotations.SerializedName

data class AppointmentDto(
    @SerializedName("doctor_slug")
    val doctorSlug: String,
    @SerializedName("appointment_date")
    val appointmentDate: String,
    @SerializedName("appointment_time")
    val appointmentTime: String,
)