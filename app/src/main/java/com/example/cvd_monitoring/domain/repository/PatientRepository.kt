package com.example.cvd_monitoring.domain.repository

import com.example.cvd_monitoring.domain.model.notifications.Notification
import com.example.cvd_monitoring.domain.model.patients.PatientCard
import com.example.cvd_monitoring.domain.model.users.Patient
import com.example.cvd_monitoring.domain.model.users.PatientContact
import com.example.cvd_monitoring.domain.model.users.PatientData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface PatientRepository {
    suspend fun updatePatientData(@Body patient: PatientData, slug: String): PatientData

    suspend fun getPatientCard(slug: String): PatientCard

    suspend fun updatePatientContact(@Body patient: PatientContact, slug: String): PatientContact

    suspend fun getPatients(): List<Patient>

    suspend fun getCurrentUser(slug: String): Patient

    suspend fun getPatientNotifications(slug: String): Notification
}