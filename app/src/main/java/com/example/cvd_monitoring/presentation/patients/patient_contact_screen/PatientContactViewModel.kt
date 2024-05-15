package com.example.cvd_monitoring.presentation.patients.patient_contact_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.domain.use_case.patient.current_patient.CurrentPatientUseCase
import com.example.cvd_monitoring.domain.use_case.patient.patient_contact.PatientContactUseCase
import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.CurrentPatientState
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PatientContactViewModel @Inject constructor(
    private val patientContactUseCase: PatientContactUseCase,
    private val currentPatientUseCase: CurrentPatientUseCase,
) : ViewModel(){
    private val _firstNameState = mutableStateOf(TextFieldState())
    val firstNameState: State<TextFieldState> = _firstNameState

    fun setFirstNameValue(value: String) {
        _firstNameState.value = firstNameState.value.copy(text = value)
    }

    private val _lastNameState = mutableStateOf(TextFieldState())
    val lastNameState: State<TextFieldState> = _lastNameState

    fun setLastNameValue(value: String) {
        _lastNameState.value = lastNameState.value.copy(text = value)
    }


    private val _mobileState = mutableStateOf(TextFieldState())
    val mobileState: State<TextFieldState> = _mobileState

    fun setMobileValue(value: String) {
        _mobileState.value = mobileState.value.copy(text = value)
    }

    private val _adressState = mutableStateOf(TextFieldState())
    val adressState: State<TextFieldState> = _adressState

    fun setAdressValue(value: String) {
        _adressState.value = adressState.value.copy(text = value)
    }

    private val _state = mutableStateOf(CurrentPatientState())
    val state: State<CurrentPatientState> = _state

    fun getCurrentPatient(slug: String) {
        currentPatientUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CurrentPatientState(patient = result.data)
                    state.value.patient?.user?.let { setFirstNameValue(it.firstName) }
                    state.value.patient?.user?.let { setLastNameValue(it.lastName) }
                    state.value.patient?.let { it.mobile?.let { it1 -> setMobileValue(it1) } }
                    state.value.patient?.let { it.address?.let { it1 -> setAdressValue(it1) } }
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

    fun updatePatientContact(slug: String) {
        val firstName = firstNameState.value.text
        val lastName = lastNameState.value.text
        val mobile = mobileState.value.text
        val adress = adressState.value.text

        viewModelScope.launch {
            try {
                val createdUser = patientContactUseCase(mobile, firstName, lastName, adress, slug)
                Log.d("SignUpViewModel", "Sign up successful: $createdUser")
            } catch (e: Exception) {
                val errorMessage = e.message.toString()
                Log.e("SignUpViewModel", "Sign up error: $errorMessage", e)
            }
        }
    }
}