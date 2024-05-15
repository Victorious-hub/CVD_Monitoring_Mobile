package com.example.cvd_monitoring.data.repository

import com.example.cvd_monitoring.data.dto.AppointmentDto
import com.example.cvd_monitoring.data.dto.CardBloodAnalysisDto
import com.example.cvd_monitoring.data.dto.CardCholesterolAnalysisDto
import com.example.cvd_monitoring.data.dto.CholesterolAnalysisDto
import com.example.cvd_monitoring.data.dto.ConclusionDto
import com.example.cvd_monitoring.data.dto.CreateCardRequestDto
import com.example.cvd_monitoring.data.dto.DoctorListDto
import com.example.cvd_monitoring.data.dto.DoctorPatientsDto
import com.example.cvd_monitoring.data.dto.MedicationDto
import com.example.cvd_monitoring.data.dto.PatientCardDto
import com.example.cvd_monitoring.data.dto.PatientPrescriptionDto
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

    override suspend fun createPatientConclusion(
        slug: String,
        patientConclusion: ConclusionDto
    ): ConclusionDto {
        return api.createPatientConclusion(slug, patientConclusion)
    }


}