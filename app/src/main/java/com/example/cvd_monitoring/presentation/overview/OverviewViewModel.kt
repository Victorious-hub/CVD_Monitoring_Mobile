package com.example.cvd_monitoring.presentation.overview

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.presentation.patients.patient_card.PatientCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val authPreferences: AuthPreferences,
) : ViewModel(){

    private val _state = mutableStateOf(PatientCardState())
    val state: State<PatientCardState> = _state

    private val _slugState = mutableStateOf(TextFieldState())
    val slugState: State<TextFieldState> = _slugState

    var errorMessage: String by mutableStateOf("")
    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getPatientSlug()
    }

    fun getPatientSlug() {
        viewModelScope.launch {
            val patientSlug = authPreferences.getUserEmail().firstOrNull()?.substringBefore("@")
            try {
                if (patientSlug != null) {
                    slugState.value.text = patientSlug
                }
                Log.d("SignUpViewModel", "Sign up successful: $patientSlug")
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.e("SignUpViewModel", "Sign up error: $errorMessage", e)
            }
        }

    }

}