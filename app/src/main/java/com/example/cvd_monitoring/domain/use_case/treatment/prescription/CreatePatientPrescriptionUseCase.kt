package com.example.cvd_monitoring.domain.use_case.treatment.prescription

import com.example.cvd_monitoring.data.dto.PatientPrescriptionDto
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import javax.inject.Inject

class CreatePatientPrescriptionUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
){
    suspend operator fun invoke(slug: String, patientSlug: String, medication: Int, dosage: String, startDate: String, endDate: String): PatientPrescriptionDto {
        val prescription = PatientPrescriptionDto(patientSlug, medication, dosage, startDate, endDate)
        return doctorRepository.createPatientPrescription(slug, prescription)
    }
}