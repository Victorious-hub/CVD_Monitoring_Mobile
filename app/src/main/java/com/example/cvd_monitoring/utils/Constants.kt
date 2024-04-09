package com.example.cvd_monitoring.utils

import androidx.datastore.preferences.core.stringSetPreferencesKey

object Constants {
    const val BASE_URI = "http://127.0.0.1:8000/api/"
    const val TAG = "OnResponse"
    const val PARAM_SLUG = "slug"
    const val PREFS_TOKEN_FILE = "PREFS_TOKEN_FILE"
    const val USER_TOKEN = "USER_TOKEN"
    const val AUTH_PREFERENCES = "AUTH_PREF"
    val AUTH_KEY = stringSetPreferencesKey("auth_key")
}