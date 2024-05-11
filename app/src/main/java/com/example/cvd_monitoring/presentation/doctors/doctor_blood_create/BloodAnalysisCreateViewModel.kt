package com.example.cvd_monitoring.presentation.doctors.doctor_blood_create

import com.example.cvd_monitoring.presentation.auth.register_screen.PasswordValidationState
import com.example.cvd_monitoring.presentation.auth.register_screen.ValidatePassword


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.model.patients.PatientCard
import com.example.cvd_monitoring.domain.use_case.analysis.create_blood.CardBloodAnalysisUseCase
import com.example.cvd_monitoring.domain.use_case.auth.registration.CreatePatientUseCase
import com.example.cvd_monitoring.presentation.Screen
import com.example.cvd_monitoring.presentation.navigation.graphs.AuthScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BloodAnalysisCreateViewModel @Inject constructor(
    private val cardBloodAnalysisUseCase: CardBloodAnalysisUseCase,
    private val authPreferences: AuthPreferences
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

    private val _passwordState = mutableStateOf(TextFieldState())
    val passwordState: State<TextFieldState> = _passwordState

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

     fun createBloodAnalysis(slug : String) {
        val apHi = apHiState.value.text
        val apLo = apLoState.value.text
        val glucose = glucoseState.value.text

        viewModelScope.launch {
            try {
                val createdUser =
                    authPreferences.getUserEmail().firstOrNull()?.substringBefore("@")
                        ?.let { cardBloodAnalysisUseCase(it, slug, apHi.toInt(), apLo.toInt(), glucose.toInt()) }
                //_eventFlow.emit(UiEvents.NavigateEvent(AuthScreen.Login.route))
                Log.d("SignUpViewModel", "Sign up successful")
            } catch (e: Exception) {
                var errorMessage = e.message.toString()
                Log.e("SignUpViewModel", "Sign up error: $errorMessage", e)
            }
        }
    }
}
