package com.example.cvd_monitoring.data.remote.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access")
    val access : String,
    @SerializedName("refresh")
    val refresh : String
)
