package com.example.cvd_monitoring.domain.use_case.treatment.patient_appointment

import com.example.cvd_monitoring.data.dto.toBloodAnalysis
import com.example.cvd_monitoring.data.dto.toPatientAppointment
import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.model.treatment.PatientAppointment
import com.example.cvd_monitoring.domain.repository.PatientRepository
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
class PatientAppointmentUseCase @Inject constructor(
    private val patientRepository: PatientRepository
) {
    operator fun invoke(slug: String): Flow<Resource<List<PatientAppointment>>> = flow {
        try {
            emit(Resource.Loading())
            val patientAppointment = patientRepository.getPatientAppointments(slug).map { it.toPatientAppointment() }
            emit(Resource.Success(patientAppointment))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}