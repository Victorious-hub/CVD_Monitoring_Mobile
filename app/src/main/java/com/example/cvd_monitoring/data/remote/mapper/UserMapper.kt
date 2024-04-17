package com.example.cvd_monitoring.data.remote.mapper

import com.example.cvd_monitoring.data.dto.UserDto
import com.example.cvd_monitoring.domain.model.users.User

fun UserDto.toUser() : User {
    return User(
        first_name = firstName,
        last_name = lastName,
        email = email,
        password = password,
    )
}