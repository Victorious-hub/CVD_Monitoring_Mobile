package com.example.cvd_monitoring.data.dto


import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.model.treatment.Medication
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class MedicationDto(
    @SerializedName("created_at")
    val createdAt: String,
    val description: String,
    val dosage: Double,
    val name: String,
    val id: Int,
)

fun MedicationDto.toMedication(): Medication {
    return Medication(
        createdAt = createdAt,
        description = description,
        dosage = dosage,
        name = name,
        id = id,
    )
}