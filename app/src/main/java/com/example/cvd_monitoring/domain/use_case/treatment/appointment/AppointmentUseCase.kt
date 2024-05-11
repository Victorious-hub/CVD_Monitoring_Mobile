package com.example.cvd_monitoring.domain.use_case.treatment.appointment

import com.example.cvd_monitoring.data.dto.AppointmentDto
import com.example.cvd_monitoring.domain.model.treatment.Appointment
import com.example.cvd_monitoring.domain.model.users.PatientContact
import com.example.cvd_monitoring.domain.model.users.UserContact
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import com.example.cvd_monitoring.domain.repository.PatientRepository
import javax.inject.Inject

class AppointmentUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
){
    suspend operator fun invoke(slug: String, patientSlug: String, appointmentDate: String,  appointmentTime: String): AppointmentDto {
        val user = Appointment(patientSlug, appointmentDate, appointmentTime)
        return doctorRepository.createPatientAppointment(slug, user)
    }
}