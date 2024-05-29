package com.example.cvd_monitoring.domain.model.analysis

import com.example.cvd_monitoring.data.dto.DiagnosisDto
import com.example.cvd_monitoring.data.dto.DoctorScheduleDto
import com.google.gson.annotations.SerializedName
data class PatientConclusion(
    val createdAt: String,
    val description: String,
    val doctor: DoctorScheduleDto,
    val recommendations: String,
    val analysisResult: DiagnosisDto
)