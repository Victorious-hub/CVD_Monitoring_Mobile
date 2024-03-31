package com.example.cvd_monitoring.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Patient(
    @Json(name = "age")
    val age: Int,
    @Json(name = "height")
    val height: Int,
    @Json(name = "user.email")
    val userEmail: String,
    @Json(name = "user.first_name")
    val userFirstName: String,
    @Json(name = "user.last_name")
    val userLastName: String,
    @Json(name = "weight")
    val weight: Double
)