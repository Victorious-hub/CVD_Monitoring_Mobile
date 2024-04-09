package com.example.cvd_monitoring.data.repository

import android.util.Log
import com.example.cvd_monitoring.data.remote.CvdApi
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.data.remote.request.AuthRequest
import com.example.cvd_monitoring.domain.model.users.CreateUserRequest
import com.example.cvd_monitoring.domain.model.users.DoctorContact
import com.example.cvd_monitoring.domain.model.users.Patient
import com.example.cvd_monitoring.domain.model.users.PatientCard
import com.example.cvd_monitoring.domain.model.users.PatientContact
import com.example.cvd_monitoring.domain.model.users.PatientData
import com.example.cvd_monitoring.domain.model.users.User
import com.example.cvd_monitoring.domain.repository.PatientRepository
import com.example.cvd_monitoring.utils.Resource
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class PatientRepositoryImpl @Inject constructor(
    private val api: CvdApi,
    private val preferences: AuthPreferences

) : PatientRepository {
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

    override suspend fun getPatients(): List<Patient> {
        return api.getPatients()
    }

    override suspend fun getCurrentUser(slug: String): Patient {
        return api.getCurrentUser(slug)
    }

    override suspend fun getPatientCard(slug: String): PatientCard {
        return api.getPatientCard(slug)
    }

    override suspend fun updateDoctorContact(doctor: DoctorContact, slug: String): DoctorContact {
        return api.updateDoctorContact(doctor, slug)
    }

    override suspend fun updatePatientContact(patient: PatientContact, slug: String): PatientContact {
        return api.updatePatientContact(patient, slug)
    }

    override suspend fun updatePatientData(patient: PatientData, slug: String): PatientData {
        return api.updatePatientData(patient, slug)
    }

}