package com.example.cvd_monitoring.data.dto


import com.example.cvd_monitoring.domain.model.analysis.PatientConclusion
import com.example.cvd_monitoring.domain.model.patients.PatientCard
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class PatientConclusionDto(
    @SerializedName("created_at")
    val createdAt: String,
    val description: String,
    val doctor: DoctorScheduleDto,
    val recommendations: String
)

fun PatientConclusionDto.toPatientConclusion(): PatientConclusion {
    return PatientConclusion(
        createdAt = createdAt,
        description = description,
        doctor = doctor,
        recommendations = recommendations,
    )
}