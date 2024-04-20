package com.example.cvd_monitoring.data.dto


import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.model.treatment.Prescription
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class BloodAnalysisDto(
    @SerializedName("ap_hi")
    val apHi: Int,
    @SerializedName("ap_lo")
    val apLo: Int,
    @SerializedName("glucose")
    val glucose: Double
)

fun BloodAnalysisDto.toBloodAnalysis(): BloodAnalysis {
    return BloodAnalysis(
        apHi = apHi,
        apLo = apLo,
        glucose = glucose,
    )
}