package com.example.cvd_monitoring.domain.use_case.patient.current_patient

import com.example.cvd_monitoring.data.dto.PatientDto
import com.example.cvd_monitoring.data.dto.toDoctorList
import com.example.cvd_monitoring.data.dto.toPatient
import com.example.cvd_monitoring.domain.model.patients.DoctorList
import com.example.cvd_monitoring.domain.model.users.Patient
import com.example.cvd_monitoring.domain.repository.PatientRepository
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CurrentPatientUseCase @Inject constructor(
    private val patientRepository: PatientRepository
){
    operator fun invoke(slug: String): Flow<Resource<Patient>> = flow {
        try {
            emit(Resource.Loading())
            val patient = patientRepository.getCurrentPatient(slug).toPatient()
            emit(Resource.Success(patient))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}