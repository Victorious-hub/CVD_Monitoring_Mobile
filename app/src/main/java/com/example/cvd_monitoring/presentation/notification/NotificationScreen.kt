package com.example.cvd_monitoring.presentation.notification

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cvd_monitoring.presentation.Screen
import com.example.cvd_monitoring.presentation.notification.components.NotificationListItem
import com.example.cvd_monitoring.presentation.patients.patient_data_screen.PatientUpdateViewModel
import com.example.cvd_monitoring.presentation.patients.patient_list.components.PatientListItem
import com.example.cvd_monitoring.presentation.treatment.patient_prescription.components.PrescriptionListItem

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NotificationScreen(
    navController: NavHostController,
    viewModel: NotificationViewModel = hiltViewModel(),
    onClickBackToMain : () -> Unit,
    slug: String
) {
    LaunchedEffect(key1 = slug) {
        viewModel.getNotificationList(slug)
    }
    val state = viewModel.state.value
    if (state.notifications.isEmpty())
    {
        Text(
            text = "Your don't have any notifications yet",
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
    else{
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.notifications) { notification ->
                    NotificationListItem(
                        notification = notification,
                    )
                }
            }
        }
    }
}