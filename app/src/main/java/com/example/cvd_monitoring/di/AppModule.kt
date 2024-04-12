package com.example.cvd_monitoring.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.cvd_monitoring.data.remote.AuthApi
import com.example.cvd_monitoring.data.remote.AuthInterceptor
import com.example.cvd_monitoring.data.remote.DoctorApi
import com.example.cvd_monitoring.data.remote.PatientApi
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.data.repository.AuthRepositoryImpl
import com.example.cvd_monitoring.data.repository.DoctorRepositoryImpl
import com.example.cvd_monitoring.data.repository.PatientRepositoryImpl
import com.example.cvd_monitoring.domain.repository.AuthRepository
import com.example.cvd_monitoring.domain.repository.DoctorRepository
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URI)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofitBuilder: Retrofit.Builder): AuthApi {
        return retrofitBuilder.build().create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun providePatientApi(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): PatientApi {
        return retrofitBuilder
            .client(okHttpClient)
            .build().create(PatientApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDoctorApi(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): DoctorApi {
        return retrofitBuilder
            .client(okHttpClient)
            .build().create(DoctorApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(authPreferences: AuthPreferences): AuthInterceptor =
        AuthInterceptor(authPreferences)


    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context): AuthPreferences = AuthPreferences(context)

//    @Provides
//    @Singleton
//    fun provideAuthPreferences(@ApplicationContext context: Context) =
//        AuthPreferences(context)

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, preferences: AuthPreferences): AuthRepository = AuthRepositoryImpl(api, preferences)

    @Provides
    @Singleton
    fun provideDoctorRepository(doctorApi: DoctorApi): DoctorRepository =
        DoctorRepositoryImpl(doctorApi)

    @Provides
    @Singleton
    fun providePatientRepository(patientApi: PatientApi, preferences: AuthPreferences): PatientRepository =
        PatientRepositoryImpl(patientApi, preferences)

}

//https://medium.com/@ratko.kostov21/jwt-authentication-in-android-using-retrofit-and-authenticator-b7b66e231295