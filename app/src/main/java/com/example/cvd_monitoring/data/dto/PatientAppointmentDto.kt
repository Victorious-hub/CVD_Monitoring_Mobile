package com.example.cvd_monitoring.data.dto

import com.example.cvd_monitoring.domain.model.patients.DoctorList
import com.example.cvd_monitoring.domain.model.treatment.PatientAppointment
import com.google.gson.annotations.SerializedName

data class PatientAppointmentDto(
    @SerializedName("appointment_date")
    val appointmentDate: String,
    @SerializedName("appointment_time")
    val appointmentTime: String,
    val doctor: DoctorListDto
)

fun PatientAppointmentDto.toPatientAppointment(): PatientAppointment {
    return PatientAppointment(
        appointmentDate = appointmentDate,
        appointmentTime = appointmentTime,
        doctor = doctor
    )
}