package com.example.cvd_monitoring.presentation.patients.patient_profile_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.use_case.patient.current_patient.CurrentPatientUseCase
import com.example.cvd_monitoring.presentation.patients.patient_card.PatientCardState
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientProfileViewModel @Inject constructor(
    private val currentPatientUseCase: CurrentPatientUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(CurrentPatientState())
    val state: State<CurrentPatientState> = _state

    fun getCurrentPatient(slug: String) {
        currentPatientUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CurrentPatientState(patient = result.data)
                }
                is Resource.Error -> {
                    _state.value = CurrentPatientState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CurrentPatientState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}