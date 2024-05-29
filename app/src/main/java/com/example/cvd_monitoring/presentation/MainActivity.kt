package com.example.cvd_monitoring.presentation

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Bloodtype
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DataSaverOn
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

import androidx.navigation.compose.rememberNavController
import com.example.cvd_monitoring.R

import com.example.cvd_monitoring.common.ConnectivityObserver
import com.example.cvd_monitoring.common.NetworkConnectivityObserver
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.presentation.auth.register_screen.ConditionRow
import com.example.cvd_monitoring.presentation.doctors.card_create.CardCreateViewModel

import com.example.cvd_monitoring.presentation.navigation.graphs.RootNavigationGraph

import com.example.cvd_monitoring.presentation.unavailable_connection.UnavailableConnectionScreen
import com.example.cvd_monitoring.presentation.doctors.schedule.calendar.CalendarDataSource
import com.example.cvd_monitoring.presentation.doctors.schedule.calendar.CalendarUiModel
import com.example.cvd_monitoring.presentation.ui.theme.Purple40
import com.example.cvd_monitoring.presentation.ui.theme.Purple80
import com.example.cvd_monitoring.presentation.ui.theme.PurpleGrey80
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var connectivityObserver: ConnectivityObserver
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000)
        installSplashScreen()
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        setContent {
            val status by connectivityObserver.observe().collectAsState(
                initial = ConnectivityObserver.Status.Unavailable
            )
            if (status == ConnectivityObserver.Status.Unavailable) {
                UnavailableConnectionScreen()
            } else {
                RootNavigationGraph(navController = rememberNavController())
            }
            // CardCreateScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun CardCreateScreen(
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 3.dp)
            ) {
                Text(
                    text = "Blood Analysis Status",
                    style = TextStyle(
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            }
            // viewModel.state.value.patientCard?.isCholesterolAnalysis?.let { ConditionRow(condition = "Is created", check = it) }

            Divider(  // Divider between sections
                modifier = Modifier
                    .padding(top = 3.dp, bottom = 8.dp)
                    .width(125.dp),
                color = Color.Gray
            )

                Text(
                    text = "Blood Analysis Status",
                    style = TextStyle(
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            }
            // viewModel.state.value.patientCard?.isCholesterolAnalysis?.let { ConditionRow(condition = "Is created", check = it) }

            Divider(
                modifier = Modifier
                    .padding(top = 3.dp, bottom = 8.dp)
                    .width(125.dp),
                color = Color.Gray
            )

        Row(
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFa5051f),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Create")
            }

            Spacer(modifier = Modifier.width(15.dp))

            Button(
                onClick = { },
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
//            Row(  // Row for Blood Analysis section
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 3.dp)
//            ) {
//                Text(
//                    text = "Blood Analysis",
//                    style = TextStyle(
//                        fontSize = 11.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Black
//                    )
//                )
//                Column(  // Column for Blood Analysis details
//                    modifier = Modifier.weight(1f)  // Occupy remaining space
//                ) {
////                    viewModel.state.value.patientCard?.isBloodAnalysis?.let { ConditionRow(condition = "Is created", check = it) }
//                }
//            }
//            Text(
//                text = "Blood Status" ?: "No info",
//                fontWeight = FontWeight.Bold,
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis,
//                style = TextStyle(
//                    fontSize = 13.sp,
//                    color = Color.Black
//                )
//            )

//            Divider(  // Divider between sections
//                modifier = Modifier
//                    .padding(top = 3.dp, bottom = 8.dp)
//                    .width(125.dp),
//                color = Color.Gray
//            )
//
//            Row(  // Row for Cholesterol Analysis section
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 3.dp)
//            ) {
//                Text(
//                    text = "Cholesterol Analysis",
//                    style = TextStyle(
//                        fontSize = 11.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Black
//                    )
//                )
//                Column(
//                    modifier = Modifier.weight(1f)
//                ) {
////                    viewModel.state.value.patientCard?.isCholesterolAnalysis?.let { ConditionRow(condition = "Is created", check = it) }
//                }
//            }
//            Text(
//                text = "Cholesterol Status" ?: "No info",
//                fontWeight = FontWeight.Bold,
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis,
//                style = TextStyle(
//                    fontSize = 13.sp,
//                    color = Color.Black
//                )
//            )
//
//            Divider(
//                modifier = Modifier
//                    .padding(top = 3.dp, bottom = 8.dp)
//                    .width(125.dp),
//                color = Color.Gray
//            )

//    Spacer(modifier = Modifier.height(50.dp))
//    Button(
//        onClick = {
////            if (state.patientCard?.isBloodAnalysis == false) {
////                val email = viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
////                val emailBeforeAt = email.substringBefore("@")
////                onClickCreateBloodAnalysis(emailBeforeAt)
////            }
//        },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 500.dp),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.White,
//            contentColor = Color.Black //if (state.patientCard?.isCholesterolAnalysis == false) Color.Black else Color.Gray,
//        ),
//        shape = RoundedCornerShape(20.dp)
//    ) {
//        Row(
//            horizontalArrangement = Arrangement.Start
//        ) {
//            Icon(
//                imageVector = Icons.Default.AddCircle,
//                contentDescription = "Home Button Icon",
//                tint = Color.Black
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Text(
//                text = "Create Blood Analysis",
//                modifier = Modifier.weight(1f)
//            )
//            Icon(
//                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
//                contentDescription = "Arrow Right Icon",
//                tint = Color.Black,
//                modifier = Modifier.align(Alignment.CenterVertically)
//            )
//        }
//    }
//
//    Button(
//        onClick = {
////            if (state.patientCard?.isCholesterolAnalysis == false) {
////                val email = viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
////                val emailBeforeAt = email.substringBefore("@")
////                onClickCreateCholesterolAnalysis(emailBeforeAt)
////            }
//        },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 550.dp),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.White,
//            contentColor = Color.Black //if (state.patientCard?.isCholesterolAnalysis == false) Color.Black else Color.Gray,
//        ),
//        shape = RoundedCornerShape(20.dp)
//    ) {
//        Row(
//            horizontalArrangement = Arrangement.Start
//        ) {
//            Icon(
//                imageVector = Icons.Default.AddCircle,
//                contentDescription = "Home Button Icon",
//                tint = Color.Black
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Text(
//                text = "Create Cholesterol Analysis",
//                modifier = Modifier.weight(1f)
//            )
//            Icon(
//                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
//                contentDescription = "Arrow Right Icon",
//                tint = Color.Black,
//                modifier = Modifier.align(Alignment.CenterVertically)
//            )
//        }
//    }
//
//    Button(
//        onClick = {
//
//        },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 600.dp),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.White,
//            contentColor = Color.Black
//        ),
//        shape = RoundedCornerShape(20.dp)
//    ) {
//        Row(
//            horizontalArrangement = Arrangement.Start
//        ) {
//            Icon(
//                imageVector = Icons.Default.AddCircle,
//                contentDescription = "Home Button Icon",
//                tint = Color.Black
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Text(
//                text = "Create Appointment",
//                modifier = Modifier.weight(1f)
//            )
//            Icon(
//                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
//                contentDescription = "Arrow Right Icon",
//                tint = Color.Black,
//                modifier = Modifier.align(Alignment.CenterVertically)
//            )
//        }
//    }
//
//    Button(
//        onClick = {
////            val email = viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
////            val emailBeforeAt = email.substringBefore("@")
////            onClickMedicationList(emailBeforeAt)
//        },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 600.dp),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.White,
//            contentColor = Color.Black
//        ),
//        shape = RoundedCornerShape(20.dp)
//    ) {
//        Row(
//            horizontalArrangement = Arrangement.Start
//        ) {
//            Icon(
//                imageVector = Icons.Default.AddCircle,
//                contentDescription = "Home Button Icon",
//                tint = Color.Black
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Text(
//                text = "Create Prescription",
//                modifier = Modifier.weight(1f)
//            )
//            Icon(
//                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
//                contentDescription = "Arrow Right Icon",
//                tint = Color.Black,
//                modifier = Modifier.align(Alignment.CenterVertically)
//            )
//        }
//    }
//
//    Button(
//        onClick = {
////            val email = viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
////            val emailBeforeAt = email.substringBefore("@")
////            onClickCreateAppointment(emailBeforeAt)
//        },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 650.dp),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.White,
//            contentColor = Color.Black
//        ),
//        shape = RoundedCornerShape(20.dp)
//    ) {
//        Row(
//            horizontalArrangement = Arrangement.Start
//        ) {
//            Icon(
//                imageVector = Icons.Default.AddCircle,
//                contentDescription = "Home Button Icon",
//                tint = Color.Black
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Text(
//                text = "Create Appointment",
//                modifier = Modifier.weight(1f)
//            )
//            Icon(
//                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
//                contentDescription = "Arrow Right Icon",
//                tint = Color.Black,
//                modifier = Modifier.align(Alignment.CenterVertically)
//            )
//        }
//    }
//
//    Button(
//        onClick = {
////            val email = viewModel.state.value.patientCard?.patient?.user?.email ?: return@Button
////            val emailBeforeAt = email.substringBefore("@")
////            onClickConclusion(emailBeforeAt)
//        },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 700.dp),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.White,
//            contentColor = Color.Black
//        ),
//        shape = RoundedCornerShape(20.dp)
//    ) {
//        Row(
//            horizontalArrangement = Arrangement.Start
//        ) {
//            Icon(
//                imageVector = Icons.Default.AddCircle,
//                contentDescription = "Home Button Icon",
//                tint = Color.Black
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Text(
//                text = "Create Conclusion",
//                modifier = Modifier.weight(1f)
//            )
//            Icon(
//                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
//                contentDescription = "Arrow Right Icon",
//                tint = Color.Black,
//                modifier = Modifier.align(Alignment.CenterVertically)
//            )
//        }
//    }
