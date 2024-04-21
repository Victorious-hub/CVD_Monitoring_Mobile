package com.example.cvd_monitoring.presentation.doctors.doctor_profile_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.use_case.doctor.current_doctor.CurrentDoctorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorProfileViewModel @Inject constructor(
    private val currentDoctorUseCase: CurrentDoctorUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(CurrentDoctorState())
    val state: State<CurrentDoctorState> = _state

    fun getCurrentDoctor(slug: String) {
        viewModelScope.launch {
            try {
                val currentDoctor = currentDoctorUseCase(slug)
                _state.value = CurrentDoctorState(currentDoctor)
                Log.e("PatientListViewModel", currentDoctor.toString())
            } catch (e: Exception) {
                val errorMessage = e.message.toString()
                Log.e("PatientListViewModel", errorMessage, e)
            }
        }
    }
}