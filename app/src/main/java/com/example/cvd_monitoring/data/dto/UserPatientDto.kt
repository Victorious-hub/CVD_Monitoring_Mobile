package com.example.cvd_monitoring.data.dto

import com.example.cvd_monitoring.domain.model.users.UserPatient
import com.google.gson.annotations.SerializedName
data class UserPatientDto (
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val email: String,
)

fun UserPatientDto.toUserPatient(): UserPatient {
    return UserPatient(
        firstName = firstName,
        lastName = lastName,
        email = email,
    )
}