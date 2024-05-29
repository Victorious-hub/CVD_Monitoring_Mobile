package com.example.cvd_monitoring.presentation.doctors.card_update

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.analysis.update_card.UpdateCardUseCase
import com.example.cvd_monitoring.domain.use_case.patient.patient_card.PatientCardUseCase
import com.example.cvd_monitoring.presentation.navigation.graphs.DoctorPatientActions
import com.example.cvd_monitoring.presentation.navigation.graphs.getRouteWithSlug
import com.example.cvd_monitoring.presentation.patients.patient_card.PatientCardState
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
class UpdateCardViewModel @Inject constructor(
    private val updateCardUseCase: UpdateCardUseCase,
    private val authPreferences: AuthPreferences,
    private val patientCardUseCase: PatientCardUseCase,
) : ViewModel(){

    private val _state = mutableStateOf(PatientCardState())
    val state: State<PatientCardState> = _state

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _bloodTypeState = mutableStateOf(TextFieldState())
    val bloodTypeState: State<TextFieldState> = _bloodTypeState

    fun setBloodTypeStateValue(value: String) {
        _bloodTypeState.value = bloodTypeState.value.copy(text = value)
    }

    private val _abnormalConditionsState = mutableStateOf(TextFieldState())
    val abnormalConditionsState: State<TextFieldState> = _abnormalConditionsState

    fun setAbnormalConditionsStateValue(value: String) {
        _abnormalConditionsState.value = abnormalConditionsState.value.copy(text = value)
    }

    private val _smokeState = mutableStateOf(TextFieldState())
    val smokeState: State<TextFieldState> = _smokeState

    fun setSmokeStateValue(value: String) {
        _smokeState.value = smokeState.value.copy(text = value)
    }

    private val _alcoholState = mutableStateOf(TextFieldState())
    val alcoholState: State<TextFieldState> = _alcoholState

    fun setAlcoholValue(value: String) {
        _alcoholState.value = alcoholState.value.copy(text = value)
    }

    private val _activeState = mutableStateOf(TextFieldState())
    val activeState: State<TextFieldState> = _activeState

    fun setActiveStateValue(value: String) {
        _activeState.value = activeState.value.copy(text = value)
    }

    private val _weightState = mutableStateOf(TextFieldState())
    val weightState: State<TextFieldState> = _weightState

    fun setWeightStateValue(value: String) {
        _weightState.value = weightState.value.copy(text = value)
    }

    private val _heightState = mutableStateOf(TextFieldState())
    val heightState: State<TextFieldState> = _heightState

    fun setHeightStateValue(value: String) {
        _heightState.value = heightState.value.copy(text = value)
    }

    private val _genderState = mutableStateOf(TextFieldState())
    val genderState: State<TextFieldState> = _genderState

    fun setGenderStateStateValue(value: String) {
        _genderState.value = genderState.value.copy(text = value)
    }

    private val _birthdayState = mutableStateOf(TextFieldState())
    val birthdayState: State<TextFieldState> = _birthdayState

    fun setBirthdayStateStateValue(value: String) {
        _birthdayState.value = birthdayState.value.copy(text = value)
    }

    fun getPatientCard(slug: String) {
        patientCardUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PatientCardState(patientCard = result.data)
                    state.value.patientCard?.birthday?.let { setBirthdayStateStateValue(it) }
                    state.value.patientCard?.height?.let{ setHeightStateValue(it.toString()) }
                    state.value.patientCard?.weight?.let{ setWeightStateValue(it.toString()) }
                    state.value.patientCard?.smoke?.let{ setSmokeStateValue(it.toString()) }
                    state.value.patientCard?.active?.let{ setActiveStateValue(it.toString()) }
                    state.value.patientCard?.alcohol?.let{ setAlcoholValue(it.toString()) }
                    state.value.patientCard?.abnormalConditions?.let{ setAbnormalConditionsStateValue(it) }
                    state.value.patientCard?.bloodType?.let{ setBloodTypeStateValue(it) }
                    state.value.patientCard?.gender?.let{ setGenderStateStateValue(it) }
                }
                is Resource.Error -> {
                    _state.value = PatientCardState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PatientCardState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updatePatientCard(patientSlug : String) {
        val bloodType = bloodTypeState.value.text
        val abnormalConditions = abnormalConditionsState.value.text
        val smoke = smokeState.value.text
        val alcohol = alcoholState.value.text
        val active = activeState.value.text
        val weight = weightState.value.text
        val height = heightState.value.text
        val gender = genderState.value.text
        val birthday = birthdayState.value.text


        viewModelScope.launch {
            try {
                authPreferences.getUserEmail().firstOrNull()?.substringBefore("@")
                    ?.let { updateCardUseCase(it,
                        patientSlug,
                        bloodType,
                        abnormalConditions,
                        smoke.toBoolean(),
                        alcohol.toBoolean(),
                        active.toBoolean(),
                        weight.toDouble(),
                        height.toInt(),
                        gender,
                        birthday
                    ) }
                DoctorPatientActions.PatientProfile.getRouteWithSlug(patientSlug)
                    ?.let { UiEvents.NavigateEvent(it) }?.let { _eventFlow.emit(it) }
            } catch (e: Exception) {
                var errorMessage = e.message.toString()
                Log.e("Card Creation error: $errorMessage", e.toString())
            }
        }
    }
}