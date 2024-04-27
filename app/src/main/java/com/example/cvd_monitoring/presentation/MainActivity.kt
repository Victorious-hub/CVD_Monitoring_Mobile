package com.example.cvd_monitoring.presentation

import android.annotation.SuppressLint
import android.os.Bundle
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewModelScope
import com.example.cvd_monitoring.R
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
@Composable
fun WelcomeScreen(
) {
    val image = painterResource(R.drawable.heart)
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
                .padding(top = 25.dp),
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
            text = "Account Info",
            modifier = Modifier.padding(bottom = 320.dp),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Monospace,
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
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier
                .weight(0.5f))
        }
        Text(
            text = "first name",
            style = TextStyle(fontSize = 16.sp)
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
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.weight(2f))
        }
        Text(
            text = "Last Name: last name",
            style = TextStyle(fontSize = 16.sp)
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
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.weight(2f))
        }
        Text(
            text = "Email: email",
            style = TextStyle(fontSize = 16.sp)
        )
        Divider(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            color = Color.Gray
        )
    }

    Button(
        onClick = {
            // navController.navigate("${Screen.UpdateDataPatient.route}/$slug/data")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 530.dp),
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
                imageVector = Icons.Default.Face,
                contentDescription = "Home Button Icon",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Update Data",
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
    Button(
        onClick = {
            //navController.navigate("${Screen.UpdateContactPatient.route}/$slug/contact")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 590.dp),
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
                imageVector = Icons.Default.Person,
                contentDescription = "Home Button Icon",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Update Contact",
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


