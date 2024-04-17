package com.example.cvd_monitoring.data.remote.mapper

import com.example.cvd_monitoring.data.dto.NotificationDto
import com.example.cvd_monitoring.domain.model.notifications.Notification
fun NotificationDto.toNotification() : Notification {
    return Notification(
        patient = patient,
        message = message,
        is_read = isRead,
        date_sent = dateSent,
    )
}