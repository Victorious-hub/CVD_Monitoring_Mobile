package com.example.cvd_monitoring.domain.model.analysis

import com.example.cvd_monitoring.domain.model.patients.PatientCard

data class CardBloodAnalysis (
    val patient_slug: String,
    val ap_hi: Int,
    val ap_lo: Int,
    val glucose: Int,
)