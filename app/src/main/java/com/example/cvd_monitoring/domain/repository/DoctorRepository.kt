package com.example.cvd_monitoring.domain.repository

import com.example.cvd_monitoring.domain.model.users.DoctorContact
import retrofit2.http.Body

interface DoctorRepository {
    suspend fun updateDoctorContact(@Body doctor: DoctorContact, slug: String): DoctorContact
}