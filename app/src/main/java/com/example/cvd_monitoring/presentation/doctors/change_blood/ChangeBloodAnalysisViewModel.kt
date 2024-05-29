package com.example.cvd_monitoring.presentation.doctors.change_blood


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.analysis.update_blood.UpdateBloodAnalysisUseCase
import com.example.cvd_monitoring.domain.use_case.doctor.check_patient_blood.PatientModelBloodAnalysisUseCase
import com.example.cvd_monitoring.domain.use_case.patient.current_patient.CurrentPatientUseCase
import com.example.cvd_monitoring.domain.use_case.patient.patient_contact.PatientContactUseCase
import com.example.cvd_monitoring.presentation.navigation.DoctorBottomBar
import com.example.cvd_monitoring.presentation.navigation.PatientBottomBar
import com.example.cvd_monitoring.presentation.navigation.getRouteWithSlug
import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.CurrentPatientState
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
class ChangeBloodAnalysisViewModel @Inject constructor(
    private val updateBloodAnalysisUseCase: UpdateBloodAnalysisUseCase,
    private val bloodAnalysisUseCase: PatientModelBloodAnalysisUseCase,
    private val authPreferences: AuthPreferences,
) : ViewModel(){
    private val _apHiState = mutableStateOf(TextFieldState())
    val apHiState: State<TextFieldState> = _apHiState

    fun setApHiStateValue(value: String) {
        _apHiState.value = apHiState.value.copy(text = value)
    }

    private val _apLoState = mutableStateOf(TextFieldState())
    val apLoState: State<TextFieldState> = _apLoState

    fun setApLoStateValue(value: String) {
        _apLoState.value = apLoState.value.copy(text = value)
    }


    private val _glucoseState = mutableStateOf(TextFieldState())
    val glucoseState: State<TextFieldState> = _glucoseState

    fun setGlucoseStateValue(value: String) {
        _glucoseState.value = glucoseState.value.copy(text = value)
    }

    private val _state = mutableStateOf(BloodState())
    val state: State<BloodState> = _state

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun getBloodAnalysis(slug: String) {
        bloodAnalysisUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = BloodState(bloodAnalysis = result.data)
                    state.value.bloodAnalysis?.let { setApHiStateValue(it.apHi.toString()) }
                    state.value.bloodAnalysis?.let { setApLoStateValue(it.apLo.toString()) }
                    state.value.bloodAnalysis?.let { setGlucoseStateValue(it.glucose.toString()) }
                }
                is Resource.Error -> {
                    _state.value = BloodState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = BloodState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateBloodAnalysis() {
        val apHi = apHiState.value.text
        val apLo = apLoState.value.text
        val glucose = glucoseState.value.text

        viewModelScope.launch {
            try {
                val doctorSlug = authPreferences.getUserEmail().firstOrNull()?.substringBefore("@")
                if (doctorSlug != null) {
                    updateBloodAnalysisUseCase(doctorSlug, apHi.toInt(), apLo.toInt(), glucose.toDouble())
                }
                doctorSlug?.let {
                    DoctorBottomBar.Profile.getRouteWithSlug(
                        it
                    )
                }?.let { UiEvents.NavigateEvent(it) }?.let { _eventFlow.emit(it) }
            } catch (e: Exception) {
                val errorMessage = e.message.toString()
                Log.e("SignUpViewModel", "Sign up error: $errorMessage", e)
            }
        }
    }
}