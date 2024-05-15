package com.example.cvd_monitoring.domain.use_case.patient.appointment

import com.example.cvd_monitoring.data.dto.AppointmentDto
import com.example.cvd_monitoring.domain.repository.PatientRepository
import javax.inject.Inject

class PatientAppointmentUseCase @Inject constructor (
    private val patientRepository: PatientRepository
) {
    suspend operator fun invoke(slug: String, doctorSlug: String, appointmentDate: String, appointmentTime: String): AppointmentDto {
        val appointment = AppointmentDto(doctorSlug, appointmentDate, appointmentTime)
        return patientRepository.createAppointment(slug, appointment)
    }
}