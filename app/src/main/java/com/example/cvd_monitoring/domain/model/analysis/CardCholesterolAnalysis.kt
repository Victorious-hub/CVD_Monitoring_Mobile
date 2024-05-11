package com.example.cvd_monitoring.domain.model.analysis

data class CardCholesterolAnalysis (
    val patient_slug: String,
    val cholesterol : Double,
    val hdl_cholesterol : Double,
    val ldl_cholesterol : Double,
    val triglycerides : Double,
)