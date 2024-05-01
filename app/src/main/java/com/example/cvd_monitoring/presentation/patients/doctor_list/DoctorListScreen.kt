package com.example.cvd_monitoring.presentation.patients.doctor_list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cvd_monitoring.presentation.Screen

import com.example.cvd_monitoring.presentation.patients.doctor_list.components.DoctorListItem

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DoctorListScreen(
    navController: NavHostController,
    viewModel: DoctorListViewModel = hiltViewModel(),
    slug: String
) {
    LaunchedEffect(key1 = slug) {
        viewModel.getDoctorList(slug)
    }
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.doctorList) { doctor ->
                DoctorListItem(
                    doctor = doctor,
                    onItemClick = {
                        navController.navigate(
                            Screen.CurrentDoctor.route + "/${doctor.user.email.substringBefore("@")}")

                    }
                )

            }
        }
    }
}

