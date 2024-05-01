package com.example.cvd_monitoring.data.dto

import com.example.cvd_monitoring.domain.model.notifications.Notification
import com.example.cvd_monitoring.domain.model.treatment.Prescription
import com.example.cvd_monitoring.domain.model.users.Patient
import com.google.gson.annotations.SerializedName

data class NotificationDto (
    val message: String,
    @SerializedName("is_read")
    val isRead: Boolean,
    @SerializedName("created_at")
    val createdAt: String
)

fun NotificationDto.toNotification(): Notification {
    return Notification(
        message = message,
        isRead = isRead,
        createdAt = createdAt,
    )
}