package com.example.cvd_monitoring.domain.use_case.patient.doctor_list

import com.example.cvd_monitoring.data.dto.toDoctorList
import com.example.cvd_monitoring.data.dto.toNotification
import com.example.cvd_monitoring.domain.model.notifications.Notification
import com.example.cvd_monitoring.domain.model.patients.DoctorList
import com.example.cvd_monitoring.domain.repository.PatientRepository
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class DoctorListUseCase @Inject constructor(
    private val patientRepository: PatientRepository
){
    operator fun invoke(slug: String): Flow<Resource<List<DoctorList>>> = flow {
        try {
            emit(Resource.Loading())
            val doctorList = patientRepository.getDoctorList(slug).map { it.toDoctorList() }
            emit(Resource.Success(doctorList))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}