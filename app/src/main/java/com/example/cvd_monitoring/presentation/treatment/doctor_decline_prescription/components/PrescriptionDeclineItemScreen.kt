package com.example.cvd_monitoring.presentation.treatment.doctor_decline_prescription.components

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
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cvd_monitoring.domain.model.treatment.Prescription

@Composable
fun PrescriptionDeclineListItem(
    prescription: Prescription,
    onPrescriptionDecline : (Prescription) -> Unit
) {
    if (!prescription.isDeclined)
    {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Card(
                modifier = Modifier
                    .padding(8.dp, 4.dp)
                    .fillMaxWidth()
                    .height(260.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Row(
                    Modifier
                        .padding(4.dp)
                        .fillMaxSize()
                ) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxHeight()
                            .weight(0.6f)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .fillMaxWidth().padding(bottom = 3.dp)
                        ) {
                            Text(
                                text = "Medication",
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                        }
                        Text(
                            text = prescription.medication.name ?: "No info",
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        )
                        Divider(
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                            color = Color.Gray
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .fillMaxWidth().padding(bottom = 3.dp)
                        ) {
                            Text(
                                text = "Dosage per day",
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                        }
                        Text(
                            text = prescription.dosage ?: "No info",
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        )
                        Divider(
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                            color = Color.Gray
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .fillMaxWidth().padding(bottom = 3.dp)
                        ) {
                            Text(
                                text = "Start date",
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                        }
                        Text(
                            text = prescription.startDate ?: "No info",
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        )
                        Divider(
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                            color = Color.Gray
                        )


                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .fillMaxWidth().padding(bottom = 3.dp)
                        ) {
                            Text(
                                text = "End date",
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                        }
                        Text(
                            text = prescription.endDate ?: "No info",
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        )
                        Divider(
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                            color = Color.Gray
                        )

                        Button(
                            onClick = {
                                onPrescriptionDecline(prescription)
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
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Home Button Icon",
                                    tint = Color.Black
                                )
                                Text(
                                    text = "Decline Prescription",
                                    color = Color.Black
                                )
                            }
                        }

                    }
                }
            }

        }
    }

}