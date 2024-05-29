package com.example.cvd_monitoring.presentation.doctors.patient_card

import com.example.cvd_monitoring.presentation.patients.patient_card.PatientCardViewModel

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PresentToAll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PatientCardInfoScreen(
    navController: NavController,
    viewModel: PatientCardViewModel = hiltViewModel(),
    slug: String,
    onClickCreateBloodAnalysis : (String) -> Unit,
    onClickCreateCholesterolAnalysis : (String) -> Unit,
    onClickCreateAppointment : (String) -> Unit,
    onClickMedicationList: (String) -> Unit,
    onClickConclusion: (String) -> Unit,
    onClickCheckPatientBlood: (String) -> Unit,
    onClickCheckPatientCholesterol: (String) -> Unit,
    onClickCheckPatientPrescriptions: (String) -> Unit,
    onClickCheckPatientDetail: (String) -> Unit,
    onDismiss : () -> Unit,
    onClickBackToMain : () -> Unit
) {
    LaunchedEffect(key1 = slug) {
        viewModel.getPatientCard(slug)
    }
    Log.d("", viewModel.state.value.patientCard?.isConfirmed.toString())
    val state = viewModel.state.value
    val image = painterResource(R.drawable.account)

    var showConclusionDialog by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    if (showConclusionDialog){
        AlertDialog(
            onDismissRequest = { onDismiss() },
            buttons = { },
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth(),

            title = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ){
                        Column{
                            Text(
                                text = "Blood Status",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                            viewModel.state.value.patientCard?.isBloodAnalysis?.let { ConditionRow(condition = "Is created", check = it) }

                            Divider(  // Divider between sections
                                modifier = Modifier
                                    .padding(top = 3.dp, bottom = 8.dp)
                                    .width(125.dp),
                                color = Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.width(25.dp))
                        Button(
                            onClick = {
                                if (viewModel.state.value.patientCard?.isBloodAnalysis == true)
                                {
                                    val email =
                                        viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
                                    val emailBeforeAt = email.substringBefore("@")
                                    onClickCheckPatientBlood(emailBeforeAt)
                                }else{
                                    Toast.makeText(context, "Blood analysis isn't provided yet", Toast.LENGTH_SHORT).show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFa5051f),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text("Change")
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center
                    ){
                        Column{
                            Text(
                                text = "Cholesterol Status",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                            viewModel.state.value.patientCard?.isCholesterolAnalysis?.let { ConditionRow(condition = "Is created", check = it) }
                            Divider(
                                modifier = Modifier
                                    .padding(top = 3.dp, bottom = 8.dp)
                                    .width(125.dp),
                                color = Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.width(25.dp))
                        Button(
                            onClick = {
                                if (viewModel.state.value.patientCard?.isCholesterolAnalysis == true)
                                {
                                    val email =
                                        viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
                                    val emailBeforeAt = email.substringBefore("@")
                                    onClickCheckPatientCholesterol(emailBeforeAt)
                                }else{
                                    Toast.makeText(context, "Cholesterol analysis isn't provided yet", Toast.LENGTH_SHORT).show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFa5051f),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text("Change")
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center
                    ){
                        Column{
                            Text(
                                text = "Diagnosis Status",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                            viewModel.state.value.patientCard?.isConfirmed?.let { ConditionRow(condition = "Is confirmed", check = it) }
                            Divider(
                                modifier = Modifier
                                    .padding(top = 3.dp, bottom = 8.dp)
                                    .width(125.dp),
                                color = Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.width(25.dp))
                        Button(
                            onClick = {
                                if (viewModel.state.value.patientCard?.isCholesterolAnalysis == true && viewModel.state.value.patientCard?.isBloodAnalysis == true)
                                {
                                    val email =
                                        viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
                                    val emailBeforeAt = email.substringBefore("@")
                                    viewModel.createDisease(emailBeforeAt)
                                    Toast.makeText(context, "Successfully confirmed analysis", Toast.LENGTH_SHORT).show()
                                    onClickConclusion(emailBeforeAt)
                                }else{
                                    Toast.makeText(context, "Some of the analysis isn't ready yet", Toast.LENGTH_SHORT).show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFa5051f),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text("Confirm")
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Button(
                            onClick = {
                                if (viewModel.state.value.patientCard?.isConfirmed == true)
                                {
                                    val email =
                                        viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
                                    val emailBeforeAt = email.substringBefore("@")
                                    onClickConclusion(emailBeforeAt)
                                }else{
                                    Toast.makeText(context, "Diagnosis isn't confirmed yet", Toast.LENGTH_SHORT).show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFa5051f),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text("Create Conclusion")
                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        Button(
                            onClick = { showConclusionDialog = false},
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

    Column(
        modifier = Modifier.fillMaxSize()
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${viewModel.state.value.patientCard?.patient?.user?.firstName} ${viewModel.state.value.patientCard?.patient?.user?.lastName}",
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        val email =
                            viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
                        val emailBeforeAt = email.substringBefore("@")
                        onClickCheckPatientPrescriptions(emailBeforeAt)
                    },
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(
                        Color.LightGray
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Default.PresentToAll,
                            contentDescription = "Home Button Icon",
                            tint = Color.Black
                        )
                        Text(
                            text = "Prescription Info",
                            color = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.width(25.dp))
                Button(
                    onClick = {
                        val email =
                            viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
                        val emailBeforeAt = email.substringBefore("@")
                        onClickCheckPatientDetail(emailBeforeAt)
                    },
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(
                        Color.LightGray
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Home Button Icon",
                            tint = Color.Black
                        )
                        Text(
                            text = "Card Info",
                            color = Color.Black
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        Column(
            modifier = Modifier.padding(60.dp, 10.dp)
        ) {
            Card(
                modifier = Modifier
                    .height(210.dp)
                    .padding(8.dp, 4.dp),
                shape = MaterialTheme.shapes.small,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Diagnostics",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Button(
                        onClick = {
                            if (state.patientCard?.isBloodAnalysis == false && state.patientCard?.isConfirmed == false) {
                                val email = viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
                                val emailBeforeAt = email.substringBefore("@")
                                onClickCreateBloodAnalysis(emailBeforeAt)
                            }else{
                                Toast.makeText(context, "Blood analysis already created. You can't create new one before getting conclusion", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray,
                            contentColor = if (state.patientCard?.isBloodAnalysis == false) Color.Black else Color.Gray
                        )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "Create Blood Analysis",
                                color = Color.Black,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = {
                            if (state.patientCard?.isCholesterolAnalysis == false && state.patientCard?.isConfirmed == false) {
                                val email = viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
                                val emailBeforeAt = email.substringBefore("@")
                                onClickCreateCholesterolAnalysis(emailBeforeAt)
                            }else{
                                Toast.makeText(context, "Cholesterol analysis already created. You can't create new one before getting conclusion", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth() // Make all buttons same width
                            .height(35.dp), // Adjust height if needed
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray,
                            contentColor = if (state.patientCard?.isCholesterolAnalysis == false) Color.Gray else Color.Black,
                        )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "Create Cholesterol Analysis",
                                color = Color.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = {
                            showConclusionDialog = true
                        },
                        modifier = Modifier
                            .fillMaxWidth() // Make all buttons same width
                            .height(35.dp),  // Adjust height if needed
                        colors = ButtonDefaults.buttonColors(
                            Color.LightGray
                        )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "Analysis Status",
                                color = Color.Black
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Card(
                modifier = Modifier
                    .height(160.dp)
                    .padding(8.dp, 4.dp),
                shape = MaterialTheme.shapes.small
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Treatment",
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(15.dp))
                    Button(
                        onClick = {
                            val email = viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
                            val emailBeforeAt = email.substringBefore("@")
                            onClickMedicationList(emailBeforeAt)
                        },
                        modifier = Modifier
                            .fillMaxWidth() // Make all buttons same width
                            .height(35.dp),  // Adjust height if needed
                        colors = ButtonDefaults.buttonColors(
                            Color.LightGray
                        )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "Create Prescription",
                                color = Color.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = {
                            val email = viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
                            val emailBeforeAt = email.substringBefore("@")
                            onClickCreateAppointment(emailBeforeAt)
                        },
                        modifier = Modifier
                            .fillMaxWidth() // Make all buttons same width
                            .height(35.dp), // Adjust height if needed
                        colors = ButtonDefaults.buttonColors(
                            Color.LightGray
                        )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "Create Appointment",
                                color = Color.Black
                            )
                        }
                    }
                }
            }

            Column (
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ){
                Button(
                    onClick = {
                        onClickBackToMain()
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
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
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Home Button Icon",
                            tint = Color.Black
                        )
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
    }
}
