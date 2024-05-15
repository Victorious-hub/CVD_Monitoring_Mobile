package com.example.cvd_monitoring.data.dto

import com.google.gson.annotations.SerializedName


data class CreateCardRequestDto(
    @SerializedName("patient_slug")
    val patientSlug: String,
    @SerializedName("abnormal_conditions")
    val abnormalConditions: String,
    @SerializedName("blood_type")
    val bloodType: String,
    val active: Boolean,
    val alcohol: Boolean,
    val smoke: Boolean,
    val weight: Double,
    val height: Int,
    val gender: String,
    val birthday: String
)