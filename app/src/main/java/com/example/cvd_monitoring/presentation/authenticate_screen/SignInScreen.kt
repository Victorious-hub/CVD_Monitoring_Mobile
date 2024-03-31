package com.example.cvd_monitoring.presentation.authenticate_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cvd_monitoring.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    navController: NavHostController,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val image = painterResource(R.drawable.heart)
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    val icon = if(passwordVisibility)
        painterResource(id = R.drawable.visible1)
    else
        painterResource(id = R.drawable.invisible)
    val isFocused by remember { mutableStateOf(false) }
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
    ){
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
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sign In",
            modifier = Modifier.padding(bottom = 25.dp),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Monospace,
            )
        )
        TextField(
            value = emailState.text,
            onValueChange = { viewModel.setEmailValue(it) },
            label = {
                Text(
                    text = "Email",
                    color = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = if (isFocused) Color.Red else Color.Black,
                cursorColor = Color.Red,
            ),
        )

        TextField(
            value = passwordState.text,
            onValueChange = { viewModel.setPasswordValue(it) },
            label = {
                Text(
                    text = "Password",
                    color = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        painter = icon,
                        contentDescription = null
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = if (isFocused) Color.Red else Color.Black,
                cursorColor = Color.Red,
            ),
        )

        Button(
            onClick = {
                viewModel.authenticateUser()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFa5051f),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Sign In")
        }

    }

}