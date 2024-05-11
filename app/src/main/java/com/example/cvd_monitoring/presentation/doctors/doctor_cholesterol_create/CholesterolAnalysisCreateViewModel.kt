package com.example.cvd_monitoring.presentation.doctors.doctor_cholesterol_create


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.model.analysis.CardCholesterolAnalysis
import com.example.cvd_monitoring.domain.use_case.analysis.create_cholesterol.CardCholesterolAnalysisUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CholesterolAnalysisCreateViewModel @Inject constructor(
    private val cardCholesterolAnalysisUseCase: CardCholesterolAnalysisUseCase,
    private val authPreferences: AuthPreferences
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

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun createCholesterolAnalysis(slug : String) {
        val cholesterol = cholesterolState.value.text
        val hdlCholesterol = hdlCholesterolState.value.text
        val ldlCholesterol = ldlCholesterolState.value.text
        val triglycerides = triglyceridesState.value.text

        viewModelScope.launch {
            try {
                val createdUser =
                    authPreferences.getUserEmail().firstOrNull()?.substringBefore("@")
                        ?.let { cardCholesterolAnalysisUseCase(it, slug, cholesterol.toDouble(), hdlCholesterol.toDouble(), ldlCholesterol.toDouble(), triglycerides.toDouble()) }
                //_eventFlow.emit(UiEvents.NavigateEvent(AuthScreen.Login.route))
                Log.d("SignUpViewModel", "Sign up successful")
            } catch (e: Exception) {
                var errorMessage = e.message.toString()
                Log.e("SignUpViewModel", "Sign up error: $errorMessage", e)
            }
        }
    }
}
