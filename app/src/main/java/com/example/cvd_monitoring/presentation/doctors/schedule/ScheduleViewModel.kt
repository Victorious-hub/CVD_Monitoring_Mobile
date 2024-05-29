package com.example.cvd_monitoring.presentation.doctors.schedule

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.doctor.schedule.ScheduleUseCase
import com.example.cvd_monitoring.domain.use_case.patient.appointment.PatientAppointmentUseCase
import com.example.cvd_monitoring.domain.use_case.patient.notifications.NotificationUseCase
import com.example.cvd_monitoring.presentation.navigation.DoctorBottomBar
import com.example.cvd_monitoring.presentation.navigation.PatientBottomBar
import com.example.cvd_monitoring.presentation.navigation.getRouteWithSlug
import com.example.cvd_monitoring.presentation.notification.NotificationListState
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
class ScheduleViewModel @Inject constructor(
    private val scheduleUseCase: ScheduleUseCase,
    private val patientAppointmentUseCase: PatientAppointmentUseCase,
    private val authPreferences: AuthPreferences,
) : ViewModel() {
    private val _state = mutableStateOf(ScheduleState())
    val state: State<ScheduleState> = _state

    private val _appointmentDateState = mutableStateOf(TextFieldState())
    val appointmentDateState: State<TextFieldState> = _appointmentDateState

    private val _appointmentTimeState = mutableStateOf(TextFieldState())
    val appointmentTimeState: State<TextFieldState> = _appointmentTimeState

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _patientSlugState = mutableStateOf(TextFieldState())
    val patientSlugState: State<TextFieldState> = _patientSlugState

    fun createAppointment(doctorSlug : String) {
        val appointmentDate = appointmentDateState.value.text
        val appointmentTime = appointmentTimeState.value.text

        viewModelScope.launch {
            try {
                authPreferences.getUserEmail().firstOrNull()
                    ?.let { patientAppointmentUseCase(it.substringBefore("@"), doctorSlug, appointmentDate, appointmentTime) }
            } catch (e: Exception) {
                var errorMessage = e.message.toString()
                Log.e("SignUpViewModel", "Sign up error: $errorMessage", e)
            }
        }
    }
    fun getScheduleList() {
        scheduleUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ScheduleState(schedule = result.data ?: emptyList())
                    _patientSlugState.value.text =
                        authPreferences.getUserEmail().firstOrNull()?.substringBefore("@").toString()
                }
                is Resource.Error -> {
                    _state.value = ScheduleState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ScheduleState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}