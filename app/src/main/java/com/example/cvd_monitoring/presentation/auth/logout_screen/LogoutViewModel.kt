package com.example.cvd_monitoring.presentation.auth.logout_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.auth.logout.LogoutUseCase
import com.example.cvd_monitoring.presentation.navigation.graphs.AuthScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LogoutViewModel @Inject constructor(
    private val authPreferences: AuthPreferences,
    private val logoutUseCase: LogoutUseCase
) : ViewModel(){
    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var errorMessage: String by mutableStateOf("")
    fun logoutUser() {
        viewModelScope.launch {
            try {
                authPreferences.deleteToken()
                _eventFlow.emit(UiEvents.NavigateEvent(AuthScreen.Home.route))
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.e("SignUpViewModel", "Sign up error: $errorMessage", e)
            }
        }
    }
}