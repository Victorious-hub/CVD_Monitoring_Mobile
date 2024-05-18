package com.example.cvd_monitoring.presentation.patients.patient_contact_screen

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import androidx.compose.foundation.layout.*
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.cvd_monitoring.common.UiEvents
import kotlinx.coroutines.flow.collectLatest

import java.time.LocalDate
import java.time.format.DateTimeFormatter




@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientContactScreen(
    navController: NavController,
    slug: String,
    viewModel: PatientContactViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = slug) {
        viewModel.getCurrentPatient(slug)
    }

    val firstNameState = viewModel.firstNameState.value
    val lastNameState = viewModel.lastNameState.value
    val mobileState  = viewModel.mobileState.value
    val addressState  = viewModel.adressState.value
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    val isFocused by remember { mutableStateOf(false) }
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
                    Log.d("PatientContactScreen", event.route)
                    navController.navigate(event.route)
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Update Successful",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Contact data",
            modifier = Modifier.padding(bottom = 25.dp),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Monospace,
            )
        )
        TextField(
            value = firstNameState.text,
            onValueChange = {  viewModel.setFirstNameValue(it) },
            label = {
                Text(
                    text = "First Name",
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
            value = lastNameState.text,
            onValueChange = { viewModel.setLastNameValue(it) },
            label = {
                Text(
                    text = "Last Name",
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
            value = mobileState.text,
            onValueChange = { viewModel.setMobileValue(it) },
            label = {
                Text(
                    text = "Mobile",
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
            value = addressState.text,
            onValueChange = { viewModel.setAdressValue(it) },
            label = {
                Text(
                    text = "Address",
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
                if (firstNameState.text.isEmpty())
                {
                    Toast.makeText(context, "First name can not be empty", Toast.LENGTH_SHORT).show()
                } else if (lastNameState.text.isEmpty())
                {
                    Toast.makeText(context, "Last name can not be empty", Toast.LENGTH_SHORT).show()
                }
                else if (addressState.text.isEmpty())
                {
                    Toast.makeText(context, "Address name can not be empty", Toast.LENGTH_SHORT).show()
                }
                else if (mobileState.text.isEmpty())
                {
                    Toast.makeText(context, "Mobile name can not be empty", Toast.LENGTH_SHORT).show()
                }
                else if (!mobileState.text.matches("^\\+\\d{10}$".toRegex())) {
                    Toast.makeText(context, "Invalid mobile number format. Must be numbers and +XXXXXXXXXX", Toast.LENGTH_SHORT).show()
                }
                else {
                    viewModel.updatePatientContact(slug)
                    Toast.makeText(context, "Data updated successfully", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFa5051f),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Update Data")
        }
    }

}

