package com.example.cvd_monitoring.presentation.patients.doctor_list

import com.example.cvd_monitoring.domain.model.patients.DoctorList

data class DoctorListState(
    val isLoading: Boolean = false,
    val doctorList: List<DoctorList> = emptyList(),
    val error: String = ""
)