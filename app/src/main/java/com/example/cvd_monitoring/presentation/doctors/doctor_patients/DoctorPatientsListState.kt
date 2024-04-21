package com.example.cvd_monitoring.presentation.doctors.doctor_patients

import com.example.cvd_monitoring.domain.model.doctors.DoctorPatients

data class DoctorPatientsListState(
    val isLoading: Boolean = false,
    val patients: DoctorPatients? = null,
    val error: String = ""
)