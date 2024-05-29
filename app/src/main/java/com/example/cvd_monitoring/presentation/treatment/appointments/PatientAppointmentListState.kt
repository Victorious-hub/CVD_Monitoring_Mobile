package com.example.cvd_monitoring.presentation.treatment.appointments

import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.model.treatment.PatientAppointment

data class PatientAppointmentListState (
    val isLoading: Boolean = false,
    val patientAppointment: List<PatientAppointment> = emptyList(),
    val error: String = ""
)