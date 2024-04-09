package com.example.cvd_monitoring.domain.model.users

import com.example.cvd_monitoring.utils.Resource

data class AuthResult(
    val passwordError: String? = null,
    val emailError : String? = null,
    val result: Resource<Unit>? = null
)