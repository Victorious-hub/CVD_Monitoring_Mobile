package com.example.cvd_monitoring.data.dto

import com.google.gson.annotations.SerializedName

data class DoctorScheduleDto (
    @SerializedName("profile_image")
    val profileImage: String,
    val description: String,
    val experience: Int,
    val user: UserPatientDto
)