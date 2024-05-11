package com.example.cvd_monitoring.data.dto

import com.google.gson.annotations.SerializedName

data class CardCholesterolAnalysisDto(
    @SerializedName("patient_slug")
    val patientSlug: String,
    val cholesterol: Double,
    @SerializedName("hdl_cholesterol")
    val hdlCholesterol: Double,
    @SerializedName("ldl_cholesterol")
    val ldlCholesterol: Double,
    @SerializedName("triglycerides")
    val triglycerides: Double,
)