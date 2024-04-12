package com.example.cvd_monitoring.data.repository

import android.util.Log
import com.example.cvd_monitoring.data.remote.AuthApi
import com.example.cvd_monitoring.data.remote.PatientApi
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.data.remote.request.AuthRequest
import com.example.cvd_monitoring.domain.model.users.CreateUserRequest
import com.example.cvd_monitoring.domain.model.users.DoctorContact
import com.example.cvd_monitoring.domain.model.users.Patient
import com.example.cvd_monitoring.domain.model.users.PatientCard
import com.example.cvd_monitoring.domain.model.users.PatientContact
import com.example.cvd_monitoring.domain.model.users.PatientData
import com.example.cvd_monitoring.domain.model.users.User
import com.example.cvd_monitoring.domain.repository.AuthRepository
import com.example.cvd_monitoring.domain.repository.PatientRepository
import com.example.cvd_monitoring.utils.Resource
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
            Log.e("PatientListViewModel", response.toString())
            preferences.saveAuthToken(response.access)

            Resource.Success(Unit)
        }catch (e: IOException){
            Resource.Error("${e.message}")
        }catch (e: HttpException){
            Resource.Error("${e.message}")
        }
    }

    override suspend fun createPatient(patient: CreateUserRequest): User {
        return api.createPatient(patient)
    }


}