package com.example.cvd_monitoring.domain.use_case.doctor.doctor_appointment

import com.example.cvd_monitoring.data.dto.toDoctorAppointment
import com.example.cvd_monitoring.data.dto.toDoctorPatients
import com.example.cvd_monitoring.data.dto.toSchedule
import com.example.cvd_monitoring.domain.model.doctors.DoctorPatients
import com.example.cvd_monitoring.domain.model.notifications.DoctorAppointment
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class DoctorAppointmentsUseCase  @Inject constructor(
    private val doctorRepository: DoctorRepository
){

    operator fun invoke(slug: String): Flow<Resource<List<DoctorAppointment>>> = flow {
        try {
            emit(Resource.Loading())
            val doctorAppointments = doctorRepository.getDoctorAppointments(slug).map { it.toDoctorAppointment() }
            emit(Resource.Success(doctorAppointments))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}