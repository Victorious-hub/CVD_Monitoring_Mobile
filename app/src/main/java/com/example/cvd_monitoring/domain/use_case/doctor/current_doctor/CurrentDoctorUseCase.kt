package com.example.cvd_monitoring.domain.use_case.doctor.current_doctor

import com.example.cvd_monitoring.domain.model.users.Doctor
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import javax.inject.Inject
class CurrentDoctorUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
){
    suspend operator fun invoke(slug: String): Doctor {
        return doctorRepository.getCurrentDoctor(slug)
    }
}