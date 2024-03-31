package com.example.cvd_monitoring.presentation.patient_card_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.use_case.current_user.CurrentUserUseCase
import com.example.cvd_monitoring.domain.use_case.patient_card.PatientCardUseCase
import com.example.cvd_monitoring.presentation.CurrentUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientCardViewModel @Inject constructor(
    private val patientCardUseCase: PatientCardUseCase
) : ViewModel() {
    private val _state = mutableStateOf(PatientCardState())
    val state: State<PatientCardState> = _state

    fun getPatientCard(slug: String) {
        viewModelScope.launch {
            try {
                val userCard = patientCardUseCase(slug)
                _state.value = PatientCardState(userCard)
            } catch (e: Exception) {
                val errorMessage = e.message.toString()
                Log.e("PatientCardViewModel", errorMessage, e)
            }
        }
    }
}