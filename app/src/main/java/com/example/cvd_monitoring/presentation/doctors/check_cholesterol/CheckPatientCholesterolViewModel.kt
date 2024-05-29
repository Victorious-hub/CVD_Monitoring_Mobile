package com.example.cvd_monitoring.presentation.doctors.check_cholesterol

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.doctor.check_patient_blood.PatientModelBloodAnalysisUseCase
import com.example.cvd_monitoring.domain.use_case.doctor.check_patient_chol.PatientModelCholesterolAnalysisUseCase
import com.example.cvd_monitoring.presentation.doctors.check_blood.CheckBloodAnalysisState
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class CheckPatientCholesterolViewModel @Inject constructor(
    private val patientModelCholesterolAnalysisUseCase: PatientModelCholesterolAnalysisUseCase,
    private val authPreferences: AuthPreferences
) : ViewModel(){
    private val _state = mutableStateOf(CheckCholesterolState())
    val state: State<CheckCholesterolState> = _state
    fun getPatientCholesterolAnalysis(slug: String) {
        patientModelCholesterolAnalysisUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CheckCholesterolState(cholesterolAnalysis = result.data)
                }
                is Resource.Error -> {
                    _state.value = CheckCholesterolState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CheckCholesterolState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}