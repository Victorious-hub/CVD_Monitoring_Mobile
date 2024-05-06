package com.example.cvd_monitoring.presentation.treatment.cholesterol_analysis

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.model.analysis.CholesterolAnalysis
import com.example.cvd_monitoring.domain.use_case.analysis.blood_analysis.PatientBloodAnalysisUseCase
import com.example.cvd_monitoring.domain.use_case.analysis.cholesterol_analysis.PatientCholesterolAnalysisUseCase
import com.example.cvd_monitoring.presentation.treatment.blood_analysis.BloodAnalysisListState
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CholesterolAnalysisViewModel @Inject constructor(
    private val cholesterolAnalysisUseCase: PatientCholesterolAnalysisUseCase,
) : ViewModel(){
    private val _state = mutableStateOf(CholesterolAnalysisListState())
    val state: State<CholesterolAnalysisListState> = _state
    fun getCholesterolAnalysisList(slug: String) {
        cholesterolAnalysisUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CholesterolAnalysisListState(cholesterolAnalysis = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CholesterolAnalysisListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CholesterolAnalysisListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}