package com.example.cvd_monitoring.presentation.doctors.schedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.use_case.doctor.schedule.ScheduleUseCase
import com.example.cvd_monitoring.domain.use_case.patient.notifications.NotificationUseCase
import com.example.cvd_monitoring.presentation.notification.NotificationListState
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleUseCase: ScheduleUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(ScheduleState())
    val state: State<ScheduleState> = _state

    fun getScheduleList() {
        scheduleUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ScheduleState(schedule = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ScheduleState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ScheduleState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}