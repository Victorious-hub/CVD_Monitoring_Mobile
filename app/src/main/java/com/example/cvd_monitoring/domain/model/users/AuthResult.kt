package com.example.cvd_monitoring.domain.model.users

import com.example.cvd_monitoring.utils.Resource
import com.google.gson.annotations.SerializedName

data class AuthResult(
    val passwordError: String? = null,
    val emailError : String? = null,
    val result: Resource<Unit>? = null,
    val token: String? = null
)