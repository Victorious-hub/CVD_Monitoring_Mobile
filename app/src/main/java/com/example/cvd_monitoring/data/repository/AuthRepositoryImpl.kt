package com.example.cvd_monitoring.data.repository

import com.example.cvd_monitoring.data.dto.UserDto
import com.example.cvd_monitoring.data.remote.api.AuthApi
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.data.remote.request.AuthRequest
import com.example.cvd_monitoring.data.remote.request.CreateUserRequest
import com.example.cvd_monitoring.data.remote.response.LogoutResponse
import com.example.cvd_monitoring.domain.repository.AuthRepository
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.firstOrNull
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val preferences: AuthPreferences
) : AuthRepository {
    override suspend fun authenticateUser(loginRequest: AuthRequest): Resource<Unit> {
        return try {
            val response = api.authenticateUser(loginRequest)
            preferences.saveAuthToken(response.access, loginRequest.email, response.role, response.refresh)
            Resource.Success(Unit)
        }catch (e: IOException){
            Resource.Error("${e.message}")
        }catch (e: HttpException){
            Resource.Error("${e.message}")
        }
    }

    override suspend fun createPatient(patient: CreateUserRequest): UserDto {
        return api.createPatient(patient)
    }
    override suspend fun logoutUser(refreshToken: String): LogoutResponse {
        preferences.deleteToken()
        return api.logoutUser(refreshToken)
    }


}