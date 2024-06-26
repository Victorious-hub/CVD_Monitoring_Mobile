package com.example.cvd_monitoring.domain.use_case.patient.patient_contact

import com.example.cvd_monitoring.domain.model.users.PatientContact
import com.example.cvd_monitoring.domain.model.users.PatientData
import com.example.cvd_monitoring.domain.model.users.User
import com.example.cvd_monitoring.domain.model.users.UserContact
import com.example.cvd_monitoring.domain.repository.PatientRepository
import javax.inject.Inject

class PatientContactUseCase @Inject constructor(
    private val patientRepository: PatientRepository
){
    suspend operator fun invoke(mobile: String, firstName: String, lastName: String, address: String, slug: String): PatientContact {
        val user = UserContact(firstName, lastName)
        return patientRepository.updatePatientContact(PatientContact(mobile, address, user), slug)
    }
}