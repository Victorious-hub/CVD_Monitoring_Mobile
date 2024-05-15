package com.example.cvd_monitoring.data.remote.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.cvd_monitoring.di.dataStore
import com.example.cvd_monitoring.domain.model.treatment.Medication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthPreferences(private val context: Context) {
    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        private val USER_EMAIL = stringPreferencesKey("email")
        private val USER_ROLE = stringPreferencesKey("role")
        private val PATIENT_SLUG = stringPreferencesKey("patient_slug")
        private val MEDICATION = stringPreferencesKey("medication")
    }

    fun getAuthToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN]
        }
    }

    fun getRefreshToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN]
        }
    }

    fun getUserRole(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[USER_ROLE]
        }
    }

    fun getUserEmail(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[USER_EMAIL]
        }
    }

    suspend fun saveAuthToken(accessToken: String, email: String, role: String, refreshToken: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL] = email
            preferences[USER_ROLE] = role
            preferences[ACCESS_TOKEN] = accessToken
            preferences[REFRESH_TOKEN] = refreshToken
        }
    }

    suspend fun savePatientSlug(patientSlug: String) {
        context.dataStore.edit { preferences ->
            preferences[PATIENT_SLUG] = patientSlug
        }
    }


    suspend fun saveMedication(medication: Int) {
        context.dataStore.edit { preferences ->
            preferences[MEDICATION] = medication.toString()
        }
    }

    fun getPatientSlug(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[PATIENT_SLUG]
        }
    }
    fun getMedication(): Flow<Int?> {
        return context.dataStore.data.map { preferences ->
            preferences[MEDICATION]?.toInt()
        }
    }

    suspend fun deleteToken() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}