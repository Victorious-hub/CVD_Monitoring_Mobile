package com.example.cvd_monitoring.domain.use_case.treatment.medications

import com.example.cvd_monitoring.data.dto.MedicationDto
import com.example.cvd_monitoring.data.dto.toBloodAnalysis
import com.example.cvd_monitoring.data.dto.toDoctorPatients
import com.example.cvd_monitoring.data.dto.toMedication
import com.example.cvd_monitoring.domain.model.doctors.DoctorPatients
import com.example.cvd_monitoring.domain.model.treatment.Medication
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class MedicationListUseCase  @Inject constructor(
    private val doctorRepository: DoctorRepository
){
    operator fun invoke(): Flow<Resource<List<Medication>>> = flow {
        try {
            emit(Resource.Loading())
            val medicationList = doctorRepository.getMedicationList().map { it.toMedication() }
            emit(Resource.Success(medicationList))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}