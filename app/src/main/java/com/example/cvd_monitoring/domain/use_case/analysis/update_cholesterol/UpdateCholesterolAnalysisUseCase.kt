package com.example.cvd_monitoring.domain.use_case.analysis.update_cholesterol

import com.example.cvd_monitoring.data.dto.CholesterolAnalysisDto
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import javax.inject.Inject

class UpdateCholesterolAnalysisUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
){
    suspend operator fun invoke(slug: String, cholesterol: Double, hdlCholesterol: Double, ldlCholesterol: Double, triglycerides: Double): CholesterolAnalysisDto {
        return doctorRepository.updatePatientCholesterolAnalysis(slug, CholesterolAnalysisDto(cholesterol, hdlCholesterol, ldlCholesterol, triglycerides, null))
    }
}