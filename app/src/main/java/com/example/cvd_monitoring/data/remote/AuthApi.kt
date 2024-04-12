package com.example.cvd_monitoring.data.remote

import com.example.cvd_monitoring.data.remote.request.AuthRequest
import com.example.cvd_monitoring.data.remote.response.AuthResponse
import com.example.cvd_monitoring.domain.model.users.CreateUserRequest
import com.example.cvd_monitoring.domain.model.users.User
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("users/v1/patients/registration")
    suspend fun createPatient(@Body patient: CreateUserRequest): User

    @POST("auth/v1/authenticate")
    suspend fun authenticateUser(@Body loginRequest: AuthRequest
    ): AuthResponse
}