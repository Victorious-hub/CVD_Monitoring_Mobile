package com.example.cvd_monitoring.domain.use_case.doctor.doctor_patients

import com.example.cvd_monitoring.data.dto.toDoctorPatients
import com.example.cvd_monitoring.data.dto.toNotification
import com.example.cvd_monitoring.domain.model.doctors.DoctorPatients
import com.example.cvd_monitoring.domain.model.notifications.Notification
import com.example.cvd_monitoring.domain.model.users.Doctor
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class DoctorPatientsUseCase  @Inject constructor(
    private val doctorRepository: DoctorRepository
){

    operator fun invoke(slug: String): Flow<Resource<DoctorPatients>> = flow {
        try {
            emit(Resource.Loading())
            val doctorPatients = doctorRepository.getDoctorPatients(slug).toDoctorPatients()
            emit(Resource.Success(doctorPatients))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}