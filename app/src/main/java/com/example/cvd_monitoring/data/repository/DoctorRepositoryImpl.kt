package com.example.cvd_monitoring.data.repository

import com.example.cvd_monitoring.data.remote.DoctorApi
import com.example.cvd_monitoring.data.remote.PatientApi
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.model.users.DoctorContact
import com.example.cvd_monitoring.domain.model.users.Patient
import com.example.cvd_monitoring.domain.model.users.PatientCard
import com.example.cvd_monitoring.domain.model.users.PatientContact
import com.example.cvd_monitoring.domain.model.users.PatientData
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import com.example.cvd_monitoring.domain.repository.PatientRepository
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val api: DoctorApi,
) : DoctorRepository {
    override suspend fun updateDoctorContact(doctor: DoctorContact, slug: String): DoctorContact {
        return api.updateDoctorContact(doctor, slug)
    }

}