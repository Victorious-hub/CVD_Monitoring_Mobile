package com.example.cvd_monitoring.data.remote

import com.example.cvd_monitoring.domain.model.users.DoctorContact
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface DoctorApi {
    @PUT("users/v1/doctors/{slug/contact")
    suspend fun updateDoctorContact(@Body doctor: DoctorContact, @Path("slug") slug: String): DoctorContact
}