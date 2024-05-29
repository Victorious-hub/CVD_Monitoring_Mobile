package com.example.cvd_monitoring.presentation.auth.logout_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cvd_monitoring.R
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

    val image = painterResource(R.drawable.heart)
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
                .background(
                    color = Color(0xFFa5051f),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 80.dp,
                        bottomEnd = 80.dp
                    )
                )
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 25.dp),
                painter = image,
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Logout User",
                modifier = Modifier.padding(bottom = 25.dp),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Monospace,
                )
            )

            Spacer(modifier = Modifier.size(25.dp))



            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFa5051f),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                viewModel.logoutUser()
                logout.invoke()
            }) {
                Text(

                    text = "Logout"
                )
            }
        }

    }
}