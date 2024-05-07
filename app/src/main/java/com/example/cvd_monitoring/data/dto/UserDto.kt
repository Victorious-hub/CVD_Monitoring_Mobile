package com.example.cvd_monitoring.data.dto

import com.google.gson.annotations.SerializedName

data class UserDto (
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val email: String,
    val password: String,
)