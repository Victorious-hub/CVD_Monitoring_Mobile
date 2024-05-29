package com.example.cvd_monitoring.presentation.doctors.doctor_blood_create


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.analysis.create_blood.CardBloodAnalysisUseCase
import com.example.cvd_monitoring.presentation.navigation.graphs.AuthScreen
import com.example.cvd_monitoring.presentation.navigation.graphs.DoctorPatientActions
import com.example.cvd_monitoring.presentation.navigation.graphs.getRouteWithSlug
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BloodAnalysisCreateViewModel @Inject constructor(
    private val cardBloodAnalysisUseCase: CardBloodAnalysisUseCase,
    private val authPreferences: AuthPreferences,
) : ViewModel(){


    private val _apHiState = mutableStateOf(TextFieldState())
    val apHiState: State<TextFieldState> = _apHiState

    fun setApHiValue(value: String) {
        _apHiState.value = apHiState.value.copy(text = value)
    }

    private val _apLoState = mutableStateOf(TextFieldState())
    val apLoState: State<TextFieldState> = _apLoState

    fun setApLoValue(value: String) {
        _apLoState.value = apLoState.value.copy(text = value)
    }

    private val _glucoseState = mutableStateOf(TextFieldState())
    val glucoseState: State<TextFieldState> = _glucoseState

    fun setGlucoseValue(value: String) {
        _glucoseState.value = glucoseState.value.copy(text = value)
    }

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

     fun createBloodAnalysis(slug : String) {
        val apHi = apHiState.value.text
        val apLo = apLoState.value.text
        val glucose = glucoseState.value.text

        viewModelScope.launch {
            try {
                authPreferences.getUserEmail().firstOrNull()?.substringBefore("@")
                    ?.let { cardBloodAnalysisUseCase(it, slug, apHi.toInt(), apLo.toInt(), glucose.toInt()) }
                DoctorPatientActions.PatientProfile.getRouteWithSlug(slug)
                    ?.let { UiEvents.NavigateEvent(it) }?.let { _eventFlow.emit(it) }
            } catch (e: Exception) {
                var errorMessage = e.message.toString()
                Log.e("SignUpViewModel", "Sign up error: $errorMessage", e)
            }
        }
    }
}
