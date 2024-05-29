package com.example.cvd_monitoring.data.dto


import com.example.cvd_monitoring.domain.model.treatment.Prescription
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class PrescriptionDto(
    @SerializedName("medication")
    val medication: MedicationDto,
    @SerializedName("dosage")
    val dosage: String,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
    val id: Int,
    @SerializedName("is_declined")
    val isDeclined: Boolean,
)

fun PrescriptionDto.toPrescription(): Prescription {
    return Prescription(
        medication = medication,
        dosage = dosage,
        endDate = endDate,
        startDate = startDate,
        id = id,
        isDeclined = isDeclined
    )
}