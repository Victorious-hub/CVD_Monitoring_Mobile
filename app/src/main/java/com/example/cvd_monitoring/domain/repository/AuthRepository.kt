package com.example.cvd_monitoring.domain.repository

import com.example.cvd_monitoring.data.dto.UserDto
import com.example.cvd_monitoring.data.remote.request.AuthRequest
import com.example.cvd_monitoring.data.remote.request.CreateUserRequest
import com.example.cvd_monitoring.data.remote.response.LogoutResponse
import com.example.cvd_monitoring.utils.Resource
import retrofit2.http.Body

interface AuthRepository {
    suspend fun authenticateUser(loginRequest: AuthRequest): Resource<Unit>
    suspend fun createPatient(@Body patient: CreateUserRequest): UserDto
    suspend fun logoutUser(refreshToken: String) : LogoutResponse
}