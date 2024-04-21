package com.example.cvd_monitoring.presentation.doctors.doctor_patients

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.use_case.doctor.doctor_patients.DoctorPatientsUseCase
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DoctorPatientsViewModel @Inject constructor(
    private val doctorPatientsUseCase: DoctorPatientsUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(DoctorPatientsListState())
    val state: State<DoctorPatientsListState> = _state

    fun getDoctorPatientList(slug: String) {
        doctorPatientsUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = DoctorPatientsListState(patients = result.data)
                }
                is Resource.Error -> {
                    _state.value = DoctorPatientsListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = DoctorPatientsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}