package com.example.cvd_monitoring.domain.use_case.analysis.diagnosis

import com.example.cvd_monitoring.data.dto.toCholesterolAnalysis
import com.example.cvd_monitoring.data.dto.toDiagnosis
import com.example.cvd_monitoring.domain.model.analysis.CholesterolAnalysis
import com.example.cvd_monitoring.domain.model.analysis.Diagnosis
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import com.example.cvd_monitoring.domain.repository.PatientRepository
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class DiagnosisUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {
    operator fun invoke(slug: String): Flow<Resource<Diagnosis>> = flow {
        try {
            emit(Resource.Loading())
            val cholesterolAnalysis = doctorRepository.getPatientDiagnosis(slug).toDiagnosis()
            emit(Resource.Success(cholesterolAnalysis))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}