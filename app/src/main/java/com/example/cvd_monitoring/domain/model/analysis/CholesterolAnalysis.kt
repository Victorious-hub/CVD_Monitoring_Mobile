package com.example.cvd_monitoring.domain.model.analysis

data class CholesterolAnalysis (
    val cholesterol : Double,
    val hdlCholesterol : Double,
    val ldlCholesterol : Double,
    val triglycerides : Double,
    val createdAt: String?,
)