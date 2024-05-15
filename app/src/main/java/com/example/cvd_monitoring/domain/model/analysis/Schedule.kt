package com.example.cvd_monitoring.domain.model.analysis

import com.example.cvd_monitoring.data.dto.DoctorScheduleDto
import com.example.cvd_monitoring.data.dto.UserPatientDto
import com.example.cvd_monitoring.domain.model.users.UserPatient

data class Schedule(
    val availableTime: Map<String, List<String>>,
    val doctor: DoctorScheduleDto
)
