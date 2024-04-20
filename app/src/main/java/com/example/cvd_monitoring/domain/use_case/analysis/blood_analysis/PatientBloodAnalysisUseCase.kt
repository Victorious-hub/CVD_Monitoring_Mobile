package com.example.cvd_monitoring.domain.use_case.analysis.blood_analysis

import com.example.cvd_monitoring.data.dto.toBloodAnalysis
import com.example.cvd_monitoring.data.dto.toNotification
import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.model.notifications.Notification
import com.example.cvd_monitoring.domain.repository.PatientRepository
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class PatientBloodAnalysisUseCase @Inject constructor(
    private val patientRepository: PatientRepository
) {
    operator fun invoke(slug: String): Flow<Resource<List<BloodAnalysis>>> = flow {
        try {
            emit(Resource.Loading())
            val bloodAnalysis = patientRepository.getPatientBloodAnalysis(slug).map { it.toBloodAnalysis() }
            emit(Resource.Success(bloodAnalysis))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}