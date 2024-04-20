package com.example.cvd_monitoring.domain.use_case.treatment

import com.example.cvd_monitoring.data.dto.toPrescription
import com.example.cvd_monitoring.domain.model.treatment.Prescription
import com.example.cvd_monitoring.domain.model.users.Patient
import com.example.cvd_monitoring.domain.repository.PatientRepository
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class PatientPrescriptionUseCase @Inject constructor (
    private val patientRepository: PatientRepository
) {
    operator fun invoke(slug: String): Flow<Resource<List<Prescription>>> = flow {
        try {
            emit(Resource.Loading())
            val prescriptions = patientRepository.getPatientPrescriptions(slug).map { it.toPrescription() }
            emit(Resource.Success(prescriptions))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}