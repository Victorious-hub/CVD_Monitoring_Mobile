package com.example.cvd_monitoring.data.remote.api

import com.example.cvd_monitoring.data.dto.BloodAnalysisDto
import com.example.cvd_monitoring.data.dto.CholesterolAnalysisDto
import com.example.cvd_monitoring.data.dto.DoctorListDto
import com.example.cvd_monitoring.data.dto.NotificationDto
import com.example.cvd_monitoring.data.dto.PatientCardDto
import com.example.cvd_monitoring.data.dto.PatientDto
import com.example.cvd_monitoring.data.dto.PrescriptionDto
import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.model.analysis.CholesterolAnalysis
import com.example.cvd_monitoring.domain.model.notifications.Notification
import com.example.cvd_monitoring.domain.model.users.Patient
import com.example.cvd_monitoring.domain.model.patients.PatientCard
import com.example.cvd_monitoring.domain.model.treatment.Prescription
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
    suspend fun getCurrentPatient(@Path("slug") slug: String): PatientDto
    @GET("users/v1/patients")
    suspend fun getPatients(): List<Patient>
    @GET("notifications/v1/{slug}/patient")
    suspend fun getNotificationList(@Path("slug") slug: String): List<NotificationDto>
    @GET("analysis/v1/patient/blood/{slug}/get")
    suspend fun getPatientBloodAnalysis(@Path("slug") slug: String): List<BloodAnalysisDto>
    @GET("analysis/v1/patient/cholesterol/{slug}/get")
    suspend fun getPatientCholesterolAnalysis(@Path("slug") slug: String): List<CholesterolAnalysisDto>
    @GET("analysis/v1/patient/card/{slug}")
    suspend fun getPatientCard(@Path("slug") slug: String): PatientCardDto
    @GET("treatment/v1/prescriptions/{slug}/get")
    suspend fun getPatientPrescriptions(@Path("slug") slug: String): List<PrescriptionDto>
    @GET("users/v1/patients/doctors/{slug}")
    suspend fun getDoctorList(@Path("slug") slug: String): List<DoctorListDto>
}