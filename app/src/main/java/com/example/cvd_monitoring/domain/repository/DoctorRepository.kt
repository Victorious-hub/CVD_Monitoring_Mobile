package com.example.cvd_monitoring.domain.repository

import com.example.cvd_monitoring.data.dto.AppointmentDto
import com.example.cvd_monitoring.data.dto.CardBloodAnalysisDto
import com.example.cvd_monitoring.data.dto.CardCholesterolAnalysisDto
import com.example.cvd_monitoring.data.dto.DoctorListDto
import com.example.cvd_monitoring.data.dto.DoctorPatientsDto
import com.example.cvd_monitoring.domain.model.analysis.CardBloodAnalysis
import com.example.cvd_monitoring.domain.model.analysis.CardCholesterolAnalysis
import com.example.cvd_monitoring.domain.model.analysis.CholesterolAnalysis
import com.example.cvd_monitoring.domain.model.doctors.DoctorPatients
import com.example.cvd_monitoring.domain.model.treatment.Appointment
import com.example.cvd_monitoring.domain.model.users.Doctor
import com.example.cvd_monitoring.domain.model.users.DoctorContact
import com.example.cvd_monitoring.domain.model.users.DoctorUser
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface DoctorRepository {
    suspend fun updateDoctorContact(@Body doctor: DoctorContact, slug: String): DoctorContact
    suspend fun getCurrentDoctor(slug: String): DoctorListDto
    suspend fun getDoctorPatients(slug: String): DoctorPatientsDto
    suspend fun createPatientBloodAnalysis(slug: String, @Body bloodAnalysis: CardBloodAnalysis): CardBloodAnalysisDto
    suspend fun createPatientCholesterolAnalysis(slug: String, @Body cholesterolAnalysis: CardCholesterolAnalysis): CardCholesterolAnalysisDto
    suspend fun createPatientAppointment(slug: String, @Body appointment: Appointment): AppointmentDto
}