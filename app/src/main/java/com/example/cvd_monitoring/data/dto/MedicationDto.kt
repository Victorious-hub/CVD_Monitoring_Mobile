package com.example.cvd_monitoring.data.dto


import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class MedicationDto(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("dosage")
    val dosage: String,
    @SerializedName("name")
    val name: String
)