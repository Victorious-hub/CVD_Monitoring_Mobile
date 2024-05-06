package com.example.cvd_monitoring.presentation.auth.register_screen

data class PasswordValidationState (
    val hasMinimum: Boolean = false,
    val hasCapitalizedLetter: Boolean = false,
    val hasSpecialCharacter: Boolean = false,
    val successful: Boolean = false,
)