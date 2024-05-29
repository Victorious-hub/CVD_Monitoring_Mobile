package com.example.cvd_monitoring.presentation.patients.patient_card

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cvd_monitoring.R
import com.example.cvd_monitoring.presentation.Screen
import com.example.cvd_monitoring.presentation.navigation.graphs.PatientActions
import com.example.cvd_monitoring.presentation.navigation.graphs.getRouteWithSlug

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PatientCardScreen(
    navController: NavController,
    viewModel: PatientCardViewModel = hiltViewModel(),
    slug: String,
    onClickBlood : (String) -> Unit,
    onClickCholesterol : (String) -> Unit,
    onClickPrescription : (String) -> Unit,
    onClickConclusion : (String) -> Unit,
    onClickBackToMain : () -> Unit,
    onClickDoctors : (String) -> Unit,
    onClickAppointments : (String) -> Unit,
) {
    LaunchedEffect(key1 = slug) {
        viewModel.getPatientCard(slug)
    }
    val state = viewModel.state.value
    val image = painterResource(R.drawable.account)
    if (state.patientCard?.patient != null) {
        Column(  // Use Column for vertical layout of cards
            modifier = Modifier.fillMaxHeight()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .padding(8.dp, 4.dp),
                shape = MaterialTheme.shapes.small
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(150.dp),
                    painter = image,
                    contentDescription = "Account Image"
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "${viewModel.state.value.patientCard?.patient?.user?.firstName}-${viewModel.state.value.patientCard?.patient?.user?.lastName}" ?: "No info",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Button(
                        onClick = {
                            onClickAppointments(slug)
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
                                imageVector = Icons.Default.MedicalInformation,
                                contentDescription = "Home Button Icon",
                                tint = Color.Black
                            )
                            Text(
                                text = "Appointments",
                                color = Color.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = {
                            onClickDoctors(slug)
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
                                imageVector = Icons.Default.HealthAndSafety,
                                contentDescription = "Home Button Icon",
                                tint = Color.Black
                            )
                            Text(
                                text = "Your doctors",
                                color = Color.Black
                            )
                        }
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 4.dp),
            ){
                Card(
                    modifier = Modifier
                        .width(180.dp)
                        .height(100.dp)
                        .padding(8.dp, 4.dp),
                    shape = MaterialTheme.shapes.small
                ){
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp, 4.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Medication,
                            contentDescription = "Home Button Icon",
                            tint = Color.Black
                        )
                        Text(
                            text = "Diagnostics",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxWidth()
                            .padding(5.dp, 4.dp),
                    ){
                        Button(
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp, 4.dp),
                            onClick = {
                                onClickConclusion(slug)
                            },
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
                Spacer(modifier = Modifier.width(25.dp))
                Card(
                    modifier = Modifier
                        .width(190.dp)
                        .height(100.dp)
                        .padding(8.dp, 4.dp),
                    shape = MaterialTheme.shapes.small
                ){
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxWidth()
                            .padding(5.dp, 4.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.MedicalServices,
                            contentDescription = "Home Button Icon",
                            tint = Color.Black
                        )
                        Text(
                            text = "Prescriptions",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                    Button(
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp, 4.dp),
                        onClick = {
                            onClickPrescription(slug)
                        },
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

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 4.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                        .fillMaxWidth().padding(5.dp, 4.dp),
                ) {
                    Text(
                        text = "Gender",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = viewModel.state.value.patientCard?.gender.toString() ?: "No info",
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


            Spacer(modifier = Modifier.height(25.dp))

            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(1.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 4.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                        .fillMaxWidth().padding(5.dp, 4.dp),
                ) {
                    Text(
                        text = "Blood Type",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = viewModel.state.value.patientCard?.bloodType.toString() ?: "No info",
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

            Spacer(modifier = Modifier.height(25.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 4.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()
                        .padding(5.dp, 4.dp)
                ) {
                    Text(
                        text = "Weight",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = viewModel.state.value.patientCard?.weight.toString() ?: "No info",
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

            Spacer(modifier = Modifier.height(25.dp))

            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(1.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 4.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()
                        .padding(5.dp, 4.dp)
                ) {
                    Text(
                        text = "Height",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = viewModel.state.value.patientCard?.height.toString() ?: "No info",
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

            Spacer(modifier = Modifier.height(25.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 4.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp, 4.dp)
                ) {
                    Text(
                        text = "Birthday",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = viewModel.state.value.patientCard?.birthday.toString()?: "No info",
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


            Spacer(modifier = Modifier.height(25.dp))

            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(1.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(5.dp, 4.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp, 4.dp)
                ) {
                    Text(
                        text = "Abnormal conditions",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.width(100.dp))

                    Text(

                        text = viewModel.state.value.patientCard?.abnormalConditions.toString() ?: "No info",
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

        }

//        Button(
//            onClick = {
//                onClickBlood(slug)
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 320.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color.White,
//                contentColor = Color.Black
//            ),
//            shape = RoundedCornerShape(20.dp)
//        ) {
//            Row(
//                horizontalArrangement = Arrangement.Start
//            ) {
//                Icon(
//                    imageVector = Icons.Default.AddCircle,
//                    contentDescription = "Home Button Icon",
//                    tint = Color.Black
//                )
//                Spacer(modifier = Modifier.width(16.dp))
//                Text(
//                    text = "Blood Analysis",
//                    modifier = Modifier.weight(1f)
//                )
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
//                    contentDescription = "Arrow Right Icon",
//                    tint = Color.Black,
//                    modifier = Modifier.align(Alignment.CenterVertically)
//                )
//
//            }
//
//        }
//
//
//        Button(
//            onClick = {
//                onClickCholesterol(slug)
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 370.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color.White,
//                contentColor = Color.Black
//            ),
//            shape = RoundedCornerShape(20.dp)
//        ) {
//            Row(
//                horizontalArrangement = Arrangement.Start
//            ) {
//                Icon(
//                    imageVector = Icons.Default.AddCircle,
//                    contentDescription = "Home Button Icon",
//                    tint = Color.Black
//                )
//                Spacer(modifier = Modifier.width(16.dp))
//                Text(
//                    text = "Cholesterol Analysis",
//                    modifier = Modifier.weight(1f)
//                )
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
//                    contentDescription = "Arrow Right Icon",
//                    tint = Color.Black,
//                    modifier = Modifier.align(Alignment.CenterVertically)
//                )
//            }
//        }
//
//
//        Button(
//            onClick = {
//                onClickPrescription(slug)
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 420.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color.White,
//                contentColor = Color.Black
//            ),
//            shape = RoundedCornerShape(20.dp)
//        ) {
//            Row(
//                horizontalArrangement = Arrangement.Start
//            ) {
//                Icon(
//                    imageVector = Icons.Default.AddCircle,
//                    contentDescription = "Home Button Icon",
//                    tint = Color.Black
//                )
//                Spacer(modifier = Modifier.width(16.dp))
//                Text(
//                    text = "Prescription List",
//                    modifier = Modifier.weight(1f)
//                )
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
//                    contentDescription = "Arrow Right Icon",
//                    tint = Color.Black,
//                    modifier = Modifier.align(Alignment.CenterVertically)
//                )
//            }
//        }
//
//        Button(
//            onClick = {
//                onClickConclusion(slug)
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 420.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color.White,
//                contentColor = Color.Black
//            ),
//            shape = RoundedCornerShape(20.dp)
//        ) {
//            Row(
//                horizontalArrangement = Arrangement.Start
//            ) {
//                Icon(
//                    imageVector = Icons.Default.AddCircle,
//                    contentDescription = "Home Button Icon",
//                    tint = Color.Black
//                )
//                Spacer(modifier = Modifier.width(16.dp))
//                Text(
//                    text = "Your conclusions",
//                    modifier = Modifier.weight(1f)
//                )
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
//                    contentDescription = "Arrow Right Icon",
//                    tint = Color.Black,
//                    modifier = Modifier.align(Alignment.CenterVertically)
//                )
//            }
//        }
    }
    else{
        Text(
            text = "Your patient card isn't ready yet",
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black
            ),
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .padding(16.dp),
            textAlign = TextAlign.Center,
        )
        Button(
            onClick = {
                onClickBackToMain()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 425.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Back to main",
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