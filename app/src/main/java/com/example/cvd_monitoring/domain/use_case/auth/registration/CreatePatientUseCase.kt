package com.example.cvd_monitoring.domain.use_case.auth.registration

import com.example.cvd_monitoring.data.dto.UserDto
import com.example.cvd_monitoring.data.remote.request.CreateUserRequest
import com.example.cvd_monitoring.domain.model.users.User
import com.example.cvd_monitoring.domain.repository.AuthRepository
import javax.inject.Inject

class CreatePatientUseCase @Inject constructor (
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(firstName: String, lastName: String, email: String, password: String): UserDto {
        val user = User(firstName, lastName, email, password)
        val createUserRequest = CreateUserRequest(user)
        return authRepository.createPatient(createUserRequest)
    }
}