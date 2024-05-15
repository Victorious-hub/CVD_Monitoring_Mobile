package com.example.cvd_monitoring.domain.use_case.analysis.conclusion

import com.example.cvd_monitoring.data.dto.ConclusionDto
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import javax.inject.Inject

class ConclusionUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {
    suspend operator fun invoke(slug: String, patientSlug: String, description: String, recommendations: String): ConclusionDto {
        val conclusion = ConclusionDto(patientSlug, description, recommendations)
        return doctorRepository.createPatientConclusion(slug, conclusion)
    }
}