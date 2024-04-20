package com.example.cvd_monitoring.presentation.patients.patient_profile_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.use_case.patient.current_patient.CurrentUserUseCase
import com.example.cvd_monitoring.utils.CurrentUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientProfileViewModel @Inject constructor(
    private val currentUserUseCase: CurrentUserUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(CurrentUserState())
    val state: State<CurrentUserState> = _state

    fun getCurrentUser(slug: String) {
        viewModelScope.launch {
            try {
                val currentUser = currentUserUseCase(slug)
                _state.value = CurrentUserState(currentUser)
                Log.e("PatientListViewModel", currentUser.toString())
            } catch (e: Exception) {
                val errorMessage = e.message.toString()
                Log.e("PatientListViewModel", errorMessage, e)
            }
        }
    }
}