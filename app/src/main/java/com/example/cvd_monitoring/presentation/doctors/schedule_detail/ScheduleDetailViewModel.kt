package com.example.cvd_monitoring.presentation.doctors.schedule_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.MapFieldState
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.domain.use_case.doctor.schedule.ScheduleDetailUseCase
import com.example.cvd_monitoring.domain.use_case.patient.appointment.PatientAppointmentUseCase
import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.CurrentPatientState
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
//@HiltViewModel
//class ScheduleDetailViewModel @Inject constructor(
//    private val scheduleDetailUseCase: ScheduleDetailUseCase,
//    private val patientAppointmentUseCase: PatientAppointmentUseCase,
//) : ViewModel() {
//    private val _state = mutableStateOf(ScheduleDetailState())
//    val state: State<ScheduleDetailState> = _state
//
//    private val _firstNameState = mutableStateOf(TextFieldState())
//    val firstNameState: State<TextFieldState> = _firstNameState
//
//    fun setFirstNameValue(value: String) {
//        _firstNameState.value = firstNameState.value.copy(text = value)
//    }
//
//    private val _lastNameState = mutableStateOf(TextFieldState())
//    val lastNameState: State<TextFieldState> = _lastNameState
//
//    fun setLastNameValue(value: String) {
//        _lastNameState.value = lastNameState.value.copy(text = value)
//    }
//
//    private val _emailState = mutableStateOf(TextFieldState())
//    val emailState: State<TextFieldState> = _emailState
//
//    fun setEmailValue(value: String) {
//        _emailState.value = emailState.value.copy(text = value)
//    }
//
//    private val _availableTimeState = mutableStateOf(MapFieldState())
//    val availableTimeState: State<MapFieldState> = _availableTimeState
//
//    fun setAvailableTimeStateValue(value: Map<String, List<String>>) {
//        _availableTimeState.value = availableTimeState.value.copy(text = value)
//    }
//
//    fun getScheduleDetail(slug: String) {
//        scheduleDetailUseCase(slug).onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _state.value = ScheduleDetailState(schedule = result.data)
//                    state.value.schedule?.doctor?.user?.let { setFirstNameValue(it.firstName) }
//                    state.value.schedule?.doctor?.user?.let { setLastNameValue(it.lastName) }
//                    state.value.schedule?.doctor?.user?.let { setEmailValue(it.email) }
//                    state.value.schedule?.let { setAvailableTimeStateValue(it.availableTime) }
//
//                }
//                is Resource.Error -> {
//                    _state.value = ScheduleDetailState(
//                        error = result.message ?: "An unexpected error occured"
//                    )
//                }
//                is Resource.Loading -> {
//                    _state.value = ScheduleDetailState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
//
//    fun createAppointment(patientSlug : String) {
//        val appointmentDate = appointmentDateState.value.text
//        val appointmentTime = appointmentTimeState.value.text
//
//        viewModelScope.launch {
//            try {
//                val createAppointment = authPreferences.getUserEmail().firstOrNull()
//                    ?.let { appointmentUseCase(it.substringBefore("@"), patientSlug, appointmentDate, appointmentTime) }
//                Log.d("SignUpViewModel", "Sign up successful")
//            } catch (e: Exception) {
//                var errorMessage = e.message.toString()
//                Log.e("SignUpViewModel", "Sign up error: $errorMessage", e)
//            }
//        }
//    }
//
//}