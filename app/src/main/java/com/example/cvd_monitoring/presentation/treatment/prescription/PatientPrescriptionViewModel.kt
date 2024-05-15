package com.example.cvd_monitoring.presentation.treatment.prescription

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.treatment.prescription.CreatePatientPrescriptionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientPrescriptionViewModel @Inject constructor(
    private val createPatientPrescriptionUseCase: CreatePatientPrescriptionUseCase,
    private val authPreferences: AuthPreferences
) : ViewModel(){

    private val _dosageState = mutableStateOf(TextFieldState())
    val dosageState: State<TextFieldState> = _dosageState

    fun setDosageStateValue(value: String) {
        _dosageState.value = dosageState.value.copy(text = value)
    }

    private val _startDateState = mutableStateOf(TextFieldState())
    val startDateState: State<TextFieldState> = _startDateState

    fun setStartDateValue(value: String) {
        _startDateState.value = startDateState.value.copy(text = value)
    }

    private val _endDateState = mutableStateOf(TextFieldState())
    val endDateState: State<TextFieldState> = _endDateState

    fun setEndDateStateValue(value: String) {
        _endDateState.value = endDateState.value.copy(text = value)
    }

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun createPatientPrescription(slug: String) {
        val dosageState = dosageState.value.text
        val startDateState = startDateState.value.text
        val endDateState = endDateState.value.text

        viewModelScope.launch {
            try {
                val createdUser =
                    createPatientPrescriptionUseCase(slug,
                            authPreferences.getPatientSlug().firstOrNull()!!,
                            authPreferences.getMedication().firstOrNull()!!.toInt(),
                            dosageState,
                            startDateState,
                            endDateState
                    )
                //_eventFlow.emit(UiEvents.NavigateEvent(AuthScreen.Login.route))
                Log.d("SignUpViewModel", "Sign up successful")
            } catch (e: Exception) {
                var errorMessage = e.message.toString()
                Log.e("SignUpViewModel", "Sign up error: $errorMessage", e)
            }
        }
    }
}