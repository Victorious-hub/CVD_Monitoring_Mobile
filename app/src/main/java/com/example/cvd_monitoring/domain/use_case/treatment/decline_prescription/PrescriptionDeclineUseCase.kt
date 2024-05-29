package com.example.cvd_monitoring.domain.use_case.treatment.decline_prescription

import com.example.cvd_monitoring.data.dto.PrescriptionDeclineDto
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import javax.inject.Inject
class PrescriptionDeclineUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
){
    suspend operator fun invoke(slug: String, patientSlug: String, prescriptionId: Int): PrescriptionDeclineDto {
        return doctorRepository.declinePatientPrescription(slug, PrescriptionDeclineDto(patientSlug, prescriptionId))
    }
}