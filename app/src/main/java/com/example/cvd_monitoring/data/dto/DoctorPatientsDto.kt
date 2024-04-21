package com.example.cvd_monitoring.data.dto


import com.example.cvd_monitoring.domain.model.doctors.DoctorPatients
import com.example.cvd_monitoring.domain.model.notifications.Notification
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class DoctorPatientsDto(
    @SerializedName("patients")
    val patients: List<PatientDto>
)

fun DoctorPatientsDto.toDoctorPatients(): DoctorPatients {
    return DoctorPatients(
        patients = patients
    )
}