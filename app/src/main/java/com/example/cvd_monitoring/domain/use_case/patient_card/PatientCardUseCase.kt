package com.example.cvd_monitoring.domain.use_case.patient_card

import com.example.cvd_monitoring.domain.model.patients.PatientCard
import com.example.cvd_monitoring.domain.repository.PatientRepository
import javax.inject.Inject

class PatientCardUseCase @Inject constructor(
    private val patientRepository: PatientRepository
)  {
    suspend operator fun invoke(slug: String): PatientCard {
        return patientRepository.getPatientCard(slug)
    }
}