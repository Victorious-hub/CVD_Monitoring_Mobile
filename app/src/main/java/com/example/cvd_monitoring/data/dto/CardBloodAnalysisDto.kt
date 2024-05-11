package com.example.cvd_monitoring.data.dto

import com.google.gson.annotations.SerializedName

data class CardBloodAnalysisDto(
    @SerializedName("patient_slug")
    val patientSlug: String,
    @SerializedName("ap_hi")
    val apHi: Int,
    @SerializedName("ap_lo")
    val apLo: Int,
    val glucose: Int,
)