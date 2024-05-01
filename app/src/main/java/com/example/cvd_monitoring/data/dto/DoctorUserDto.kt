package com.example.cvd_monitoring.data.dto


import com.example.cvd_monitoring.domain.model.users.DoctorUser
import com.google.gson.annotations.SerializedName

data class DoctorUserDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String
)

fun DoctorUserDto.toDoctorUser(): DoctorUser {
    return DoctorUser(
        firstName = firstName,
        lastName = lastName,
        email = email,
    )
}