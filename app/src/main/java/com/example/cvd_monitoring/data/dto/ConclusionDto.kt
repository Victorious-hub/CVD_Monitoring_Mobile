package com.example.cvd_monitoring.data.dto

import com.google.gson.annotations.SerializedName

data class ConclusionDto (
    @SerializedName("patient_slug")
    val patientSlug: String,
    val description: String,
    val recommendations: String,
)