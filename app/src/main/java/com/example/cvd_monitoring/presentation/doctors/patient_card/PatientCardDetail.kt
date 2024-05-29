package com.example.cvd_monitoring.presentation.doctors.patient_card

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cvd_monitoring.R
import com.example.cvd_monitoring.presentation.auth.register_screen.ConditionRow
import com.example.cvd_monitoring.presentation.patients.patient_card.PatientCardViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PatientCardDetailScreen(
    navController: NavController,
    viewModel: PatientCardViewModel = hiltViewModel(),
    slug: String,
) {
    LaunchedEffect(key1 = slug) {
        viewModel.getPatientCard(slug)
    }
    val image = painterResource(R.drawable.account)
    Column(  // Use Column for vertical layout of cards
        modifier = Modifier.fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
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
        }
        Spacer(modifier = Modifier.height(25.dp))

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
                    text = "Mobile",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = viewModel.state.value.patientCard?.patient?.mobile.toString() ?: "No info",
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
                modifier = Modifier.fillMaxWidth()
                    .fillMaxWidth().padding(5.dp, 4.dp),
            ) {
                Text(
                    text = "Email",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = viewModel.state.value.patientCard?.patient?.user?.email ?: "No info",
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 4.dp)
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
                    text = "Is Smoking",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = viewModel.state.value.patientCard?.smoke.toString() ?: "No info",
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
                    .fillMaxWidth()
                    .padding(5.dp, 4.dp)
            ) {
                Text(
                    text = "Is Alcohol",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = viewModel.state.value.patientCard?.alcohol.toString() ?: "No info",
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
                    text = "Is Active",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = viewModel.state.value.patientCard?.active.toString() ?: "No info",
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

}