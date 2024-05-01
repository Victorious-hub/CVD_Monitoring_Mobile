package com.example.cvd_monitoring.data.remote.api


import com.example.cvd_monitoring.data.dto.DoctorListDto
import com.example.cvd_monitoring.data.dto.DoctorPatientsDto
import com.example.cvd_monitoring.domain.model.doctors.DoctorPatients
import com.example.cvd_monitoring.domain.model.users.Doctor
import com.example.cvd_monitoring.domain.model.users.DoctorContact
import com.example.cvd_monitoring.domain.model.users.DoctorUser
import com.example.cvd_monitoring.domain.model.users.Patient
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface DoctorApi {
    @PUT("users/v1/doctors/{slug}/contact")
    suspend fun updateDoctorContact(@Body doctor: DoctorContact, @Path("slug") slug: String): DoctorContact
    @GET("users/v1/doctors/{slug}/get")
    suspend fun getCurrentDoctor(@Path("slug") slug: String): DoctorListDto
    @GET("users/v1/doctors/patient/{slug}/get")
    suspend fun getDoctorPatients(@Path("slug") slug: String): DoctorPatientsDto
}