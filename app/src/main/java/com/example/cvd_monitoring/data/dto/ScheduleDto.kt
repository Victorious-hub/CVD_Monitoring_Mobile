package com.example.cvd_monitoring.data.dto


import com.example.cvd_monitoring.domain.model.analysis.Schedule
import com.example.cvd_monitoring.domain.model.users.DoctorUser
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class ScheduleDto(
    @SerializedName("available_time")
    val availableTime: Map<String, List<String>>,
    @SerializedName("doctor")
    val doctor: DoctorScheduleDto
)

fun ScheduleDto.toSchedule(): Schedule {
    return Schedule(
        availableTime = availableTime,
        doctor = doctor,
    )
}


