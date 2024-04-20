package com.example.cvd_monitoring.domain.model.treatment

import com.squareup.moshi.Json

data class Medication (
    val name: String,
    val dosage: String,
    val description: String,
    val createdAt: String,
)