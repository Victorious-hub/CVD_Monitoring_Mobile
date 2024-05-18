package com.example.cvd_monitoring.presentation.doctors.doctor_appointment

import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.model.notifications.DoctorAppointment
import com.example.cvd_monitoring.domain.model.users.Doctor

data class DoctorAppointmentState (
    val isLoading: Boolean = false,
    val appointment: List<DoctorAppointment> = emptyList(),
    val error: String = ""
)