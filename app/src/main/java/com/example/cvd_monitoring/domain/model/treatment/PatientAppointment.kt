package com.example.cvd_monitoring.domain.model.treatment

import com.example.cvd_monitoring.data.dto.DoctorListDto
import com.example.cvd_monitoring.data.dto.UserPatientDto
import com.google.gson.annotations.SerializedName

data class PatientAppointment(
    val appointmentDate: String,
    val appointmentTime: String,
    val doctor: DoctorListDto
)
