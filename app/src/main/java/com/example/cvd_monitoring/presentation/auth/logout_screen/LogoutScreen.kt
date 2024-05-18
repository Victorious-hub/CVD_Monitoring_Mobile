package com.example.cvd_monitoring.presentation.auth.logout_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.presentation.auth.logout_screen.LogoutViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LogoutScreen(
    navHostController: NavHostController,
    logout: () -> Unit,
    viewModel: LogoutViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.SnackbarEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Short
                    )
                }
                is UiEvents.NavigateEvent -> {
                    Log.d("SignUpViewModel", event.route)
                    navHostController.navigate(event.route)
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Login Successful",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Logout Screen",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.size(100.dp))

            Button(onClick = {
                viewModel.logoutUser()
                logout.invoke()
            }) {
                Text(text = "Logout")
            }
        }

    }
}