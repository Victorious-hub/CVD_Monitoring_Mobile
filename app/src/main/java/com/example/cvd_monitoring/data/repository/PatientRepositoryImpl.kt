package com.example.cvd_monitoring.data.repository

import com.example.cvd_monitoring.data.remote.PatientApi
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.model.users.Patient
import com.example.cvd_monitoring.domain.model.users.PatientCard
import com.example.cvd_monitoring.domain.model.users.PatientContact
import com.example.cvd_monitoring.domain.model.users.PatientData
import com.example.cvd_monitoring.domain.repository.PatientRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class PatientRepositoryImpl @Inject constructor(
    private val api: PatientApi,
    private val preferences: AuthPreferences
) : PatientRepository {

    override suspend fun getPatients(): List<Patient> {
        return api.getPatients()
    }

    override suspend fun getCurrentUser(slug: String): Patient {
        return api.getCurrentUser(slug)
    }

    override suspend fun getPatientCard(slug: String): PatientCard {
        return api.getPatientCard(slug)
    }

    override suspend fun updatePatientContact(patient: PatientContact, slug: String): PatientContact {
        return api.updatePatientContact(patient, slug)
    }

    override suspend fun updatePatientData(patient: PatientData, slug: String): PatientData {
        return api.updatePatientData(patient, slug)
    }

}