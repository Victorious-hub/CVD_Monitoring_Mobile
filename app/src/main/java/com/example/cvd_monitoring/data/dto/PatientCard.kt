package com.example.cvd_monitoring.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PatientCard(
    @Json(name = "abnormal_conditions")
    val abnormalConditions: String,
    @Json(name = "active")
    val active: Double,
    @Json(name = "alcohol")
    val alcohol: Double,
    @Json(name = "allergies")
    val allergies: Allergies,
    @Json(name = "blood_type")
    val bloodType: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "patient")
    val patient: Patient,
    @Json(name = "smoke")
    val smoke: Double
)