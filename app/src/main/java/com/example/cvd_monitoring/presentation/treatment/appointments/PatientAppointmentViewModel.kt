package com.example.cvd_monitoring.presentation.treatment.appointments

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.use_case.patient.doctor_list.DoctorListUseCase
import com.example.cvd_monitoring.domain.use_case.treatment.patient_appointment.PatientAppointmentUseCase
import com.example.cvd_monitoring.presentation.patients.patient_doctor_list.PatientDoctorListState
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PatientAppointmentViewModel @Inject constructor(
    private val patientAppointmentUseCase: PatientAppointmentUseCase,
) : ViewModel(){

    private val _state = mutableStateOf(PatientAppointmentListState())
    val state: State<PatientAppointmentListState> = _state

    fun getPatientAppointmentList(slug: String) {
        patientAppointmentUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PatientAppointmentListState(patientAppointment = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = PatientAppointmentListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PatientAppointmentListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}