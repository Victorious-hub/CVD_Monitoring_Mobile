package com.example.cvd_monitoring.data.dto


import com.example.cvd_monitoring.domain.model.analysis.Diagnosis
import com.example.cvd_monitoring.domain.model.patients.PatientCard
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class DiagnosisDto(
    @SerializedName("anomaly")
    val anomaly: Boolean,
    @SerializedName("blood_analysis")
    val bloodAnalysis: BloodAnalysisDto,
    @SerializedName("cholesterol_analysis")
    val cholesterolAnalysis: CholesterolAnalysisDto
)
fun DiagnosisDto.toDiagnosis(): Diagnosis {
    return Diagnosis(
        anomaly = anomaly,
        bloodAnalysis = bloodAnalysis,
        cholesterolAnalysis = cholesterolAnalysis
    )
}