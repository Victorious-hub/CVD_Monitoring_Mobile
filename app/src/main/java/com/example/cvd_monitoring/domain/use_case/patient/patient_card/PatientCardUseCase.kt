package com.example.cvd_monitoring.domain.use_case.patient.patient_card

import com.example.cvd_monitoring.data.dto.toPatientCard
import com.example.cvd_monitoring.domain.model.patients.PatientCard
import com.example.cvd_monitoring.domain.repository.PatientRepository
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class PatientCardUseCase @Inject constructor(
    private val patientRepository: PatientRepository
)  {

    operator fun invoke(slug: String): Flow<Resource<PatientCard>> = flow {
        try {
            emit(Resource.Loading())
            val patientCard = patientRepository.getPatientCard(slug).toPatientCard()
            emit(Resource.Success(patientCard))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}