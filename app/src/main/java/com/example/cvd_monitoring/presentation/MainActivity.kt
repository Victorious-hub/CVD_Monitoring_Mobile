package com.example.cvd_monitoring.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.common.ConnectivityObserver
import com.example.cvd_monitoring.common.NetworkConnectivityObserver
import com.example.cvd_monitoring.common.UiEvents
import com.example.cvd_monitoring.presentation.unavailable_connection.UnavailableConnectionScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEach
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
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PatientProfileScreen(
) {

    Spacer(modifier = Modifier.height(50.dp))
    Card(
        modifier = Modifier
            .padding(25.dp, 50.dp)
            .fillMaxWidth()
            .height(80.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            Modifier
                .padding(4.dp)
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxHeight()
                    .weight(0.8f)
            ) {
                Text(
                    "First Name : first name"

                )
                Text(
                    "Last Name : last name"
                )
                Text(
                    "Email: email"
                )
            }
        }
    }
}


