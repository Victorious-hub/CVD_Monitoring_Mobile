package com.example.cvd_monitoring.presentation.doctors.doctor_appointment

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.model.notifications.DoctorAppointment
import com.example.cvd_monitoring.domain.use_case.analysis.blood_analysis.PatientBloodAnalysisUseCase
import com.example.cvd_monitoring.domain.use_case.doctor.doctor_appointment.DoctorAppointmentsUseCase
import com.example.cvd_monitoring.presentation.treatment.blood_analysis.BloodAnalysisListState
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class DoctorAppointmentViewModel @Inject constructor(
    private val doctorAppointmentsUseCase: DoctorAppointmentsUseCase,
) : ViewModel(){

    private val _state = mutableStateOf(DoctorAppointmentState())
    val state: State<DoctorAppointmentState> = _state

    fun getDoctorAppointmentList(slug: String) {
        doctorAppointmentsUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = DoctorAppointmentState(appointment = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = DoctorAppointmentState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = DoctorAppointmentState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}