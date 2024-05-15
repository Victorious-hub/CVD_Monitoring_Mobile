package com.example.cvd_monitoring.domain.use_case.doctor.schedule

import com.example.cvd_monitoring.data.dto.toSchedule
import com.example.cvd_monitoring.domain.model.analysis.Schedule
import com.example.cvd_monitoring.domain.repository.PatientRepository
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


class ScheduleDetailUseCase @Inject constructor(
    private val patientRepository: PatientRepository
){
    operator fun invoke(slug: String): Flow<Resource<Schedule>> = flow {
        try {
            emit(Resource.Loading())
            val scheduleDetail = patientRepository.scheduleDetail(slug).toSchedule()
            emit(Resource.Success(scheduleDetail))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}