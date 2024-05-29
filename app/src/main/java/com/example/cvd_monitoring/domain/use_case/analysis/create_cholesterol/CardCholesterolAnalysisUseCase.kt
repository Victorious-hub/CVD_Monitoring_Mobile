package com.example.cvd_monitoring.domain.use_case.analysis.create_cholesterol

import com.example.cvd_monitoring.data.dto.CardCholesterolAnalysisDto
import com.example.cvd_monitoring.domain.model.analysis.CardCholesterolAnalysis
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import javax.inject.Inject

class CardCholesterolAnalysisUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {
    suspend operator fun invoke(slugDoctor: String, slugPatient: String, cholesterol: Double, hdlCholesterol: Double, ldlCholesterol: Double, triglycerides: Double) : CardCholesterolAnalysisDto
    {
        val cholesterolAnalysis = CardCholesterolAnalysis(slugPatient, cholesterol, hdlCholesterol, ldlCholesterol, triglycerides)
        return doctorRepository.createPatientCholesterolAnalysis(slugDoctor, cholesterolAnalysis)
    }
}