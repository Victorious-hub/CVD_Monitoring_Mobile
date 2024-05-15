package com.example.cvd_monitoring.data.repository

import com.example.cvd_monitoring.data.dto.AppointmentDto
import com.example.cvd_monitoring.data.dto.BloodAnalysisDto
import com.example.cvd_monitoring.data.dto.CholesterolAnalysisDto
import com.example.cvd_monitoring.data.dto.DoctorListDto
import com.example.cvd_monitoring.data.dto.NotificationDto
import com.example.cvd_monitoring.data.dto.PatientCardDto
import com.example.cvd_monitoring.data.dto.PatientDto
import com.example.cvd_monitoring.data.dto.PrescriptionDto
import com.example.cvd_monitoring.data.dto.ScheduleDto
import com.example.cvd_monitoring.data.remote.api.PatientApi
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.model.analysis.CholesterolAnalysis
import com.example.cvd_monitoring.domain.model.notifications.Notification
import com.example.cvd_monitoring.domain.model.users.Patient
import com.example.cvd_monitoring.domain.model.patients.PatientCard
import com.example.cvd_monitoring.domain.model.users.PatientContact
import com.example.cvd_monitoring.domain.model.users.PatientData
import com.example.cvd_monitoring.domain.repository.PatientRepository
import com.example.cvd_monitoring.utils.Resource
import retrofit2.HttpException
import javax.inject.Inject

class PatientRepositoryImpl @Inject constructor(
    private val api: PatientApi,
    private val preferences: AuthPreferences
) : PatientRepository {

    override suspend fun getPatients(): List<Patient> {
        return api.getPatients()
    }

    override suspend fun getPatientPrescriptions(slug: String): List<PrescriptionDto> {
        return api.getPatientPrescriptions(slug)
    }

    override suspend fun getPatientBloodAnalysis(slug: String): List<BloodAnalysisDto> {
        return api.getPatientBloodAnalysis(slug)
    }

    override suspend fun getPatientCholesterolAnalysis(slug: String): List<CholesterolAnalysisDto> {
        return api.getPatientCholesterolAnalysis(slug)
    }

    override suspend fun getNotificationList(slug: String): List<NotificationDto> {
        return api.getNotificationList(slug)
    }

    override suspend fun getCurrentPatient(slug: String): PatientDto {
        return api.getCurrentPatient(slug)
    }

    override suspend fun getDoctorList(slug: String): List<DoctorListDto> {
        return api.getDoctorList(slug)
    }

    override suspend fun getPatientCard(slug: String): PatientCardDto {
        preferences.savePatientSlug(slug)
        return api.getPatientCard(slug)
    }

    override suspend fun updatePatientContact(patient: PatientContact, slug: String): PatientContact {
        return api.updatePatientContact(patient, slug)
    }

    override suspend fun updatePatientData(patient: PatientData, slug: String): PatientData {
        return api.updatePatientData(patient, slug)
    }

    override suspend fun scheduleList(): List<ScheduleDto> {
        return api.scheduleList()
    }

    override suspend fun createAppointment(slug: String, appointment: AppointmentDto): AppointmentDto {
        return api.createAppointment(slug, appointment)
    }

    override suspend fun scheduleDetail(slug: String): ScheduleDto {
        return api.scheduleDetail(slug)
    }
}