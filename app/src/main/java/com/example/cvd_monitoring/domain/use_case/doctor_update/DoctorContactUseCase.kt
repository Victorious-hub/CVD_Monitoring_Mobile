package com.example.cvd_monitoring.domain.use_case.doctor_update

import com.example.cvd_monitoring.domain.model.users.DoctorContact
import com.example.cvd_monitoring.domain.model.users.UserContact
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import com.example.cvd_monitoring.utils.Resource
import javax.inject.Inject

class DoctorContactUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {
    suspend operator fun invoke(firstName: String, lastName: String, email: String, slug: String): DoctorContact {
        val user = UserContact(firstName, lastName, email)
        return doctorRepository.updateDoctorContact(DoctorContact(user), slug)
    }
}


