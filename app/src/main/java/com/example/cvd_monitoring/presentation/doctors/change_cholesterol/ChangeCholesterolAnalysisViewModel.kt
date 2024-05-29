package com.example.cvd_monitoring.presentation.doctors.change_cholesterol

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.analysis.update_blood.UpdateBloodAnalysisUseCase
import com.example.cvd_monitoring.domain.use_case.analysis.update_cholesterol.UpdateCholesterolAnalysisUseCase
import com.example.cvd_monitoring.domain.use_case.doctor.check_patient_blood.PatientModelBloodAnalysisUseCase
import com.example.cvd_monitoring.domain.use_case.doctor.check_patient_chol.PatientModelCholesterolAnalysisUseCase
import com.example.cvd_monitoring.presentation.doctors.change_blood.BloodState
import com.example.cvd_monitoring.presentation.doctors.check_cholesterol.CheckCholesterolState
import com.example.cvd_monitoring.presentation.navigation.DoctorBottomBar
import com.example.cvd_monitoring.presentation.navigation.getRouteWithSlug
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
class ChangeCholesterolAnalysisViewModel @Inject constructor(
    private val updateCholesterolAnalysisUseCase: UpdateCholesterolAnalysisUseCase,
    private val cholesterolAnalysisUseCase: PatientModelCholesterolAnalysisUseCase,
    private val authPreferences: AuthPreferences,
) : ViewModel(){
    private val _cholesterolState = mutableStateOf(TextFieldState())
    val cholesterolState: State<TextFieldState> = _cholesterolState

    fun setCholesterolValue(value: String) {
        _cholesterolState.value = cholesterolState.value.copy(text = value)
    }

    private val _hdlCholesterolState = mutableStateOf(TextFieldState())
    val hdlCholesterolState: State<TextFieldState> = _hdlCholesterolState

    fun setHdlCholesterolValue(value: String) {
        _hdlCholesterolState.value = hdlCholesterolState.value.copy(text = value)
    }

    private val _triglyceridesState = mutableStateOf(TextFieldState())
    val triglyceridesState: State<TextFieldState> = _triglyceridesState

    fun setTriglyceridesValue(value: String) {
        _triglyceridesState.value = triglyceridesState.value.copy(text = value)
    }

    private val _ldlCholesterolState = mutableStateOf(TextFieldState())
    val ldlCholesterolState: State<TextFieldState> = _ldlCholesterolState

    fun setLdlCholesterolValue(value: String) {
        _ldlCholesterolState.value = ldlCholesterolState.value.copy(text = value)
    }
    private val _state = mutableStateOf(CheckCholesterolState())
    val state: State<CheckCholesterolState> = _state

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun getCholesterolAnalysis(slug: String) {
        cholesterolAnalysisUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CheckCholesterolState(cholesterolAnalysis = result.data)
                    state.value.cholesterolAnalysis?.let { setCholesterolValue(it.cholesterol.toString()) }
                    state.value.cholesterolAnalysis?.let { setTriglyceridesValue(it.triglycerides.toString()) }
                    state.value.cholesterolAnalysis?.let { setHdlCholesterolValue(it.hdlCholesterol.toString()) }
                    state.value.cholesterolAnalysis?.let { setLdlCholesterolValue(it.ldlCholesterol.toString()) }
                }
                is Resource.Error -> {
                    _state.value = CheckCholesterolState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CheckCholesterolState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateCholesterolAnalysis() {
        val cholesterol = cholesterolState.value.text
        val triglycerides = triglyceridesState.value.text
        val hdlCholesterol = hdlCholesterolState.value.text
        val ldlCholesterol = ldlCholesterolState.value.text

        viewModelScope.launch {
            try {
                val doctorSlug = authPreferences.getUserEmail().firstOrNull()?.substringBefore("@")
                if (doctorSlug != null) {
                    updateCholesterolAnalysisUseCase(
                        doctorSlug,
                        cholesterol.toDouble(),
                        triglycerides.toDouble(),
                        hdlCholesterol.toDouble(),
                        ldlCholesterol.toDouble()
                    )
                }
                doctorSlug?.let {
                    DoctorBottomBar.Profile.getRouteWithSlug(
                        it
                    )
                }?.let { UiEvents.NavigateEvent(it) }?.let { _eventFlow.emit(it) }
            } catch (e: Exception) {
                val errorMessage = e.message.toString()
                Log.e("SignUpViewModel", "Sign up error: $errorMessage", e)
            }
        }
    }
}