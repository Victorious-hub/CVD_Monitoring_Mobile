package com.example.cvd_monitoring.presentation.treatment.doctor_decline_prescription

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.treatment.PatientPrescriptionUseCase
import com.example.cvd_monitoring.domain.use_case.treatment.decline_prescription.PrescriptionDeclineUseCase
import com.example.cvd_monitoring.presentation.navigation.graphs.DoctorPatientActions
import com.example.cvd_monitoring.presentation.navigation.graphs.getRouteWithSlug
import com.example.cvd_monitoring.presentation.treatment.patient_prescription.PrescriptionListState
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
class PrescriptionDeclineViewModel @Inject constructor(
    private val prescriptionUseCase: PatientPrescriptionUseCase,
    private val prescriptionDeclineUseCase: PrescriptionDeclineUseCase,
    private val authPreferences: AuthPreferences,
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
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PrescriptionListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()
    fun declinePrescription(patientSlug: String, prescriptionId: Int) {
        viewModelScope.launch {
            try {
                authPreferences.getUserEmail().firstOrNull()
                    ?.let { prescriptionDeclineUseCase(it.substringBefore("@"), patientSlug, prescriptionId) }

            } catch (_: Exception) {

            }
        }
    }
}