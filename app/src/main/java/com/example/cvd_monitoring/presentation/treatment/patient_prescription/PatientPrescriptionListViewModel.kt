package com.example.cvd_monitoring.presentation.treatment.patient_prescription

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.use_case.treatment.PatientPrescriptionUseCase
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PatientPrescriptionListViewModel @Inject constructor(
    private val prescriptionUseCase: PatientPrescriptionUseCase
) : ViewModel() {

    private val _state = mutableStateOf(PrescriptionListState())
    val state: State<PrescriptionListState> = _state

    fun getPrescriptionList(slug: String) {
        prescriptionUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PrescriptionListState(prescriptions = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = PrescriptionListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PrescriptionListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}