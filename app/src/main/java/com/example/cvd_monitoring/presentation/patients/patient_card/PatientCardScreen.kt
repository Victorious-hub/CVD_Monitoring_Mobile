package com.example.cvd_monitoring.presentation.patients.patient_card

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.PatientProfileViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PatientCardScreen(
    navController: NavController,
    viewModel: PatientCardViewModel = hiltViewModel(),
    slug: String
) {
    LaunchedEffect(key1 = slug) {
        viewModel.getPatientCard(slug)
    }
    val state = viewModel.state.value
    Spacer(modifier = Modifier.height(50.dp))
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(160.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            Modifier
                .padding(4.dp)
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxHeight()
                    .weight(0.8f)
            ) {
                Text(
                    text = state.patientCard?.patient?.user?.first_name ?: "No information",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = state.patientCard?.patient?.user?.last_name ?: "No information",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = state.patientCard?.patient?.user?.email ?: "No information",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = state.patientCard?.patient?.age.toString(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = state.patientCard?.patient?.weight.toString(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = state.patientCard?.patient?.height.toString(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = state.patientCard?.alcohol.toString(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}