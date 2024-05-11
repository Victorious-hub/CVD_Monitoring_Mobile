package com.example.cvd_monitoring.domain.use_case.analysis.create_blood

import com.example.cvd_monitoring.data.dto.CardBloodAnalysisDto
import com.example.cvd_monitoring.data.dto.UserDto
import com.example.cvd_monitoring.data.remote.request.AuthRequest
import com.example.cvd_monitoring.data.remote.request.CreateUserRequest
import com.example.cvd_monitoring.domain.model.analysis.CardBloodAnalysis
import com.example.cvd_monitoring.domain.model.patients.PatientCard
import com.example.cvd_monitoring.domain.model.users.AuthResult
import com.example.cvd_monitoring.domain.model.users.User
import com.example.cvd_monitoring.domain.repository.AuthRepository
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import javax.inject.Inject
class CardBloodAnalysisUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {

    suspend operator fun invoke(slug: String, slugPatient: String, apHi: Int, apLo: Int, glucose: Int): CardBloodAnalysisDto {
        val bloodAnalysis = CardBloodAnalysis(slugPatient, apLo, apHi, glucose)
        return doctorRepository.createPatientBloodAnalysis(slug, bloodAnalysis)
    }
}