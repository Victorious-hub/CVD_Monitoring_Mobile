package com.example.cvd_monitoring.presentation.check_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.current_user.CurrentUserUseCase
import com.example.cvd_monitoring.domain.use_case.doctor_update.DoctorContactUseCase
import com.example.cvd_monitoring.presentation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckUserViewModel @Inject constructor(
    private val authPreferences: AuthPreferences
) : ViewModel() {
    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()
    init {
        checkUserAuth()
    }
    private fun checkUserAuth() {
        viewModelScope.launch {
            val email = authPreferences.getEmail().firstOrNull()
            val doctorEmail = email?.substringAfter("@")?.substringBefore(".")
            val slug = email?.substringBefore("@")
           // _eventFlow.emit(UiEvents.NavigateEvent(Screen.Home.route))
            if (doctorEmail == "cvd") {
                _eventFlow.emit(UiEvents.NavigateEvent("${Screen.CurrentDoctor.route}/$slug/get"))
            } else if (slug != null) {
                _eventFlow.emit(UiEvents.NavigateEvent("${Screen.CurrentUser.route}/$slug/get"))
            } else {
                _eventFlow.emit(UiEvents.NavigateEvent(Screen.Home.route))
            }
        }
    }
}