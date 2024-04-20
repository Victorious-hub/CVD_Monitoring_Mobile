package com.example.cvd_monitoring.presentation.patients.patient_profile_screen

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import com.example.cvd_monitoring.presentation.Screen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PatientProfileScreen(
    navController: NavController,
    viewModel: PatientProfileViewModel = hiltViewModel(),

    slug: String,
) {
    LaunchedEffect(key1 = slug) {
        viewModel.getCurrentUser(slug)
    }
    val state = viewModel.state.value
    Card(
        modifier = Modifier
            .padding(25.dp, 50.dp)
            .fillMaxWidth()
            .height(100.dp),
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
                state.patient?.user?.let {
                    Text(
                        text = it.first_name,
                        fontWeight = FontWeight.Bold
                    )
                }
                state.patient?.user?.let {
                    Text(
                        text = it.last_name,
                        modifier = Modifier
                            .background(Color.LightGray)
                            .padding(4.dp)
                    )
                }
                state.patient?.user?.let {
                    Text(
                        text = it.email,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    text = state.patient?.age.toString(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = state.patient?.weight.toString(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = state.patient?.height.toString(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

    Button(
        onClick = {
            navController.navigate("${Screen.UpdateDataPatient.route}/$slug/data")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 200.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFa5051f),
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Home Button Icon",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Update Data",
                modifier = Modifier.weight(1f)
            )
        }

    }
    Button(
        onClick = {
            navController.navigate("${Screen.UpdateContactPatient.route}/$slug/contact")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 250.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFa5051f),
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Home Button Icon",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Update Contact",
                modifier = Modifier.weight(1f)
            )
        }
    }
}