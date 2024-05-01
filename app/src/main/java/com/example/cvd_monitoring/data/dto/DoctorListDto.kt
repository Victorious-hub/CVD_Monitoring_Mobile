package com.example.cvd_monitoring.data.dto


import com.example.cvd_monitoring.domain.model.patients.DoctorList
import com.google.gson.annotations.SerializedName

data class DoctorListDto(
    @SerializedName("profile_image")
    val profileImage: String,
    @SerializedName("user")
    val user: DoctorUserDto
)

fun DoctorListDto.toDoctorList(): DoctorList {
    return DoctorList(
        profileImage = profileImage,
        user = user.toDoctorUser(),
    )
}