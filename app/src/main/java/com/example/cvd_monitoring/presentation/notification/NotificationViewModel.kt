package com.example.cvd_monitoring.presentation.notification

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.model.notifications.Notification
import com.example.cvd_monitoring.domain.model.users.Patient
import com.example.cvd_monitoring.domain.use_case.patient.current_patient.CurrentUserUseCase
import com.example.cvd_monitoring.domain.use_case.patient.notifications.NotificationUseCase
import com.example.cvd_monitoring.presentation.treatment.patient_prescription.PrescriptionListState
import com.example.cvd_monitoring.utils.CurrentUserState
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationUseCase: NotificationUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(NotificationListState())
    val state: State<NotificationListState> = _state

    fun getNotificationList(slug: String) {
        notificationUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = NotificationListState(notifications = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = NotificationListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = NotificationListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}