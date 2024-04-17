package com.example.cvd_monitoring.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.presentation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor (
    private val authPreferences: AuthPreferences
): ViewModel() {

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()
    init {
        checkUserAuth()
    }
    private fun checkUserAuth() {
        viewModelScope.launch {
            val email = authPreferences.getEmail().first()
            if (email != null) {
                Log.i("qwrqwr", email)
            }
            val slug = email?.substringBefore("@")
            if (email != null) {
                _eventFlow.emit(UiEvents.NavigateEvent("${Screen.CurrentUser.route}/$slug/get"))
            }
        }
    }
}
