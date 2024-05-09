package com.example.cvd_monitoring.presentation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object CheckUser : Screen("checkUser")
    data object CurrentDoctor : Screen("currentDoctor")
    data object Notification : Screen("notification")
    data object OverView : Screen("overview")
    data object  SignIn : Screen("SignIn")
    data object  UpdateDoctor : Screen("updateDoctor")
    data object  CurrentPatient: Screen("currentPatient")
    data object  PatientCard: Screen("patientCard")
}