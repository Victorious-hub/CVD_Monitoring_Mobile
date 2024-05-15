package com.example.cvd_monitoring.presentation.doctors.conclusion

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.analysis.conclusion.ConclusionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PatientConclusionViewModel @Inject constructor(
    private val conclusionUseCase: ConclusionUseCase,
    private val authPreferences: AuthPreferences
) : ViewModel(){

    private val _descriptionState = mutableStateOf(TextFieldState())
    val descriptionState: State<TextFieldState> = _descriptionState

    fun setDescriptionStateValue(value: String) {
        _descriptionState.value = descriptionState.value.copy(text = value)
    }

    private val _recommendationsState = mutableStateOf(TextFieldState())
    val recommendationsState: State<TextFieldState> = _recommendationsState

    fun setRecommendationsStateValue(value: String) {
        _recommendationsState.value = recommendationsState.value.copy(text = value)
    }


    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun createPatientConclusion(slug : String) {
        val descriptionState = descriptionState.value.text
        val recommendationsState = recommendationsState.value.text

        viewModelScope.launch {
            try {
                val createdUser =
                    authPreferences.getUserEmail().firstOrNull()?.substringBefore("@")
                        ?.let { conclusionUseCase(it, slug, descriptionState, recommendationsState) }
                //_eventFlow.emit(UiEvents.NavigateEvent(AuthScreen.Login.route))
                Log.d("SignUpViewModel", "Sign up successful")
            } catch (e: Exception) {
                var errorMessage = e.message.toString()
                Log.e("SignUpViewModel", "Sign up error: $errorMessage", e)
            }
        }
    }
}