package com.example.cvd_monitoring.presentation.patients.blood_analysis

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.use_case.analysis.blood_analysis.PatientBloodAnalysisUseCase
import com.example.cvd_monitoring.presentation.treatment.patient_prescription.PrescriptionListState
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BloodAnalysisViewModel @Inject constructor(
    private val bloodAnalysisUseCase: PatientBloodAnalysisUseCase,
) : ViewModel(){

    private val _state = mutableStateOf(BloodAnalysisListState())
    val state: State<BloodAnalysisListState> = _state

    fun getBloodAnalysisList(slug: String) {
        bloodAnalysisUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = BloodAnalysisListState(bloodAnalysis = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = BloodAnalysisListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = BloodAnalysisListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}