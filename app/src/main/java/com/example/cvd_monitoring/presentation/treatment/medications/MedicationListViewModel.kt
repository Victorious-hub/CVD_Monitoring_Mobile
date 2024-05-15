package com.example.cvd_monitoring.presentation.treatment.medications

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.treatment.medications.MedicationListUseCase
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MedicationListViewModel @Inject constructor(
    private val medicationListUseCase: MedicationListUseCase,
    private val authPreferences: AuthPreferences
) : ViewModel() {
    private val _state = mutableStateOf(MedicationListState())
    val state: State<MedicationListState> = _state

    private val _doctorSlugState = mutableStateOf(TextFieldState())
    val doctorSlugState: State<TextFieldState> = _doctorSlugState

    suspend fun saveMedicationId(medicationId: Int) {
        authPreferences.saveMedication(medicationId)
    }


    fun getMedicationList() {
        medicationListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MedicationListState(medications = result.data ?: emptyList())
                    _doctorSlugState.value.text =
                        authPreferences.getUserEmail().firstOrNull()?.substringBefore("@").toString()
                }
                is Resource.Error -> {
                    _state.value = MedicationListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MedicationListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}