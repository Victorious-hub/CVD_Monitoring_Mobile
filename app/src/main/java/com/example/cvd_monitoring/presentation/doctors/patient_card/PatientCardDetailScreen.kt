package com.example.cvd_monitoring.presentation.doctors.patient_card

import com.example.cvd_monitoring.presentation.patients.patient_card.PatientCardViewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cvd_monitoring.R
import com.example.cvd_monitoring.domain.model.patients.PatientCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PatientCardDetailScreen(
    navController: NavController,
    viewModel: PatientCardViewModel = hiltViewModel(),
    slug: String,
    onClickCreateBloodAnalysis : (String) -> Unit,
    onClickCreateCholesterolAnalysis : (String) -> Unit,
    onClickCreateAppointment : (String) -> Unit,
    onClickMedicationList: (String) -> Unit,
    onClickConclusion: (String) -> Unit,
) {
    LaunchedEffect(key1 = slug) {
        viewModel.getPatientCard(slug)
    }
    val state = viewModel.state.value
    val image = painterResource(R.drawable.account)
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Card(
            modifier = Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth()
                .height(280.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.3f),
                    painter = image,
                    contentDescription = "Account Image"
                )
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
                            text = "First Name",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }

                    state.patientCard?.patient?.user?.let {
                        Text(
                            text = it.firstName ?: "No info",
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 13.sp,
                                color = Color.Black
                            )
                        )
                    }
                    Divider(
                        modifier = Modifier.padding(top = 3.dp, bottom = 8.dp),
                        color = Color.Gray
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxWidth().padding(bottom = 3.dp)
                    ) {
                        Text(
                            text = "Last Name",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                    state.patientCard?.patient?.user?.let {
                        Text(
                            text = it.lastName ?: "No info",
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 13.sp,
                                color = Color.Black
                            )
                        )
                    }
                    Divider(
                        modifier = Modifier.padding(top = 3.dp, bottom = 8.dp),
                        color = Color.Gray
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxWidth().padding(bottom = 3.dp)
                    ) {
                        Text(
                            text = "Birthday",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                    state.patientCard?.let {
                        Text(
                            text = it.birthday ?: "No info",
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 13.sp,
                                color = Color.Black
                            )
                        )
                    }
                    Divider(
                        modifier = Modifier.padding(top = 3.dp, bottom = 8.dp),
                        color = Color.Gray
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxWidth().padding(bottom = 3.dp)
                    ) {
                        Text(
                            text = "Age",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            ) // Set text color to black
                        )
                    }
                    state.patientCard?.let {
                        Text(
                            text = it.age.toString() ?: "No info",
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 13.sp,
                                color = Color.Black
                            )
                        )
                    }
                    Divider(
                        modifier = Modifier.padding(top = 3.dp, bottom = 8.dp),
                        color = Color.Gray
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxWidth().padding(bottom = 3.dp)
                    ) {
                        Text(
                            text = "Height",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                    state.patientCard.let {
                        if (it != null) {
                            Text(
                                text = it.height.toString() ?: "No info",
                                fontWeight = FontWeight.Bold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    color = Color.Black
                                )
                            )
                        }
                    }
                    Divider(
                        modifier = Modifier.padding(top = 3.dp, bottom = 8.dp),
                        color = Color.Gray
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxWidth().padding(bottom = 3.dp)
                    ) {
                        Text(
                            text = "Weight",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                    state.patientCard?.let {
                        Text(
                            text = it.weight.toString() ?: "No info",
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 13.sp,
                                color = Color.Black
                            )
                        )
                    }
                    Divider(
                        modifier = Modifier.padding(top = 3.dp, bottom = 8.dp),
                        color = Color.Gray
                    )
                }
            }
        }

    }

    Button(
        onClick = {
            if (state.patientCard?.isBloodAnalysis == false) {
                val email = viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
                val emailBeforeAt = email.substringBefore("@")
                onClickCreateBloodAnalysis(emailBeforeAt)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 320.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = if (state.patientCard?.isBloodAnalysis == false) Color.Black else Color.Gray
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Home Button Icon",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Create Blood Analysis",
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Arrow Right Icon",
                tint = Color.Black,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

        }

    }


    Button(
        onClick = {
            if (state.patientCard?.isCholesterolAnalysis == false)
            {
                val email = viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
                val emailBeforeAt = email.substringBefore("@")
                onClickCreateCholesterolAnalysis(emailBeforeAt)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 370.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = if (state.patientCard?.isCholesterolAnalysis == false) Color.Black else Color.Gray
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Home Button Icon",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Create Cholesterol Analysis",
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Arrow Right Icon",
                tint = Color.Black,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }


    Button(
        onClick = {
            val email = viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
            val emailBeforeAt = email.substringBefore("@")
            onClickCreateAppointment(emailBeforeAt)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 420.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Home Button Icon",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Create Appointment",
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Arrow Right Icon",
                tint = Color.Black,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }

    Button(
        onClick = {
            val email = viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
            val emailBeforeAt = email.substringBefore("@")
            onClickMedicationList(emailBeforeAt)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 470.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Home Button Icon",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Create Prescription",
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Arrow Right Icon",
                tint = Color.Black,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }


    Button(
        onClick = {
            if (state.patientCard?.analysisStatus == "CT")
            {
                val email = viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
                val emailBeforeAt = email.substringBefore("@")
                onClickConclusion(emailBeforeAt)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 520.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = if (state.patientCard?.analysisStatus == "CT") Color.Black else Color.Gray
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Home Button Icon",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Create conclusion",
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Arrow Right Icon",
                tint = Color.Black,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }

}