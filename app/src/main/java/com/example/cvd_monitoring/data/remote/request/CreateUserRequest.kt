package com.example.cvd_monitoring.data.remote.request

import com.example.cvd_monitoring.domain.model.users.User

data class CreateUserRequest(
    val user: User
)