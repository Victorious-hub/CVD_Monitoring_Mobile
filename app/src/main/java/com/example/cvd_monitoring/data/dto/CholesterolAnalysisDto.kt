package com.example.cvd_monitoring.data.dto


import com.example.cvd_monitoring.domain.model.analysis.CholesterolAnalysis
import com.google.gson.annotations.SerializedName

data class CholesterolAnalysisDto(
    @SerializedName("cholesterol")
    val cholesterol: Double,
    @SerializedName("hdl_cholesterol")
    val hdlCholesterol: Double,
    @SerializedName("ldl_cholesterol")
    val ldlCholesterol: Double,
    @SerializedName("triglycerides")
    val triglycerides: Double,
    @SerializedName("created_at")
    val createdAt: String?,
)

fun CholesterolAnalysisDto.toCholesterolAnalysis(): CholesterolAnalysis {
    return CholesterolAnalysis(
        cholesterol = cholesterol,
        hdlCholesterol = hdlCholesterol,
        ldlCholesterol = ldlCholesterol,
        triglycerides = triglycerides,
        createdAt = createdAt,
    )
}