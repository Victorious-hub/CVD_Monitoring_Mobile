package com.example.cvd_monitoring.domain.use_case.patient.notifications

import com.example.cvd_monitoring.data.dto.toNotification
import com.example.cvd_monitoring.data.dto.toPrescription
import com.example.cvd_monitoring.domain.model.notifications.Notification
import com.example.cvd_monitoring.domain.model.treatment.Prescription
import com.example.cvd_monitoring.domain.repository.PatientRepository
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
class NotificationUseCase @Inject constructor(
    private val patientRepository: PatientRepository
){
    operator fun invoke(slug: String): Flow<Resource<List<Notification>>> = flow {
        try {
            emit(Resource.Loading())
            val notifications = patientRepository.getNotificationList(slug).map { it.toNotification() }
            emit(Resource.Success(notifications))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}