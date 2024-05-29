package com.example.cvd_monitoring.data.dto


import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.google.gson.annotations.SerializedName

data class BloodAnalysisDto(
    @SerializedName("ap_hi")
    val apHi: Int,
    @SerializedName("ap_lo")
    val apLo: Int,
    @SerializedName("glucose")
    val glucose: Double,
    @SerializedName("created_at")
    val createdAt: String?,
)

fun BloodAnalysisDto.toBloodAnalysis(): BloodAnalysis {
    return BloodAnalysis(
        apHi = apHi,
        apLo = apLo,
        glucose = glucose,
        createdAt = createdAt
    )
}