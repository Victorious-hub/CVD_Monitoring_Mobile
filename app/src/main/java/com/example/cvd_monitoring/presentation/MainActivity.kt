package com.example.cvd_monitoring.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.ModalDrawer
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cvd_monitoring.R

import com.example.cvd_monitoring.common.ConnectivityObserver
import com.example.cvd_monitoring.common.NetworkConnectivityObserver
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.domain.model.analysis.BloodAnalysis
import com.example.cvd_monitoring.navigation.graphs.RootNavGraph
import com.example.cvd_monitoring.presentation.doctors.doctor_profile_screen.DoctorProfileViewModel
import com.example.cvd_monitoring.presentation.navigation.home.HomeScreen
import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.PatientProfileViewModel
import com.example.cvd_monitoring.presentation.unavailable_connection.UnavailableConnectionScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var connectivityObserver: ConnectivityObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectivityObserver = NetworkConnectivityObserver(applicationContext)


        setContent {
            val status by connectivityObserver.observe().collectAsState(
                initial = ConnectivityObserver.Status.Unavailable
            )
            if (status == ConnectivityObserver.Status.Unavailable) {
                UnavailableConnectionScreen()
            } else {
                NavGraph()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PatientProfileScreen(
) {

    val image = painterResource(R.drawable.account)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.25f)
            .background(
                color = Color(0xFFa5051f),
            )
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 25.dp)
                .size(width = 100.dp, height = 100.dp),
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

        Text(
            text = "Doctor Info",
            modifier = Modifier.padding(bottom = 320.dp),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Monospace,
                color = Color.Black // Set text color to black
            )
        )

    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth().padding(top = 20.dp, bottom = 5.dp)
        ) {
            Text(
                text = "First Name",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black) // Set text color to black
            )
            Spacer(modifier = Modifier
                .weight(0.5f))
        }
        Text(
            text = "wqrqwr",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 16.sp, color = Color.Black) // Set text color to black
        )
        Divider(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp),
            color = Color.Gray
        )
        Row(
            modifier = Modifier.fillMaxWidth()
                .fillMaxWidth().padding(top = 20.dp, bottom = 5.dp)
        ){
            Text(
                text = "Last Name",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black) // Set text color to black
            )
            Spacer(modifier = Modifier.weight(2f))
        }
        Text(
            text = "wqrqwr",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 16.sp, color = Color.Black) // Set text color to black
        )
        Divider(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            color = Color.Gray
        )
        Row(
            modifier = Modifier.fillMaxWidth()
                .fillMaxWidth().padding(top = 20.dp, bottom = 5.dp)
        ){
            Text(
                text = "Email",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black) // Set text color to black
            )
            Spacer(modifier = Modifier.weight(2f))
        }
        Text(
            text = "wqrqwr",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 16.sp, color = Color.Black) // Set text color to black
        )
        Divider(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            color = Color.Gray
        )
    }

}
