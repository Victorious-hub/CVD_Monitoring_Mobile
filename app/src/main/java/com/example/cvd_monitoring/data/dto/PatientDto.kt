package com.example.cvd_monitoring.data.dto


import com.example.cvd_monitoring.domain.model.users.Patient
import com.example.cvd_monitoring.domain.model.users.UserPatient
import com.google.gson.annotations.SerializedName

data class PatientDto(
    val address: String,
    @SerializedName("has_card")
    val hasCard: Boolean,
    @SerializedName("user")
    val user: UserPatientDto,
    val mobile: String? = null,
)

fun PatientDto.toPatient(): Patient {
    return Patient(
        address = address,
        mobile = mobile,
        hasCard = hasCard,
        user = user.toUserPatient(),
    )
}
