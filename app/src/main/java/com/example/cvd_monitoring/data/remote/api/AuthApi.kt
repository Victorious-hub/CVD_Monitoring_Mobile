package com.example.cvd_monitoring.data.remote.api

import com.example.cvd_monitoring.data.dto.UserDto
import com.example.cvd_monitoring.data.remote.request.AuthRequest
import com.example.cvd_monitoring.data.remote.request.CreateUserRequest
import com.example.cvd_monitoring.data.remote.response.AuthResponse
import com.example.cvd_monitoring.data.remote.response.LogoutResponse
import com.example.cvd_monitoring.utils.Resource
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("users/v1/patients/registration")
    suspend fun createPatient(@Body patient: CreateUserRequest): UserDto
    @POST("auth/v1/authenticate")
    suspend fun authenticateUser(@Body loginRequest: AuthRequest): AuthResponse
    @POST("auth/v1/logout")
    suspend fun logoutUser(refreshToken: String): LogoutResponse
}