package com.example.cvd_monitoring.data.dto


import com.example.cvd_monitoring.domain.model.users.Patient
import com.example.cvd_monitoring.domain.model.users.UserPatient
import com.google.gson.annotations.SerializedName

data class PatientDto(
    @SerializedName("age")
    val age: Int,
    @SerializedName("birthday")
    val birthday: String? = null,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Double,
    @SerializedName("user")
    val user: UserPatientDto,
    val mobile: String? = null,
)

fun PatientDto.toPatient(): Patient {
    return Patient(
        age = age,
        birthday = birthday,
        gender = gender,
        height = height,
        weight = weight,
        mobile = mobile,
        user = user.toUserPatient(),
    )
}
