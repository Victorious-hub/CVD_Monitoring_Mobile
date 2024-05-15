package com.example.cvd_monitoring.presentation.auth.authentication_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.TextFieldState
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.domain.use_case.auth.authentication.UserAuthenticationUseCase
import com.example.cvd_monitoring.utils.AuthState
import com.example.cvd_monitoring.presentation.Screen
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val userAuthenticationUseCase: UserAuthenticationUseCase,
    private val authPreferences: AuthPreferences,
) : ViewModel(){
    private var _loginState  = mutableStateOf(AuthState())
    val loginState: State<AuthState> = _loginState

    private val _emailState = mutableStateOf(TextFieldState())
    val emailState: State<TextFieldState> = _emailState

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun setEmailValue(value: String) {
        _emailState.value = emailState.value.copy(text = value)
    }

    private val _passwordState = mutableStateOf(TextFieldState())
    val passwordState: State<TextFieldState> = _passwordState

    fun setPasswordValue(value: String) {
        _passwordState.value = passwordState.value.copy(text = value)
    }

    fun authenticateUser() {
        viewModelScope.launch {
            _loginState.value = loginState.value.copy(isLoading = false)

            val loginResult = userAuthenticationUseCase(
                email = emailState.value.text,
                password = passwordState.value.text
            )

            if (loginResult.emailError != null){
                _emailState.value=emailState.value.copy(error = loginResult.emailError)
            }
            if (loginResult.passwordError != null){
                _passwordState.value = passwordState.value.copy(error = loginResult.passwordError)
            }
            _loginState.value = loginState.value.copy(isLoading = true)
            val slug = _emailState.value.text.substringBefore("@")

            when (loginResult.result) {

                is Resource.Success -> {
                    val role = authPreferences.getUserRole().firstOrNull()
                    when (role) {
                        "D" -> _eventFlow.emit(UiEvents.NavigateEvent("${Screen.CurrentDoctor.route}/$slug/get"))
                        "P" -> _eventFlow.emit(UiEvents.NavigateEvent("${Screen.CurrentPatient.route}/$slug/get"))
                    }
                }
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvents.SnackbarEvent(
                            loginResult.result.message ?: "Error!"
                        )
                    )
                }
                else -> {
                }
            }
        }
    }

}

