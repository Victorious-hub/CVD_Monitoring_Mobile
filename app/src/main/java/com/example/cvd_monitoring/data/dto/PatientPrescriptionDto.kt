package com.example.cvd_monitoring.data.dto

import com.google.gson.annotations.SerializedName

data class PatientPrescriptionDto(
    @SerializedName("patient_slug")
    val patientSlug: String,
    val medication: Int,
    val dosage: String,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
)