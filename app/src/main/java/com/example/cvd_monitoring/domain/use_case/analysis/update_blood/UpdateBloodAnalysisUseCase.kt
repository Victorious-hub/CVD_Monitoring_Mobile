package com.example.cvd_monitoring.domain.use_case.analysis.update_blood

import com.example.cvd_monitoring.data.dto.BloodAnalysisDto
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import javax.inject.Inject

class UpdateBloodAnalysisUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
){
    suspend operator fun invoke(slug: String, apHi: Int, apLo: Int, glucose: Double): BloodAnalysisDto {
        return doctorRepository.updatePatientBloodAnalysis(slug, BloodAnalysisDto(apHi, apLo, glucose, null))
    }
}