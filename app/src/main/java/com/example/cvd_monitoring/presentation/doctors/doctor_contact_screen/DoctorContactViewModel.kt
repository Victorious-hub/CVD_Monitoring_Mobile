package com.example.cvd_monitoring.presentation.doctors.doctor_contact_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.domain.use_case.doctor.current_doctor.CurrentDoctorUseCase
import com.example.cvd_monitoring.domain.use_case.doctor.doctor_update.DoctorContactUseCase
import com.example.cvd_monitoring.presentation.doctors.doctor_profile_screen.CurrentDoctorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorContactViewModel @Inject constructor(
    private val doctorContactUseCase: DoctorContactUseCase,
    private val currentDoctorUseCase: CurrentDoctorUseCase
) : ViewModel() {
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

    private val _state = mutableStateOf(CurrentDoctorState())
    val state: State<CurrentDoctorState> = _state

    fun getCurrentUser(slug: String) {
//        viewModelScope.launch {
//            try {
//                val currentUser = currentDoctorUseCase(slug)
//                _state.value = CurrentDoctorState(currentUser)
//                state.value.doctor?.user?.let { setFirstNameValue(it.first_name) }
//                state.value.doctor?.user?.let { setLastNameValue(it.last_name) }
//            } catch (e: Exception) {
//                val errorMessage = e.message.toString()
//                Log.e("PatientListViewModel", errorMessage, e)
//            }
//        }
    }

    fun updateDoctorContact(slug: String) {
        val firstName = firstNameState.value.text
        val lastName = lastNameState.value.text

        viewModelScope.launch {
            try {
                val createdUser = doctorContactUseCase(firstName, lastName, slug)
                Log.d("SignUpViewModel", "Sign up successful: $createdUser")
            } catch (e: Exception) {
                val errorMessage = e.message.toString()
                Log.e("SignUpViewModel", "Sign up error: $errorMessage", e)
            }
        }
    }
}