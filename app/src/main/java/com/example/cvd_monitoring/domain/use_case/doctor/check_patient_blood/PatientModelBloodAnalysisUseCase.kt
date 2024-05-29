package com.example.cvd_monitoring.domain.use_case.doctor.check_patient_blood

import com.example.cvd_monitoring.data.dto.toBloodAnalysis
import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
class PatientModelBloodAnalysisUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {
    operator fun invoke(slug: String): Flow<Resource<BloodAnalysis>> = flow {
        try {
            emit(Resource.Loading())
            val cholesterolAnalysis = doctorRepository.getPatientBloodAnalysis(slug).toBloodAnalysis()
            emit(Resource.Success(cholesterolAnalysis))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}