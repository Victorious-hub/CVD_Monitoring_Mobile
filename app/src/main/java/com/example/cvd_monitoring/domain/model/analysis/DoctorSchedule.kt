package com.example.cvd_monitoring.domain.model.analysis

import com.example.cvd_monitoring.data.dto.UserPatientDto
import com.example.cvd_monitoring.domain.model.users.UserPatient

data class DoctorSchedule (
    val user : UserPatientDto
)