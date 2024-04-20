package com.example.cvd_monitoring.data.dto


import com.example.cvd_monitoring.domain.model.patients.PatientCard
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class PatientDto(
    @SerializedName("age")
    val age: Int,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("user.email")
    val userEmail: String,
    @SerializedName("user.first_name")
    val userFirstName: String,
    @SerializedName("user.last_name")
    val userLastName: String,
    @SerializedName("weight")
    val weight: Double
)
