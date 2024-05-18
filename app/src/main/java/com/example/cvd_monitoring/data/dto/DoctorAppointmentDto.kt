package com.example.cvd_monitoring.data.dto


import com.example.cvd_monitoring.domain.model.notifications.DoctorAppointment
import com.google.gson.annotations.SerializedName

data class DoctorAppointmentDto(
    @SerializedName("appointment_date")
    val appointmentDate: String,
    @SerializedName("appointment_time")
    val appointmentTime: String,
    @SerializedName("patient")
    val patient: DoctorScheduleDto
)

fun DoctorAppointmentDto.toDoctorAppointment(): DoctorAppointment {
    return DoctorAppointment(
        appointmentDate = appointmentDate,
        appointmentTime = appointmentTime,
        patient = patient
    )
}