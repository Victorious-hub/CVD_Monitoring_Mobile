package com.example.cvd_monitoring.domain.use_case.doctor.current_doctor

import com.example.cvd_monitoring.data.dto.DoctorListDto
import com.example.cvd_monitoring.data.dto.toDoctorList
import com.example.cvd_monitoring.data.dto.toPatientCard
import com.example.cvd_monitoring.domain.model.patients.DoctorList
import com.example.cvd_monitoring.domain.model.patients.PatientCard
import com.example.cvd_monitoring.domain.model.users.Doctor
import com.example.cvd_monitoring.domain.model.users.DoctorUser
import com.example.cvd_monitoring.domain.repository.DoctorRepository
import com.example.cvd_monitoring.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
class CurrentDoctorUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
){
    operator fun invoke(slug: String): Flow<Resource<DoctorList>> = flow {
        try {
            emit(Resource.Loading())
            val patientCard = doctorRepository.getCurrentDoctor(slug).toDoctorList()
            emit(Resource.Success(patientCard))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}