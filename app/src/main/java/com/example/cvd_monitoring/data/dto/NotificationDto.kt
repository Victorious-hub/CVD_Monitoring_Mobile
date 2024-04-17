package com.example.cvd_monitoring.data.dto

import com.example.cvd_monitoring.domain.model.users.Patient
import com.google.gson.annotations.SerializedName

data class NotificationDto (
    val patient: Patient,
    val message: String,
    @SerializedName("is_read")
    val isRead: Boolean,
    @SerializedName("date_sent")
    val dateSent: String
)