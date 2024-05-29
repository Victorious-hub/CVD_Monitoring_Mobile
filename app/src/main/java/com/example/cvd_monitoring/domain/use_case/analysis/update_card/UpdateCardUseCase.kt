package com.example.cvd_monitoring.domain.use_case.analysis.update_card

import com.example.cvd_monitoring.data.dto.CreateCardRequestDto
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import javax.inject.Inject
class UpdateCardUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {
    suspend operator fun invoke(
        slugDoctor: String,
        slugPatient: String,
        bloodType: String,
        abnormalConditions: String,
        smoke: Boolean,
        alcohol: Boolean,
        active: Boolean,
        weight: Double,
        height: Int,
        gender: String,
        birthday: String
    ) : CreateCardRequestDto
    {
        val patientCard = CreateCardRequestDto(
            slugPatient,
            abnormalConditions,
            bloodType,
            smoke,
            alcohol,
            active,
            weight,
            height,
            gender,
            birthday
        )
        return doctorRepository.updatePatientCard(slugDoctor, patientCard)
    }
}