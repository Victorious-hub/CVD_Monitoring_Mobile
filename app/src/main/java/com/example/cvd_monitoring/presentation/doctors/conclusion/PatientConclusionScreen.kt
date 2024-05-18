package com.example.cvd_monitoring.presentation.doctors.conclusion

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cvd_monitoring.R
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.presentation.doctors.doctor_blood_create.BloodAnalysisCreateViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientConclusionScreen(
    navController: NavHostController,
    viewModel: PatientConclusionViewModel = hiltViewModel(),
    slug: String,
) {
    LaunchedEffect(key1 = slug) {
        viewModel.getPatientDiagnosis(slug)
    }

    val image = painterResource(R.drawable.heart)
    val description = viewModel.descriptionState.value
    val recommendations = viewModel.recommendationsState.value

    val isFocused by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

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
                    navController.navigate(event.route)
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Login Successful",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

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
            text = "Patient Conclusion",
            modifier = Modifier.padding(bottom = 25.dp),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Monospace,
            )
        )
        Card(
            modifier = Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth()
                .height(190.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.6f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxWidth().padding(bottom = 3.dp)
                    ) {
                        Text(
                            text = "Ap Hi",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                    Text(
                        text = viewModel.state.value.diagnosis?.bloodAnalysis?.apHi.toString() ?: "No info",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    )
                    Divider(
                        modifier = Modifier.padding(top = 3.dp, bottom = 8.dp),
                        color = Color.Gray
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxWidth().padding(bottom = 3.dp)
                    ) {
                        Text(
                            text = "Ap Lo",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                    Text(
                        text = viewModel.state.value.diagnosis?.bloodAnalysis?.apLo.toString() ?: "No info",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    )
                    Divider(
                        modifier = Modifier.padding(top = 3.dp, bottom = 8.dp),
                        color = Color.Gray
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxWidth().padding(bottom = 3.dp)
                    ) {
                        Text(
                            text = "Glucose",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                    Text(
                        text = viewModel.state.value.diagnosis?.bloodAnalysis?.glucose.toString() ?: "No info",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    )
                    Divider(
                        modifier = Modifier.padding(top = 3.dp, bottom = 8.dp),
                        color = Color.Gray
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxWidth().padding(bottom = 3.dp)
                    ) {
                        Text(
                            text = "Glucose",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                    Text(
                        text = viewModel.state.value.diagnosis?.anomaly.toString() ?: "No info",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    )
                    Divider(
                        modifier = Modifier.padding(top = 3.dp, bottom = 8.dp),
                        color = Color.Gray
                    )
                }
            }

        }


        TextField(
            value = description.text,
            onValueChange = { viewModel.setDescriptionStateValue(it) },
            label = {
                Text(
                    text = "Description",
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
            value = recommendations.text,
            onValueChange = { viewModel.setRecommendationsStateValue(it) },
            label = {
                Text(
                    text = "Recommendations",
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
                if (viewModel.descriptionState.value.text.isEmpty())
                {
                    Toast.makeText(context, "Description can not be empty", Toast.LENGTH_SHORT).show()
                } else if (viewModel.recommendationsState.value.text.isEmpty()) {
                    Toast.makeText(context, "Recommendations can not be empty", Toast.LENGTH_SHORT).show()
                } else{
                    viewModel.createPatientConclusion(slug)
                    Toast.makeText(context, "Conclusion created successfully", Toast.LENGTH_SHORT).show()
                }
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