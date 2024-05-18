package com.example.cvd_monitoring.presentation.patients.patient_conclusion

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.use_case.analysis.blood_analysis.PatientBloodAnalysisUseCase
import com.example.cvd_monitoring.domain.use_case.analysis.patient_conclusion.PatientConclusionUseCase
import com.example.cvd_monitoring.presentation.treatment.blood_analysis.BloodAnalysisListState
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class PatientConclusionViewModel @Inject constructor(
    private val patientConclusionUseCase: PatientConclusionUseCase,
) : ViewModel(){

    private val _state = mutableStateOf(PatientConclusionListState())
    val state: State<PatientConclusionListState> = _state

    fun getPatientConclusionList(slug: String) {
        patientConclusionUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PatientConclusionListState(patientConclusion = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = PatientConclusionListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PatientConclusionListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}