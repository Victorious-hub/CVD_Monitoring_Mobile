package com.example.cvd_monitoring.data.dto

import com.google.gson.annotations.SerializedName

data class AppointmentDto(
    @SerializedName("patient_slug")
    val patient_slug: String,
    @SerializedName("appointment_date")
    val appointment_date: String,
    @SerializedName("appointment_time")
    val appointment_time: String,
)