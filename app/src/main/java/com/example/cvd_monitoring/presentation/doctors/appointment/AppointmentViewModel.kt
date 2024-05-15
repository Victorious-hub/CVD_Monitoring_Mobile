package com.example.cvd_monitoring.presentation.doctors.appointment

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.treatment.appointment.AppointmentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentViewModel @Inject constructor(
    private val appointmentUseCase: AppointmentUseCase,
    private val authPreferences: AuthPreferences
) : ViewModel(){

    private val _appointmentDateState = mutableStateOf(TextFieldState())
    val appointmentDateState: State<TextFieldState> = _appointmentDateState

    fun setAppointmentDateValue(value: String) {
        _appointmentDateState.value = appointmentDateState.value.copy(text = value)
    }

    private val _appointmentTimeState = mutableStateOf(TextFieldState())
    val appointmentTimeState: State<TextFieldState> = _appointmentTimeState

    fun setAppointmentTimeValue(value: String) {
        _appointmentTimeState.value = appointmentTimeState.value.copy(text = value)
    }

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun createAppointment(patientSlug : String) {
        val appointmentDate = appointmentDateState.value.text
        val appointmentTime = appointmentTimeState.value.text

        viewModelScope.launch {
            try {
                val createAppointment = authPreferences.getUserEmail().firstOrNull()
                    ?.let { appointmentUseCase(it.substringBefore("@"), patientSlug, appointmentDate, appointmentTime) }
                Log.d("SignUpViewModel", "Sign up successful")
            } catch (e: Exception) {
                var errorMessage = e.message.toString()
                Log.e("SignUpViewModel", "Sign up error: $errorMessage", e)
            }
        }
    }
}