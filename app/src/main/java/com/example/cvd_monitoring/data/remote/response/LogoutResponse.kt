package com.example.cvd_monitoring.data.remote.response

import com.google.gson.annotations.SerializedName
data class LogoutResponse(
    @SerializedName("refresh_token")
    val refreshToken: String,
)
