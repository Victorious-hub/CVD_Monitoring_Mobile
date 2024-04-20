package com.example.cvd_monitoring.domain.use_case.auth.logout

import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.data.remote.response.LogoutResponse
import com.example.cvd_monitoring.domain.repository.AuthRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val preferences: AuthPreferences
) {
    suspend operator fun invoke(): LogoutResponse{
        val getRefreshToken = preferences.getRefreshToken().firstOrNull().toString()
        return authRepository.logoutUser(getRefreshToken)
    }
}
