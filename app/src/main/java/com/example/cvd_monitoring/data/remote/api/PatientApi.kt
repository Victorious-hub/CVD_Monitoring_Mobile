package com.example.cvd_monitoring.data.remote.api

import com.example.cvd_monitoring.domain.model.notifications.Notification
import com.example.cvd_monitoring.domain.model.patients.PatientCard
import com.example.cvd_monitoring.domain.model.users.Patient
import com.example.cvd_monitoring.domain.model.users.PatientContact
import com.example.cvd_monitoring.domain.model.users.PatientData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface PatientApi {
    @PUT("users/v1/patients/update/{slug}/data")
    suspend fun updatePatientData(@Body patient: PatientData, @Path("slug") slug: String): PatientData
    @PUT("users/v1/patients/update/{slug}/contact")
    suspend fun updatePatientContact(@Body patient: PatientContact, @Path("slug") slug: String): PatientContact
    @GET("users/v1/patients/{slug}/get")
    suspend fun getCurrentUser(@Path("slug") slug: String): Patient
    @GET("users/v1/patients")
    suspend fun getPatients(): List<Patient>
    @GET("analysis/v1/patient/card/{slug}")
    suspend fun getPatientCard(@Path("slug") slug: String): PatientCard
    @GET("notifications/v1/{slug}/patient")
    suspend fun getPatientNotifications(@Path("slug") slug: String): Notification
}