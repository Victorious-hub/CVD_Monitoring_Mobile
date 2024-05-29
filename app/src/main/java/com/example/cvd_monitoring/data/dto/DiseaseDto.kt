package com.example.cvd_monitoring.data.dto

import com.google.gson.annotations.SerializedName

data class DiseaseDto (
    @SerializedName("patient_slug")
    val patientSlug: String
)