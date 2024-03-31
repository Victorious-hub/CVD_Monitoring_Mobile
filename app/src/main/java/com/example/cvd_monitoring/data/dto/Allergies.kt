package com.example.cvd_monitoring.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Allergies(
    @Json(name = "milk")
    val milk: String,
    @Json(name = "pollen")
    val pollen: String
)