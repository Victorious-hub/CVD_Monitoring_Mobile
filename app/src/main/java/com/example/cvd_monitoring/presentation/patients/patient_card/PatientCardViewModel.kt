package com.example.cvd_monitoring.presentation.patients.patient_card

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.analysis.disease.DiseaseUseCase
import com.example.cvd_monitoring.domain.use_case.patient.patient_card.PatientCardUseCase
import com.example.cvd_monitoring.presentation.navigation.graphs.DoctorPatientActions
import com.example.cvd_monitoring.presentation.navigation.graphs.getRouteWithSlug
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientCardViewModel @Inject constructor(
    private val patientCardUseCase: PatientCardUseCase,
    private val diseaseUseCase: DiseaseUseCase,
    private val authPreferences: AuthPreferences
) : ViewModel(){
   
    private val _state = mutableStateOf(PatientCardState())
    val state: State<PatientCardState> = _state

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun createDisease(patientSlug : String) {
        viewModelScope.launch {
            try {
                authPreferences.getUserEmail().firstOrNull()?.substringBefore("@")
                    ?.let { diseaseUseCase(it, patientSlug,
                    ) }
                DoctorPatientActions.PatientProfile.getRouteWithSlug(patientSlug)
                    ?.let { UiEvents.NavigateEvent(it) }?.let { _eventFlow.emit(it) }
            } catch (e: Exception) {
                var errorMessage = e.message.toString()
                Log.e("Card Creation error: $errorMessage", e.toString())
            }
        }
    }


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