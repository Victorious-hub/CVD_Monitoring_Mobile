package com.example.cvd_monitoring.domain.repository

import com.example.cvd_monitoring.domain.model.users.Patient
import com.example.cvd_monitoring.domain.model.users.PatientCard
import com.example.cvd_monitoring.domain.model.users.PatientContact
import com.example.cvd_monitoring.domain.model.users.PatientData
import retrofit2.http.Body

interface PatientRepository {
    suspend fun updatePatientData(@Body patient: PatientData, slug: String): PatientData

    suspend fun getPatientCard(slug: String): PatientCard

    suspend fun updatePatientContact(@Body patient: PatientContact, slug: String): PatientContact

    suspend fun getPatients(): List<Patient>

    suspend fun getCurrentUser(slug: String): Patient
}