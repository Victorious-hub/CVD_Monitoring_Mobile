package com.example.cvd_monitoring.domain.model.treatment

import com.example.cvd_monitoring.data.dto.MedicationDto

data class Prescription(
    val medication: MedicationDto,
    val dosage: String,
    val startDate: String,
    val endDate: String
)