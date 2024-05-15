package com.example.cvd_monitoring.presentation.treatment.prescription

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cvd_monitoring.R
import com.example.cvd_monitoring.presentation.doctors.doctor_blood_create.BloodAnalysisCreateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientPrescriptionScreen(
    navController: NavHostController,
    viewModel: PatientPrescriptionViewModel = hiltViewModel(),
    slug: String,
) {

    val image = painterResource(R.drawable.heart)
    val dosage = viewModel.dosageState.value
    val startDate = viewModel.startDateState.value
    val endDate = viewModel.endDateState.value

    val isFocused by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()

//    LaunchedEffect(key1 = true) {
//        viewModel.eventFlow.collectLatest { event ->
//            when (event) {
//                is UiEvents.SnackbarEvent -> {
//                    scaffoldState.snackbarHostState.showSnackbar(
//                        message = event.message,
//                        duration = SnackbarDuration.Short
//                    )
//                }
//                is UiEvents.NavigateEvent -> {
//                    Log.d("SignUpViewModel", event.route)
//                    navController.navigate(event.route)
//                    scaffoldState.snackbarHostState.showSnackbar(
//                        message = "Login Successful",
//                        duration = SnackbarDuration.Short
//                    )
//                }
//            }
//        }
//    }

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
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(125.dp))
        Text(
            text = "Patient Prescription",
            modifier = Modifier.padding(bottom = 25.dp),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Monospace,
            )
        )
        TextField(
            value = dosage.text,
            onValueChange = { viewModel.setDosageStateValue(it) },
            label = {
                Text(
                    text = "Dosage",
                    color = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = if (isFocused) Color.Red else Color.Black,
                cursorColor = Color.Red,
            ),
        )
        TextField(
            value = startDate.text,
            onValueChange = { viewModel.setStartDateValue(it) },
            label = {
                Text(
                    text = "Start Date",
                    color = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = if (isFocused) Color.Red else Color.Black,
                cursorColor = Color.Red,
            ),
        )
        TextField(
            value = endDate.text,
            onValueChange = { viewModel.setEndDateStateValue(it) },
            label = {
                Text(
                    text = "End Date",
                    color = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = if (isFocused) Color.Red else Color.Black,
                cursorColor = Color.Red,
            ),
        )


        Button(
            onClick = {
                viewModel.createPatientPrescription(slug)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFa5051f),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Create")
        }
    }
}