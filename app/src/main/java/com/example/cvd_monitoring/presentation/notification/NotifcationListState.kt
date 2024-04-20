package com.example.cvd_monitoring.presentation.notification

import com.example.cvd_monitoring.domain.model.notifications.Notification
import com.example.cvd_monitoring.domain.model.treatment.Prescription

data class NotificationListState (
    val isLoading: Boolean = false,
    val notifications: List<Notification> = emptyList(),
    val error: String = ""
)