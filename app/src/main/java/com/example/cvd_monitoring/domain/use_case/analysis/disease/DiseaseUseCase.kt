package com.example.cvd_monitoring.domain.use_case.analysis.disease

import com.example.cvd_monitoring.data.dto.ConclusionDto
import com.example.cvd_monitoring.data.dto.DiseaseDto
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import javax.inject.Inject
class DiseaseUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {
    suspend operator fun invoke(slug: String, patientSlug: String): DiseaseDto {
        return doctorRepository.createDiagnosis(slug, DiseaseDto(patientSlug))
    }
}