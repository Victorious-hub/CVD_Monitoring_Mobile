package com.example.cvd_monitoring.domain.use_case.doctor.schedule

import com.example.cvd_monitoring.data.dto.toNotification
import com.example.cvd_monitoring.data.dto.toSchedule
import com.example.cvd_monitoring.domain.model.analysis.Schedule
import com.example.cvd_monitoring.domain.model.notifications.Notification
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import com.example.cvd_monitoring.domain.repository.PatientRepository
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
class ScheduleUseCase @Inject constructor(
    private val patientRepository: PatientRepository
){
    operator fun invoke(): Flow<Resource<List<Schedule>>> = flow {
        try {
            emit(Resource.Loading())
            val schedule = patientRepository.scheduleList().map { it.toSchedule() }
            emit(Resource.Success(schedule))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}