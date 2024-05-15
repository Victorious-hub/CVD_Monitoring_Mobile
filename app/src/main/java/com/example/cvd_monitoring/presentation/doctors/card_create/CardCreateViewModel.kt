package com.example.cvd_monitoring.presentation.doctors.card_create

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.analysis.create_card.PatientCardCreateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardCreateViewModel @Inject constructor(
    private val cardCreateUseCase: PatientCardCreateUseCase,
    private val authPreferences: AuthPreferences
) : ViewModel(){

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

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun createPatientCard(slug : String) {
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
                val createdCard =
                    authPreferences.getUserEmail().firstOrNull()?.substringBefore("@")
                        ?.let { cardCreateUseCase(it,
                            slug,
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
                //_eventFlow.emit(UiEvents.NavigateEvent(AuthScreen.Login.route))
                Log.d("SignUpViewModel", "Sign up successful")
            } catch (e: Exception) {
                var errorMessage = e.message.toString()
                Log.e("SignUpViewModel", "Sign up error: $errorMessage", e)
            }
        }
    }
}
