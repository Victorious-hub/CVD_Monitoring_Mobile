package com.example.cvd_monitoring.domain.model.notifications

import com.example.cvd_monitoring.data.dto.DoctorScheduleDto

data class DoctorAppointment(
    val appointmentDate: String,
    val appointmentTime: String,
    val patient: DoctorScheduleDto
)
