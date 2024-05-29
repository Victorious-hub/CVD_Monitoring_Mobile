package com.example.cvd_monitoring.data.repository

import com.example.cvd_monitoring.data.dto.AppointmentDto
import com.example.cvd_monitoring.data.dto.BloodAnalysisDto
import com.example.cvd_monitoring.data.dto.CardBloodAnalysisDto
import com.example.cvd_monitoring.data.dto.CardCholesterolAnalysisDto
import com.example.cvd_monitoring.data.dto.CholesterolAnalysisDto
import com.example.cvd_monitoring.data.dto.ConclusionDto
import com.example.cvd_monitoring.data.dto.CreateCardRequestDto
import com.example.cvd_monitoring.data.dto.DiagnosisDto
import com.example.cvd_monitoring.data.dto.DiseaseDto
import com.example.cvd_monitoring.data.dto.DoctorAppointmentDto
import com.example.cvd_monitoring.data.dto.DoctorListDto
import com.example.cvd_monitoring.data.dto.DoctorPatientsDto
import com.example.cvd_monitoring.data.dto.MedicationDto
import com.example.cvd_monitoring.data.dto.PatientCardDto
import com.example.cvd_monitoring.data.dto.PatientPrescriptionDto
import com.example.cvd_monitoring.data.dto.PrescriptionDeclineDto
import com.example.cvd_monitoring.data.dto.ScheduleDto
import com.example.cvd_monitoring.data.remote.api.DoctorApi
import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.model.analysis.CardBloodAnalysis
import com.example.cvd_monitoring.domain.model.analysis.CardCholesterolAnalysis
import com.example.cvd_monitoring.domain.model.analysis.CholesterolAnalysis
import com.example.cvd_monitoring.domain.model.doctors.DoctorPatients
import com.example.cvd_monitoring.domain.model.treatment.Appointment
import com.example.cvd_monitoring.domain.model.users.Doctor
import com.example.cvd_monitoring.domain.model.users.DoctorContact
import com.example.cvd_monitoring.domain.model.users.DoctorUser
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import retrofit2.http.Body
import retrofit2.http.Path
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val api: DoctorApi,
) : DoctorRepository {
    override suspend fun updateDoctorContact(doctor: DoctorContact, slug: String): DoctorContact {
        return api.updateDoctorContact(doctor, slug)
    }
    override suspend fun createPatientBloodAnalysis(slug: String, bloodAnalysis: CardBloodAnalysis): CardBloodAnalysisDto {
        return api.createPatientBloodAnalysis(slug, bloodAnalysis)
    }

    override suspend fun createPatientAppointment(slug: String, appointment: Appointment): AppointmentDto {
        return api.createPatientAppointment(slug, appointment)
    }

    override suspend fun createPatientCard(slug: String, patientCard: CreateCardRequestDto): CreateCardRequestDto {
        return api.createPatientCard(slug, patientCard)
    }
    override suspend fun getMedicationList(): List<MedicationDto> {
        return api.getMedicationList()
    }
    override suspend fun createPatientCholesterolAnalysis(slug: String, cholesterolAnalysis: CardCholesterolAnalysis): CardCholesterolAnalysisDto {
        return api.createPatientCholesterolAnalysis(slug, cholesterolAnalysis)
    }
    override suspend fun getCurrentDoctor(slug: String): DoctorListDto {
        return api.getCurrentDoctor(slug)
    }

    override suspend fun getDoctorPatients(slug: String): DoctorPatientsDto{
        return api.getDoctorPatients(slug)
    }

    override suspend fun createPatientPrescription(slug: String, patientPrescription: PatientPrescriptionDto): PatientPrescriptionDto{
        return  api.createPatientPrescription(slug, patientPrescription)
    }

    override suspend fun createPatientConclusion(slug: String, patientConclusion: ConclusionDto): ConclusionDto {
        return api.createPatientConclusion(slug, patientConclusion)
    }

    override suspend fun getDoctorAppointments(slug: String): List<DoctorAppointmentDto> {
        return api.getDoctorAppointments(slug)
    }

    override suspend fun getPatientDiagnosis(slug: String): DiagnosisDto {
        return api.getPatientDiagnosis(slug)
    }

    override suspend fun getPatientBloodAnalysis(slug: String): BloodAnalysisDto {
        return api.getPatientBloodAnalysis(slug)
    }

    override suspend fun getPatientCholesterolAnalysis(slug: String): CholesterolAnalysisDto {
        return api.getPatientCholesterolAnalysis(slug)
    }
    override suspend fun updatePatientBloodAnalysis(slug: String, bloodAnalysis: BloodAnalysisDto): BloodAnalysisDto {
        return api.updatePatientBloodAnalysis(slug, bloodAnalysis)
    }
    override suspend fun updatePatientCholesterolAnalysis(slug: String, cholesterolAnalysis: CholesterolAnalysisDto): CholesterolAnalysisDto {
        return api.updatePatientCholesterolAnalysis(slug, cholesterolAnalysis)
    }

    override suspend fun declinePatientPrescription(slug: String, prescriptionDecline: PrescriptionDeclineDto): PrescriptionDeclineDto {
        return api.declinePatientPrescription(slug, prescriptionDecline)
    }

    override suspend fun createDiagnosis(slug: String, disease: DiseaseDto): DiseaseDto {
        return api.createDiagnosis(slug, disease)
    }

    override suspend fun updatePatientCard(
        slug: String,
        patientCard: CreateCardRequestDto
    ): CreateCardRequestDto {
        return api.updatePatientCard(slug, patientCard)
    }

}