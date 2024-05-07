package com.example.cvd_monitoring.domain.repository

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
import com.example.cvd_monitoring.domain.model.users.PatientContact
import com.example.cvd_monitoring.domain.model.users.PatientData
import retrofit2.http.Body
import retrofit2.http.Path

interface PatientRepository {
    suspend fun updatePatientData(@Body patient: PatientData, slug: String): PatientData

    suspend fun getPatientCard(slug: String): PatientCardDto

    suspend fun updatePatientContact(@Body patient: PatientContact, slug: String): PatientContact

    suspend fun getPatients(): List<Patient>

    suspend fun getPatientPrescriptions(slug: String): List<PrescriptionDto>

    suspend fun getPatientBloodAnalysis(slug: String): List<BloodAnalysisDto>

    suspend fun getPatientCholesterolAnalysis(slug: String): List<CholesterolAnalysisDto>
    suspend fun getNotificationList(slug: String): List<NotificationDto>

    suspend fun getCurrentPatient(slug: String): PatientDto
    suspend fun getDoctorList(slug: String): List<DoctorListDto>

}