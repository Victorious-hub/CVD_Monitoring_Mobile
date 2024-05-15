package com.example.cvd_monitoring.presentation.doctors.patient_appointment

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.patient.appointment.PatientAppointmentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PatientAppointmentViewModel @Inject constructor(
    private val appointmentUseCase: PatientAppointmentUseCase,
    private val authPreferences: AuthPreferences
) : ViewModel(){

    private val _doctorSlugState = mutableStateOf(TextFieldState())
    private val doctorSlugState: State<TextFieldState> = _doctorSlugState

    fun setDoctorSlugStateStateValue(value: String) {
        _doctorSlugState.value = doctorSlugState.value.copy(text = value)
    }

    private val _appointmentDateState = mutableStateOf(TextFieldState())
    private val appointmentDateState: State<TextFieldState> = _appointmentDateState

    fun setAppointmentDateStateValue(value: String) {
        _appointmentDateState.value = appointmentDateState.value.copy(text = value)
    }

    private val _appointmentTimeState = mutableStateOf(TextFieldState())
    private val appointmentTimeState: State<TextFieldState> = _appointmentTimeState

    fun setAppointmentTimeStateStateValue(value: String) {
        _appointmentTimeState.value = appointmentTimeState.value.copy(text = value)
    }


    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun createPatientCard(slug : String) {
        val doctorSlug = doctorSlugState.value.text
        val appointmentDate = appointmentDateState.value.text
        val appointmentTime = appointmentTimeState.value.text

        viewModelScope.launch {
            try {
                val createAppointment =
                    authPreferences.getUserEmail().firstOrNull()?.substringBefore("@")
                        ?.let { appointmentUseCase(it,
                            slug,
                            appointmentDate,
                            appointmentTime,
                        ) }
                //_eventFlow.emit(UiEvents.NavigateEvent(AuthScreen.Login.route))
                Log.d("SignUpViewModel", "Sign up successful")
            } catch (e: Exception) {
                var errorMessage = e.message.toString()
                Log.e("SignUpViewModel", "Sign up error: $errorMessage", e)
            }
        }
    }
}