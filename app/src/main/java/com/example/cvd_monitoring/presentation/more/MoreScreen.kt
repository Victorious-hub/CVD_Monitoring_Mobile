package com.example.cvd_monitoring.presentation.more

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

//@Preview(showSystemUi = true)
@Composable
fun MoreScreen(
    navController: NavController
){
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "More")
    }
    Column {
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 150.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFa5051f),
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Home Button Icon",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Doctors",
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFa5051f),
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Home Button Icon",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Analysis",
                    modifier = Modifier.weight(1f)
                )
            }

        }
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFa5051f),
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Home Button Icon",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Logout",
                    modifier = Modifier.weight(1f)
                )
            }

        }
    }


}