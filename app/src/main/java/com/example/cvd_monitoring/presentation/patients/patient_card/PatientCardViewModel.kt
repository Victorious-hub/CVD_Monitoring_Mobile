package com.example.cvd_monitoring.presentation.patients.patient_card

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.use_case.patient_card.PatientCardUseCase
import com.example.cvd_monitoring.utils.PatientCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientCardViewModel @Inject constructor(
    private val patientCardUseCase: PatientCardUseCase,
) : ViewModel(){
    private val _state = mutableStateOf(PatientCardState())
    val state: State<PatientCardState> = _state

    fun getPatientCard(slug: String) {
        viewModelScope.launch {
            try {
                val patientCard = patientCardUseCase(slug)
                _state.value = PatientCardState(patientCard)
                Log.e("PatientCardViewModel", patientCard.toString())
            } catch (e: Exception) {
                val errorMessage = e.message.toString()
                Log.e("PatientCardViewModel", errorMessage, e)
            }
        }
    }
}