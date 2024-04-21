package com.example.cvd_monitoring.presentation.notification


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.use_case.patient.notifications.NotificationUseCase
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = NotificationListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}