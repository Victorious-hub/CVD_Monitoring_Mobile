package com.example.cvd_monitoring.presentation.patients.patient_doctor_list.components

import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

import com.example.cvd_monitoring.domain.model.patients.DoctorList
import com.example.cvd_monitoring.presentation.doctors.schedule.components.Content
import com.example.cvd_monitoring.presentation.doctors.schedule.components.Header
import com.example.cvd_monitoring.utils.Constants


@Composable
fun PatientDoctorListItem(
    doctor: DoctorList,
    onItemClick: (DoctorList) -> Unit,
    onDismiss : () -> Unit,
) {
    val image =
        rememberImagePainter(data = "http://${Constants.BASE_IP}:8000${doctor.profileImage}")
    var showAppointmentDialog by remember {
        mutableStateOf(false)
    }
    if (showAppointmentDialog){
        AlertDialog(
            onDismissRequest = { onDismiss() },
            buttons = { },
            modifier = Modifier.height(200.dp),

            title = {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp, 4.dp)
                    ){
                        Image(
                            modifier = Modifier
                                .size(65.dp),
                            painter = image,
                            contentDescription = "Account Image"
                        )
                        Spacer(modifier = Modifier.width(25.dp))
                        Text(
                            text = "${doctor.user.firstName} ${doctor.user.lastName}" ?: "No info",
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.Black
                            )
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "About",
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.Black
                            )
                        )

                        Text(
                            text = doctor.description ?: "No info",
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        )

                        Button(
                            onClick = { showAppointmentDialog = false },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFa5051f),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text(text = "Close")
                        }
                    }
                }
            }
        )
    }

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
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxWidth()
                            .padding(bottom = 3.dp)
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

                    Button(
                        onClick = {
                            showAppointmentDialog = true
                        },
                        modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(
                            Color.LightGray
                        )
                    ){
                        Row(
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Home Button Icon",
                                tint = Color.Black
                            )
                            Text(
                                text = "Details",
                                color = Color.Black
                            )
                        }
                    }

                }
            }
        }

    }

}

