package com.example.cvd_monitoring.data.remote.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.cvd_monitoring.di.dataStore
import com.example.cvd_monitoring.presentation.MainActivity
import com.example.cvd_monitoring.utils.Constants.AUTH_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

//private val Context.dataStore by preferencesDataStore("user_preferences")
//class AuthPreferences(
//    private val dataStore: DataStore<Preferences>
//) {
//   // private val dataStore = context.dataStore
//   companion object {
//       val ACCESS_JWT_KEY = stringPreferencesKey("access_jwt")
//   }
//    suspend fun saveAuthToken(loginToken: String) {
//        dataStore.edit { preferences ->
//            preferences[ACCESS_JWT_KEY] = loginToken
//        }
//    }
//    suspend fun getAuthToken(): String? {
//        return dataStore.data.map { preferences ->
//            preferences[ACCESS_JWT_KEY]
//        }.first()
//    }
//
//}

class AuthPreferences(private val context: Context) {
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
    }

    fun getAuthToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    suspend fun saveAuthToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun deleteToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
}