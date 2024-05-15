package com.example.cvd_monitoring.presentation.patients.patient_doctor_list.components

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
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

import com.example.cvd_monitoring.domain.model.patients.DoctorList


@Composable
fun PatientDoctorListItem(
    doctor: DoctorList,
    onItemClick: (DoctorList) -> Unit
) {
    val image =
        rememberImagePainter(data = "http://127.0.0.1:8000${doctor.profileImage}")

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Card(
            modifier = Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth()
                .height(150.dp),
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
                        text = doctor.user.firstName ?: "No info",
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
                        text = doctor.user.lastName ?: "No info",
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
                        text = doctor.user.email ?: "No info",
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

    }

}

