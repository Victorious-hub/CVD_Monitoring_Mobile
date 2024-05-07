package com.example.cvd_monitoring.presentation.patients.patient_data_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.domain.model.users.PatientData
import com.example.cvd_monitoring.domain.use_case.patient.current_patient.CurrentPatientUseCase
import com.example.cvd_monitoring.domain.use_case.patient.patient_data.PatientDataUseCase
import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.CurrentPatientState
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientUpdateViewModel @Inject constructor(
    private val patientDataUseCase: PatientDataUseCase,
    private val currentPatientUseCase: CurrentPatientUseCase,
) : ViewModel() {
    private val _ageState = mutableStateOf(TextFieldState())
    val ageState: State<TextFieldState> = _ageState

    fun setAgeValue(value: String) {
        _ageState.value = ageState.value.copy(text = value)
    }

    private val _heightState = mutableStateOf(TextFieldState())
    val heightState: State<TextFieldState> = _heightState

    fun setHeightValue(value: String) {
        _heightState.value = heightState.value.copy(text = value)
    }

    private val _weightState = mutableStateOf(TextFieldState())
    val weightState: State<TextFieldState> = _weightState

    fun setWeightValue(value: String) {
        _weightState.value = weightState.value.copy(text = value)
    }

    private val _genderState = mutableStateOf(TextFieldState())
    val genderState: State<TextFieldState> = _genderState

    fun setGenderValue(value: String) {
        _genderState.value = genderState.value.copy(text = value)
    }

    private val _birthdayState = mutableStateOf(TextFieldState())
    val birthdayState: State<TextFieldState> = _birthdayState

    fun setBirthdayValue(value: String) {
        _birthdayState.value = birthdayState.value.copy(text = value)
    }

    private val _state = mutableStateOf(CurrentPatientState())
    val state: State<CurrentPatientState> = _state

    fun getCurrentPatient(slug: String) {
        currentPatientUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CurrentPatientState(patient = result.data)
                    state.value.patient?.let { setAgeValue(it.age.toString()) }
                    state.value.patient?.let { setHeightValue(it.height.toString()) }
                    state.value.patient?.let { setWeightValue(it.weight.toString()) }
                    state.value.patient?.let { setGenderValue(it.gender) }
                    state.value.patient?.let { it.birthday?.let { it1 -> setBirthdayValue(it1) } }
                }
                is Resource.Error -> {
                    _state.value = CurrentPatientState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CurrentPatientState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun updatePatientData(slug: String) {
        val age = ageState.value.text
        val height = heightState.value.text
        val weight = weightState.value.text
        val gender = genderState.value.text
        val birthday = birthdayState.value.text

        viewModelScope.launch {
            try {
                val patientData  = PatientData(age.toInt(), birthday, gender, height.toInt(), weight.toDouble())
                val createdUser = patientDataUseCase(patientData, slug)
                Log.d("SignUpViewModel", "Sign up successful: $createdUser")
            } catch (e: Exception) {
                val errorMessage = e.message.toString()
                Log.e("SignUpViewModel", "Sign up error: $errorMessage", e)
            }
        }
    }
}