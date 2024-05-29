package com.example.cvd_monitoring.presentation.doctors.doctor_profile_screen

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
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
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.cvd_monitoring.R
import com.example.cvd_monitoring.presentation.Screen
import com.example.cvd_monitoring.utils.Constants.BASE_IP

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DoctorProfileScreen(
    navController: NavHostController,
    viewModel: DoctorProfileViewModel = hiltViewModel(),
    slug: String,
    onUpdateContact : (String) -> Unit
) {
    LaunchedEffect(key1 = slug) {
        viewModel.getCurrentDoctor(slug)
    }
    val state = viewModel.state.value
    val image =
        rememberImagePainter(data = "http://${BASE_IP}:8000${state.doctor?.profileImage}")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.25f)
            .background(
                color = Color(0xFFa5051f),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 80.dp,
                    bottomEnd = 80.dp
                )
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
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column(
        ) {
            Spacer(modifier = Modifier.height(110.dp))
            Text(
                text = "Account Info",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Monospace,
                    color = Color.Black
                )
            )

        }

        Column(
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 5.dp)
            ) {
                Text(
                    text = "First Name",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black) // Set text color to black
                )
                Spacer(modifier = Modifier
                    .weight(0.5f))
            }
            state.doctor?.user?.let {
                Text(
                    text = it.firstName,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(fontSize = 16.sp, color = Color.Black) // Set text color to black
                )
            }
            Divider(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp),
                color = Color.Gray
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 5.dp)
            ){
                Text(
                    text = "Last Name",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black) // Set text color to black
                )
                Spacer(modifier = Modifier.weight(2f))
            }
            state.doctor?.user?.let {
                Text(
                    text = it.lastName,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(fontSize = 16.sp, color = Color.Black) // Set text color to black
                )
            }
            Divider(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                color = Color.Gray
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 5.dp)
            ){
                Text(
                    text = "Email",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black) // Set text color to black
                )
                Spacer(modifier = Modifier.weight(2f))
            }
            state.doctor?.user?.let {
                Text(
                    text = it.email,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(fontSize = 16.sp, color = Color.Black) // Set text color to black
                )
            }
            Divider(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                color = Color.Gray
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 5.dp)
            ){
                Text(
                    text = "Experience",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black) // Set text color to black
                )
                Spacer(modifier = Modifier.weight(2f))
            }
            state.doctor?.let {
                Text(
                    text = "${it.experience.toString()} year(s)",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(fontSize = 16.sp, color = Color.Black) // Set text color to black
                )
            }
            Divider(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                color = Color.Gray
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 5.dp)
            ){
                Text(
                    text = "Description",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black) // Set text color to black
                )
                Spacer(modifier = Modifier.weight(2f))
            }
            state.doctor?.let {
                it.description?.let { it1 ->
                    Text(
                        text = it1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(fontSize = 16.sp, color = Color.Black) // Set text color to black
                    )
                }
            }
            Divider(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                color = Color.Gray
            )
        }

        Column(
        ) {
            Button(
                onClick = {
                    onUpdateContact(slug)
                    //navController.navigate("${Screen.UpdateDoctor.route}/$slug/contact")
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
                        imageVector = Icons.Default.Face,
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
    }

}