package com.example.cvd_monitoring.domain.model.patients

import com.example.cvd_monitoring.data.dto.UserDto
import com.example.cvd_monitoring.data.dto.toDoctorUser
import com.example.cvd_monitoring.domain.model.users.DoctorUser
import com.example.cvd_monitoring.domain.model.users.User
import com.squareup.moshi.Json

data class DoctorList (
    val profileImage: String,
    val user: DoctorUser
)