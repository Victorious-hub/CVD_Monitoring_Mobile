package com.example.cvd_monitoring.presentation.doctors.schedule

import com.example.cvd_monitoring.domain.model.analysis.Schedule
import com.example.cvd_monitoring.domain.model.treatment.Medication
data class ScheduleState(
    val isLoading: Boolean = false,
    val schedule: List<Schedule> = emptyList(),
    val error: String = ""
)