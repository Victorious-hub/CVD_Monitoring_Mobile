package com.example.cvd_monitoring.domain.use_case.analysis.patient_conclusion

import com.example.cvd_monitoring.data.dto.toBloodAnalysis
import com.example.cvd_monitoring.data.dto.toPatientConclusion
import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.model.analysis.PatientConclusion
import com.example.cvd_monitoring.domain.repository.PatientRepository
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
class PatientConclusionUseCase @Inject constructor(
    private val patientRepository: PatientRepository
) {
    operator fun invoke(slug: String): Flow<Resource<List<PatientConclusion>>> = flow {
        try {
            emit(Resource.Loading())
            val patientConclusion = patientRepository.getPatientConclusionList(slug).map { it.toPatientConclusion() }
            emit(Resource.Success(patientConclusion))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}