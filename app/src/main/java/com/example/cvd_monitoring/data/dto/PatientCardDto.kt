package com.example.cvd_monitoring.data.dto


import com.example.cvd_monitoring.domain.model.patients.PatientCard
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class PatientCardDto(
    @SerializedName("abnormal_conditions")
    val abnormalConditions: String,
    @SerializedName("active")
    val active: Double,
    @SerializedName( "alcohol")
    val alcohol: Double,
    @SerializedName("blood_type")
    val bloodType: String,
    @SerializedName("patient")
    val patient: PatientDto,
    @SerializedName("smoke")
    val smoke: Double
)

fun PatientCardDto.toPatientCard(): PatientCard {
    return PatientCard(
        abnormalConditions = abnormalConditions,
        active = active,
        alcohol = alcohol,
        bloodType = bloodType,
        patient = patient,
        smoke = smoke,
    )
}