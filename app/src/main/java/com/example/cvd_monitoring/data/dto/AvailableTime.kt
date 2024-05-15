package com.example.cvd_monitoring.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AvailableTime(
    @Json(name = "2020-10-10")
    val x20201010: List<String>
)