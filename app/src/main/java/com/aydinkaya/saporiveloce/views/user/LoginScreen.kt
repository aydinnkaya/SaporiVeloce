package com.aydinkaya.saporiveloce.views.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aydinkaya.saporiveloce.viewmodel.UserViewModel
import com.aydinkaya.saporiveloce.viewmodel.User

@Composable
fun LoginScreen(
    onLoginSuccess: (User) -> Unit,
    navController: NavController,
    userViewModel: UserViewModel
) {
    var email by remember { mutableStateOf(userViewModel.getEmail() ?: "") }
    var password by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf("") }

    val loggedInUser by userViewModel.loggedInUser.observeAsState()

    LaunchedEffect(loggedInUser) {
        loggedInUser?.let {
            onLoginSuccess(it)
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderView(
            title = "Login",
            subtitle = "Spori Voloce",
            angle = 15f,
            backgroundColor = Color(0xFFF50053)
        )

        Spacer(modifier = Modifier.height(180.dp))

        if (loginError.isNotEmpty()) {
            Text(
                text = loginError,
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter your email") },
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
                .padding(vertical = 8.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Gray.copy(alpha = 0.1f)),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Email
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter your password") },
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
                .padding(vertical = 8.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Gray.copy(alpha = 0.1f)),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { /* Forgot Password Logic */ }) {
                Text(
                    text = "Forgot Password?",
                    color = Color(0xFF007AFF)
                )
            }
        }

        Button(
            onClick = {
                userViewModel.loginUser(email, password) { success, error ->
                    if (success) {
                        userViewModel.setEmail(email) // Optional: Save the email for future reference
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        loginError = error ?: "Unknown error"
                    }
                }
            },
            modifier = Modifier
                .padding(8.dp)
                .width(340.dp)
                .height(44.dp)
                .clip(RoundedCornerShape(20.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF55D65A))
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Divider(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(),
            thickness = 1.dp,
            color = Color.Gray
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "New around here?")
            TextButton(
                onClick = {
                    navController.navigate("register")
                },
            ) {
                Text("Create An Account", color = Color(0xFF007AFF))
            }
        }
    }
}
