package com.example.cvd_monitoring.data.remote.api


import com.example.cvd_monitoring.data.dto.AppointmentDto
import com.example.cvd_monitoring.data.dto.CardBloodAnalysisDto
import com.example.cvd_monitoring.data.dto.CardCholesterolAnalysisDto
import com.example.cvd_monitoring.data.dto.ConclusionDto
import com.example.cvd_monitoring.data.dto.CreateCardRequestDto
import com.example.cvd_monitoring.data.dto.DoctorListDto
import com.example.cvd_monitoring.data.dto.DoctorPatientsDto
import com.example.cvd_monitoring.data.dto.MedicationDto
import com.example.cvd_monitoring.data.dto.PatientCardDto
import com.example.cvd_monitoring.data.dto.PatientPrescriptionDto
import com.example.cvd_monitoring.data.dto.PrescriptionDto
import com.example.cvd_monitoring.data.dto.ScheduleDto
import com.example.cvd_monitoring.domain.model.analysis.CardBloodAnalysis
import com.example.cvd_monitoring.domain.model.analysis.CardCholesterolAnalysis
import com.example.cvd_monitoring.domain.model.doctors.DoctorPatients
import com.example.cvd_monitoring.domain.model.treatment.Appointment
import com.example.cvd_monitoring.domain.model.users.Doctor
import com.example.cvd_monitoring.domain.model.users.DoctorContact
import com.example.cvd_monitoring.domain.model.users.DoctorUser
import com.example.cvd_monitoring.domain.model.users.Patient
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DoctorApi {
    @PUT("users/v1/doctors/{slug}/contact")
    suspend fun updateDoctorContact(@Body doctor: DoctorContact, @Path("slug") slug: String): DoctorContact
    @GET("users/v1/doctors/{slug}/get")
    suspend fun getCurrentDoctor(@Path("slug") slug: String): DoctorListDto
    @GET("users/v1/doctors/patient/{slug}/get")
    suspend fun getDoctorPatients(@Path("slug") slug: String): DoctorPatientsDto
    @POST("analysis/v1/patient/blood/analysis/{slug}")
    suspend fun createPatientBloodAnalysis(@Path("slug") slug: String, @Body bloodAnalysis: CardBloodAnalysis): CardBloodAnalysisDto
    @POST("analysis/v1/patient/cholesterol/analysis/{slug}")
    suspend fun createPatientCholesterolAnalysis(@Path("slug") slug: String, @Body cholesterolAnalysis: CardCholesterolAnalysis): CardCholesterolAnalysisDto
    @POST("treatment/v1/appointments/{slug}/create")
    suspend fun createPatientAppointment(@Path("slug") slug: String, @Body appointment: Appointment): AppointmentDto
    @POST("analysis/v1/patients/card/{slug}")
    suspend fun createPatientCard(@Path("slug") slug: String, @Body patientCard: CreateCardRequestDto): CreateCardRequestDto
    @GET("treatment/v1/medications")
    suspend fun getMedicationList(): List<MedicationDto>
    @POST("treatment/v1/prescriptions/{slug}/create")
    suspend fun createPatientPrescription(@Path("slug") slug: String, @Body patientPrescription: PatientPrescriptionDto): PatientPrescriptionDto
    @POST("analysis/v1/patient/conclusion/{slug}/create")
    suspend fun createPatientConclusion(@Path("slug") slug: String, @Body patientConclusion: ConclusionDto): ConclusionDto
}