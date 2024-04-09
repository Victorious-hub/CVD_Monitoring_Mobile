package com.example.cvd_monitoring.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.cvd_monitoring.data.remote.CvdApi
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.data.repository.PatientRepositoryImpl
import com.example.cvd_monitoring.domain.repository.PatientRepository
import com.example.cvd_monitoring.domain.use_case.authenticate.UserAuthenticationUseCase
import com.example.cvd_monitoring.domain.use_case.sign_sup.CreatePatientUseCase
import com.example.cvd_monitoring.utils.Constants
import com.example.cvd_monitoring.utils.Constants.AUTH_PREFERENCES
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCvdAPi(): CvdApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CvdApi::class.java)
    }

    @Provides
    @Singleton
    fun providePreferenceDataStore(@ApplicationContext context: Context) : DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(AUTH_PREFERENCES)
            }
        )

    @Provides
    @Singleton
    fun provideAuthPreferences(dataStore: DataStore<Preferences>) =
        AuthPreferences(dataStore)


    @Provides
    @Singleton
    fun providePatientRepository(api: CvdApi, preferences: AuthPreferences): PatientRepository = PatientRepositoryImpl(api, preferences)


    @Provides
    @Singleton
    fun provideUserAuthenticationUseCase(repository: PatientRepository): UserAuthenticationUseCase {
        return UserAuthenticationUseCase(repository)
    }


    @Provides
    @Singleton
    fun provideCreatePatientUseCase(repository: PatientRepository): CreatePatientUseCase {
        return CreatePatientUseCase(repository)
    }
}