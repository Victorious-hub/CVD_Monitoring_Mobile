package com.example.cvd_monitoring.presentation.doctors.doctor_patients

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
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
import androidx.navigation.NavHostController
import com.example.cvd_monitoring.R
import com.example.cvd_monitoring.domain.model.doctors.DoctorPatients
import com.example.cvd_monitoring.presentation.Screen

import com.example.cvd_monitoring.presentation.notification.NotificationViewModel
import com.example.cvd_monitoring.presentation.notification.components.NotificationListItem
import com.example.cvd_monitoring.presentation.treatment.blood_analysis.components.BloodAnalysisListItem

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DoctorPatientsScreen(
    navController: NavHostController,
    viewModel: DoctorPatientsViewModel = hiltViewModel(),
    slug: String,
    onUpdateContact: (String) -> Unit,
) {
    LaunchedEffect(key1 = slug) {
        viewModel.getDoctorPatientList(slug)
    }
    val state = viewModel.state.value
    val image = painterResource(R.drawable.account)


    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxHeight()
    ) {
        state.patients?.patients?.let { patients ->
            items(patients) { patient ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxSize()
                ) {
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
                                Text(
                                    text = patient.user.firstName ?: "No info",
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
                                        text = "Last Name",
                                        style = TextStyle(
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                        )
                                    )
                                }
                                Text(
                                    text = patient.user.lastName ?: "No info",
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
                                        text = "Email",
                                        style = TextStyle(
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                        )
                                    )
                                }
                                Text(
                                    text = patient.user.email ?: "No info",
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
                                Button(
                                    onClick = {
                                        val email = patient.user.email ?: return@Button
                                        val emailBeforeAt = email.substringBefore("@")
                                        onUpdateContact(emailBeforeAt)
                                    },
                                    modifier = Modifier.width(125.dp).height(35.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.LightGray,
                                        contentColor = Color.Black
                                    ),
                                    shape = RoundedCornerShape(20.dp),
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Text(
                                            text = "Details",
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
                        }
                    }

                }

            }
        }

    }
}

