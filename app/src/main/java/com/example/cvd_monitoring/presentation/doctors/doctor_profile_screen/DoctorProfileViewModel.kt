package com.example.cvd_monitoring.presentation.doctors.doctor_profile_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.use_case.doctor.current_doctor.CurrentDoctorUseCase
import com.example.cvd_monitoring.presentation.patients.patient_card.PatientCardState
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorProfileViewModel @Inject constructor(
    private val currentDoctorUseCase: CurrentDoctorUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(CurrentDoctorState())
    val state: State<CurrentDoctorState> = _state

    fun getCurrentDoctor(slug: String) {
        currentDoctorUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CurrentDoctorState(doctor = result.data)
                }

                is Resource.Error -> {
                    _state.value = CurrentDoctorState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = CurrentDoctorState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}