package com.example.cvd_monitoring.presentation.patients.patient_conclusion.components

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.cvd_monitoring.R
import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.domain.model.analysis.PatientConclusion
import com.example.cvd_monitoring.presentation.doctors.schedule.components.Content
import com.example.cvd_monitoring.presentation.doctors.schedule.components.Header
import com.example.cvd_monitoring.utils.Constants


@Composable
fun PatientConclusionListItemScreen(
    conclusion: PatientConclusion,
    onDismissAnalysis : () -> Unit,
    onDismissTreatment : () -> Unit,
) {
    val doctorImage = rememberImagePainter(data = "http://${Constants.BASE_IP}:8000${conclusion.doctor.profileImage}")
    var showTreatmentDialog by remember {
        mutableStateOf(false)
    }
    var showBLoodAnalysisDialog by remember {
        mutableStateOf(false)
    }
    var showCholesterolAnalysisDialog by remember {
        mutableStateOf(false)
    }

    if (showTreatmentDialog){
        AlertDialog(
            onDismissRequest = { onDismissTreatment() },
            buttons = { },
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            title = {
                Column {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp, 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 3.dp)
                        ) {
                            Text(
                                text = "Description",
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                        }
                        Text(
                            text = conclusion.description ?: "No info",
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 11.sp,
                                color = Color.Black
                            )
                        )
                        Divider(
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp),
                            color = Color.Gray
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 3.dp)
                        ) {
                            Text(
                                text = "Recommendations",
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                        }
                        Text(
                            text = conclusion.recommendations ?: "No info",
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 11.sp,
                                color = Color.Black
                            )
                        )
                        Divider(
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp),
                            color = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = { showTreatmentDialog = false },
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
        )
    }

    if (showBLoodAnalysisDialog){
        AlertDialog(
            onDismissRequest = { onDismissAnalysis() },
            buttons = { },
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            title = {
                Column {
                    Row(
                        Modifier
                            .padding(4.dp)
                    ) {
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
                                    .padding(bottom = 3.dp)
                            ) {
                                Text(
                                    text = "Created at",
                                    style = TextStyle(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                )
                            }
                            Text(
                                text = conclusion.analysisResult.bloodAnalysis.createdAt ?: "No info",
                                fontWeight = FontWeight.Bold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    color = Color.Black
                                )
                            )
                            Divider(
                                modifier = Modifier
                                    .padding(top = 8.dp, bottom = 8.dp),
                                color = Color.Gray
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxWidth()
                                    .padding(bottom = 3.dp)
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
                                text = conclusion.analysisResult.bloodAnalysis.glucose.toString()  ?: "No info",
                                fontWeight = FontWeight.Bold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    color = Color.Black
                                )
                            )
                            Divider(
                                modifier = Modifier
                                    .padding(top = 8.dp, bottom = 8.dp),
                                color = Color.Gray
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxWidth()
                                    .padding(bottom = 3.dp)
                            ) {
                                Text(
                                    text = "Ap hi",
                                    style = TextStyle(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                )
                            }
                            Text(
                                text = conclusion.analysisResult.bloodAnalysis.apHi.toString()  ?: "No info",
                                fontWeight = FontWeight.Bold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    color = Color.Black
                                )
                            )
                            Divider(
                                modifier = Modifier
                                    .padding(top = 8.dp, bottom = 8.dp),
                                color = Color.Gray
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 3.dp)
                            ) {
                                Text(
                                    text = "Ap lo",
                                    style = TextStyle(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                )
                            }
                            Text(
                                text = conclusion.analysisResult.bloodAnalysis.apLo.toString() ?: "No info",
                                fontWeight = FontWeight.Bold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    color = Color.Black
                                )
                            )
                            Divider(
                                modifier = Modifier
                                    .padding(top = 8.dp, bottom = 8.dp),
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(
                                onClick = {  showBLoodAnalysisDialog = false},
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
            }
        )
    }

    if (showCholesterolAnalysisDialog){
        AlertDialog(
            onDismissRequest = { onDismissAnalysis() },
            buttons = { },
            modifier = Modifier
                .height(310.dp)
                .fillMaxWidth(),
            title = {
                Column {
                    Row(
                        Modifier
                            .padding(4.dp)
                    ) {
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
                                    .padding(bottom = 3.dp)
                            ) {
                                Text(
                                    text = "Created at",
                                    style = TextStyle(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                )
                            }
                            Text(
                                text = conclusion.analysisResult.cholesterolAnalysis.createdAt ?: "No info",
                                fontWeight = FontWeight.Bold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    color = Color.Black
                                )
                            )
                            Divider(
                                modifier = Modifier
                                    .padding(top = 8.dp, bottom = 8.dp),
                                color = Color.Gray
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxWidth()
                                    .padding(bottom = 3.dp)
                            ) {
                                Text(
                                    text = "Cholesterol",
                                    style = TextStyle(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                )
                            }
                            Text(
                                text = conclusion.analysisResult.cholesterolAnalysis.cholesterol.toString()  ?: "No info",
                                fontWeight = FontWeight.Bold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    color = Color.Black
                                )
                            )
                            Divider(
                                modifier = Modifier
                                    .padding(top = 8.dp, bottom = 8.dp),
                                color = Color.Gray
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxWidth()
                                    .padding(bottom = 3.dp)
                            ) {
                                Text(
                                    text = "hdlCholesterol",
                                    style = TextStyle(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                )
                            }
                            Text(
                                text = conclusion.analysisResult.cholesterolAnalysis.hdlCholesterol.toString()  ?: "No info",
                                fontWeight = FontWeight.Bold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    color = Color.Black
                                )
                            )
                            Divider(
                                modifier = Modifier
                                    .padding(top = 8.dp, bottom = 8.dp),
                                color = Color.Gray
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 3.dp)
                            ) {
                                Text(
                                    text = "ldlCholesterol",
                                    style = TextStyle(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                )
                            }
                            Text(
                                text = conclusion.analysisResult.cholesterolAnalysis.ldlCholesterol.toString() ?: "No info",
                                fontWeight = FontWeight.Bold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    color = Color.Black
                                )
                            )
                            Divider(
                                modifier = Modifier
                                    .padding(top = 8.dp, bottom = 8.dp),
                                color = Color.Gray
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxWidth()
                                    .padding(bottom = 3.dp)
                            ) {
                                Text(
                                    text = "triglycerides",
                                    style = TextStyle(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                )
                            }
                            Text(
                                text = conclusion.analysisResult.cholesterolAnalysis.triglycerides.toString() ?: "No info",
                                fontWeight = FontWeight.Bold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    color = Color.Black
                                )
                            )
                            Divider(
                                modifier = Modifier
                                    .padding(top = 8.dp, bottom = 8.dp),
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(
                                onClick = {  showCholesterolAnalysisDialog = false},
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
            }
        )
    }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Card(
            modifier = Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth()
                .height(210.dp),
            shape = MaterialTheme.shapes.medium,
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier.size(80.dp),
                    painter = doctorImage,
                    contentDescription = "Account Image"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(0.6f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxWidth()
                            .padding(bottom = 3.dp)
                    ) {
                        Text(
                            text = "Doctor First Name",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                    Text(
                        text = conclusion.doctor.user.firstName ?: "No info",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    )
                    Divider(
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                        color = Color.Gray
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxWidth()
                            .padding(bottom = 3.dp)
                    ) {
                        Text(
                            text = "Doctor Last Name",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                    Text(
                        text = conclusion.doctor.user.lastName ?: "No info",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    )
                    Divider(
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 8.dp),
                        color = Color.Gray
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxWidth()
                            .padding(bottom = 3.dp)
                    ) {
                        Text(
                            text = "Diagnosis",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                    Text(
                        text = "Heart troubles: ${conclusion.analysisResult.anomaly.toString()}" ?: "No info",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    )
                    Divider(
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 8.dp),
                        color = Color.Gray
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { showBLoodAnalysisDialog = true },
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(
                        Color.LightGray
                    )
                ){
                    Row(
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Blood",
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = {
                        showCholesterolAnalysisDialog = true
                    },
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(
                        Color.LightGray
                    )
                ){
                    Row(
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Cholesterol",
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = {
                        showTreatmentDialog = true
                    },
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(
                        Color.LightGray
                    )
                ){
                    Row(
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Treatment",
                            color = Color.Black
                        )
                    }
                }


            }
        }

    }

}