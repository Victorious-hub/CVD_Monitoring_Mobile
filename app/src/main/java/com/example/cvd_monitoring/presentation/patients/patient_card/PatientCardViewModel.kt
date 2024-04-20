package com.example.cvd_monitoring.presentation.patients.patient_card

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.use_case.patient.patient_card.PatientCardUseCase
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PatientCardViewModel @Inject constructor(
    private val patientCardUseCase: PatientCardUseCase,
) : ViewModel(){
   
    private val _state = mutableStateOf(PatientCardState())
    val state: State<PatientCardState> = _state
    
    fun getPatientCard(slug: String) {
        patientCardUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PatientCardState(patientCard = result.data)
                }
                is Resource.Error -> {
                    _state.value = PatientCardState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PatientCardState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}