package com.example.cvd_monitoring.domain.repository

import com.example.cvd_monitoring.data.remote.request.AuthRequest
import com.example.cvd_monitoring.domain.model.users.CreateUserRequest
import com.example.cvd_monitoring.domain.model.users.Patient
import com.example.cvd_monitoring.domain.model.users.User
import com.example.cvd_monitoring.utils.Resource
import retrofit2.http.Body

interface AuthRepository {
    suspend fun authenticateUser(loginRequest: AuthRequest): Resource<Unit>

    suspend fun createPatient(@Body patient: CreateUserRequest): User

}