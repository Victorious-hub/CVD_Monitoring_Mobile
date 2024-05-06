package com.example.cvd_monitoring.presentation.patients.patient_doctor_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.domain.use_case.patient.doctor_list.DoctorListUseCase
import com.example.cvd_monitoring.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class PatientDoctorListViewModel @Inject constructor(
    private val doctorListUseCase: DoctorListUseCase,
) : ViewModel(){

    private val _state = mutableStateOf(PatientDoctorListState())
    val state: State<PatientDoctorListState> = _state

    fun getDoctorList(slug: String) {
        doctorListUseCase(slug).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PatientDoctorListState(doctorList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = PatientDoctorListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PatientDoctorListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}