package com.example.cvd_monitoring.data.dto


import com.example.cvd_monitoring.domain.model.patients.PatientCard
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class PatientCardDto(
    @SerializedName("abnormal_conditions")
    val abnormalConditions: String,
    val active: Double,
    val alcohol: Double,
    @SerializedName("blood_type")
    val bloodType: String,
    val patient: PatientDto,
    val smoke: Double,
    val weight: Double,
    val height: Int,
    val gender: String,
    val birthday: String,
    val age: Int,
    @SerializedName("is_cholesterol_analysis")
    val isCholesterolAnalysis: Boolean,
    @SerializedName("is_blood_analysis")
    val isBloodAnalysis: Boolean,
    @SerializedName("is_confirmed")
    val isConfirmed: Boolean,
)

fun PatientCardDto.toPatientCard(): PatientCard {
    return PatientCard(
        abnormalConditions = abnormalConditions,
        active = active,
        alcohol = alcohol,
        bloodType = bloodType,
        patient = patient,
        smoke = smoke,
        weight = weight,
        height = height,
        gender = gender,
        birthday = birthday,
        age = age,
        isCholesterolAnalysis = isCholesterolAnalysis,
        isBloodAnalysis = isBloodAnalysis,
        isConfirmed = isConfirmed
    )
}