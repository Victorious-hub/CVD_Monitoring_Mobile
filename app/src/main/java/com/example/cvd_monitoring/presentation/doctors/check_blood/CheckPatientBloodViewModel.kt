package com.example.cvd_monitoring.presentation.doctors.check_blood

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.analysis.conclusion.ConclusionUseCase
import com.example.cvd_monitoring.domain.use_case.analysis.diagnosis.DiagnosisUseCase
import com.example.cvd_monitoring.domain.use_case.doctor.check_patient_blood.PatientModelBloodAnalysisUseCase
import com.example.cvd_monitoring.presentation.doctors.conclusion.DiagnosisState
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
class CheckPatientBloodViewModel @Inject constructor(
    private val patientModelBloodAnalysisUseCase: PatientModelBloodAnalysisUseCase,
    private val authPreferences: AuthPreferences
) : ViewModel(){
    private val _state = mutableStateOf(CheckBloodAnalysisState())
    val state: State<CheckBloodAnalysisState> = _state
    fun getPatientBloodAnalysis(slug: String) {
        patientModelBloodAnalysisUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CheckBloodAnalysisState(bloodAnalysis = result.data)
                }
                is Resource.Error -> {
                    _state.value = CheckBloodAnalysisState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CheckBloodAnalysisState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
