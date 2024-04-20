package com.example.cvd_monitoring.presentation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object CheckUser : Screen("checkUser")
    data object CurrentDoctor : Screen("currentDoctor")
    data object Notification : Screen("notification")
    data object More : Screen("more")
    data object PatientDetails : Screen("patientDetails")
    data object SignUp : Screen("signUp")
    data object  SignIn : Screen("SignIn")
    data object  PatientBloodList : Screen("patientBlood")
    data object  PatientCholesterolList : Screen("patientCholesterol")
    data object  PatientPrescriptionList : Screen("patientPrescription")
    data object  UpdateDataPatient : Screen("updateData")
    data object  UpdateContactPatient : Screen("updateContactPatient")
    data object  UpdateContactDoctor : Screen("UpdateContactDoctor")
    data object  PatientList : Screen("patientList")
    data object  CurrentPatient: Screen("currentPatient")
    data object  PatientCard: Screen("patientCard")
}