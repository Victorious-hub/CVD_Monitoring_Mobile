package com.example.cvd_monitoring.presentation.patients.patient_conclusion

import android.annotation.SuppressLint
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
import com.example.cvd_monitoring.presentation.patients.patient_conclusion.components.PatientConclusionListItemScreen
import com.example.cvd_monitoring.presentation.treatment.blood_analysis.BloodAnalysisViewModel
import com.example.cvd_monitoring.presentation.treatment.blood_analysis.components.BloodAnalysisListItem


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PatientConclusionScreen(
    navController: NavHostController,
    viewModel: PatientConclusionViewModel = hiltViewModel(),
    onClickBackToMain : () -> Unit,
    slug: String
) {
    val state = viewModel.state.value
    LaunchedEffect(key1 = slug) {
        viewModel.getPatientConclusionList(slug)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.patientConclusion.isEmpty())
        {
            Text(
                text = "Your don't have any conclusions yet",
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
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.patientConclusion) { conclusion ->
                    PatientConclusionListItemScreen(
                        conclusion = conclusion,
                    )
                }
            }
        }
    }
}