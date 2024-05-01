package com.example.cvd_monitoring.domain.model.notifications

import com.example.cvd_monitoring.domain.model.users.Patient

data class Notification(
    val message: String,
    val isRead: Boolean,
    val createdAt: String
)
