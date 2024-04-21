package com.example.cvd_monitoring.domain.model.doctors

import com.example.cvd_monitoring.data.dto.PatientDto
import com.google.gson.annotations.SerializedName

data class DoctorPatients(
    val patients: List<PatientDto>
)
