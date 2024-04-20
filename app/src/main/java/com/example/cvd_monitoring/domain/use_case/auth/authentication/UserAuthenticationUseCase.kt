package com.example.cvd_monitoring.domain.use_case.auth.authentication

import com.example.cvd_monitoring.data.remote.request.AuthRequest
import com.example.cvd_monitoring.domain.model.users.AuthResult
import com.example.cvd_monitoring.domain.repository.AuthRepository
import javax.inject.Inject

class UserAuthenticationUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): AuthResult {
        val emailError = if (email.isBlank()) "Username cannot be blank" else null
        val passwordError = if (password.isBlank()) "Password cannot be blank" else null

        if (emailError != null){
            return AuthResult(
                emailError = emailError
            )
        }

        if (passwordError!=null){
            return AuthResult(
                passwordError = passwordError
            )
        }

        val loginRequest = AuthRequest(
            email = email.trim(),
            password = password.trim()
        )

        return AuthResult(
            result = authRepository.authenticateUser(loginRequest)
        )
    }
}