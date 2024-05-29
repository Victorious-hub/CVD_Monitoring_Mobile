package com.example.cvd_monitoring.data.remote.api


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
import com.example.cvd_monitoring.data.dto.PrescriptionDto
import com.example.cvd_monitoring.data.dto.ScheduleDto
import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.model.analysis.CardBloodAnalysis
import com.example.cvd_monitoring.domain.model.analysis.CardCholesterolAnalysis
import com.example.cvd_monitoring.domain.model.doctors.DoctorPatients
import com.example.cvd_monitoring.domain.model.treatment.Appointment
import com.example.cvd_monitoring.domain.model.users.Doctor
import com.example.cvd_monitoring.domain.model.users.DoctorContact
import com.example.cvd_monitoring.domain.model.users.DoctorUser
import com.example.cvd_monitoring.domain.model.users.Patient
import retrofit2.http.Body
import retrofit2.http.DELETE
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
    @GET("treatment/v1/appointments/{slug}")
    suspend fun getDoctorAppointments(@Path("slug") slug: String): List<DoctorAppointmentDto>
    @GET("analysis/v1/patient/diagnosis/{slug}")
    suspend fun getPatientDiagnosis(@Path("slug") slug: String): DiagnosisDto
    @GET("analysis/v1/blood/{slug}/get/last")
    suspend fun getPatientBloodAnalysis(@Path("slug") slug: String): BloodAnalysisDto
    @GET("analysis/v1/cholesterol/{slug}/get/last")
    suspend fun getPatientCholesterolAnalysis(@Path("slug") slug: String): CholesterolAnalysisDto
    @PUT("analysis/v1/blood/{slug}/update")
    suspend fun updatePatientBloodAnalysis(@Path("slug") slug: String, @Body bloodAnalysis: BloodAnalysisDto): BloodAnalysisDto
    @PUT("analysis/v1/cholesterol/{slug}/update")
    suspend fun updatePatientCholesterolAnalysis(@Path("slug") slug: String, @Body cholesterolAnalysis: CholesterolAnalysisDto): CholesterolAnalysisDto
    @PUT("treatment/v1/prescriptions/decline/{slug}")
    suspend fun declinePatientPrescription(@Path("slug") slug: String, @Body prescriptionDecline: PrescriptionDeclineDto): PrescriptionDeclineDto
    @POST("analysis/v1/diagnosis/{slug}/create")
    suspend fun createDiagnosis(@Path("slug") slug: String, @Body disease: DiseaseDto): DiseaseDto
    @PUT("analysis/v1/patient/card/{slug}/update")
    suspend fun updatePatientCard(@Path("slug") slug: String, @Body patientCard: CreateCardRequestDto): CreateCardRequestDto
}