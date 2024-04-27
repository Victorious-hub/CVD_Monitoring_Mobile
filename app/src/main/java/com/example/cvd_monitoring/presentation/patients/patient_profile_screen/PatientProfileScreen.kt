package com.example.cvd_monitoring.presentation.patients.patient_profile_screen

import android.annotation.SuppressLint
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
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cvd_monitoring.R
import com.example.cvd_monitoring.presentation.Screen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PatientProfileScreen(
    navController: NavController,
    viewModel: PatientProfileViewModel = hiltViewModel(),

    slug: String,
) {
    LaunchedEffect(key1 = slug) {
        viewModel.getCurrentUser(slug)
    }
    val state = viewModel.state.value
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
        state.patient?.user?.let {
            Text(
                text = it.first_name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(fontSize = 16.sp)
            )
        }
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
        state.patient?.user?.let {
            Text(
                text = it.last_name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(fontSize = 16.sp)
            )
        }
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
        state.patient?.user?.let {
            Text(
                text = it.email,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(fontSize = 16.sp)
            )
        }
        Divider(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            color = Color.Gray
        )
    }

    Button(
        onClick = {
            navController.navigate("${Screen.UpdateDataPatient.route}/$slug/data")
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
            navController.navigate("${Screen.UpdateContactPatient.route}/$slug/contact")
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