package com.example.cvd_monitoring.domain.model.notifications

import com.example.cvd_monitoring.domain.model.users.Patient

data class Notification(
    val patient: Patient,
    val message: String,
    val is_read: Boolean,
    val date_sent: String
)
