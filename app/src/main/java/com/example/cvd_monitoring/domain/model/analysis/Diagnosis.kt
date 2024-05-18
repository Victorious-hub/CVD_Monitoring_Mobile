package com.example.cvd_monitoring.domain.model.analysis

import com.example.cvd_monitoring.data.dto.BloodAnalysisDto
import com.example.cvd_monitoring.data.dto.CholesterolAnalysisDto
import com.google.gson.annotations.SerializedName

data class Diagnosis(
    val anomaly: Boolean,
    val bloodAnalysis: BloodAnalysisDto,
    val cholesterolAnalysis: CholesterolAnalysisDto
)
