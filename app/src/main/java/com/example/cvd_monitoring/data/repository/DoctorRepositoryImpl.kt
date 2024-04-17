package com.example.cvd_monitoring.data.repository

import com.example.cvd_monitoring.data.remote.api.DoctorApi
import com.example.cvd_monitoring.domain.model.users.Doctor
import com.example.cvd_monitoring.domain.model.users.DoctorContact
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val api: DoctorApi,
) : DoctorRepository {
    override suspend fun updateDoctorContact(doctor: DoctorContact, slug: String): DoctorContact {
        return api.updateDoctorContact(doctor, slug)
    }

    override suspend fun getCurrentDoctor(slug: String): Doctor {
        return api.getCurrentDoctor(slug)
    }

}