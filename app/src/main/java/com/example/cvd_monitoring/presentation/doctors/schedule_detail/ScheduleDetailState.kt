package com.example.cvd_monitoring.presentation.doctors.schedule_detail

import com.example.cvd_monitoring.domain.model.analysis.Schedule
data class ScheduleDetailState(
    val isLoading: Boolean = false,
    val schedule: Schedule? = null,
    val error: String = ""
)
