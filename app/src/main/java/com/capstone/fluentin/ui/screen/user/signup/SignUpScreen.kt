package com.capstone.fluentin.ui.screen.user.signup

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.capstone.fluentin.R
import com.capstone.fluentin.data.preferences.UserPreferences
import com.capstone.fluentin.data.repository.UserRepository
import com.capstone.fluentin.ui.components.CustomTextField
import com.capstone.fluentin.ui.components.PasswordTextField
import com.capstone.fluentin.ui.screen.user.UserViewModel
import com.capstone.fluentin.ui.theme.FluentInTheme

@Composable
fun SignUpScreen(navController: NavController, viewModel: UserViewModel) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pass by remember{ mutableStateOf("")}
    var confirmPass by remember{ mutableStateOf("")}
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        IconButton(onClick = { /*TODO*/
                             navController.navigate("welcome")
                             },
            modifier = Modifier
                .align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back_button)
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(R.string.register),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )

        // First Name and Last Name Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            // First Name
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.first_name_label),
                    fontWeight = FontWeight.Bold
                )
                CustomTextField(
                    value = firstName,
                    onValueChange = { newText ->
                        firstName = newText
                    },
                    placeholder = stringResource(R.string.first_name)
                )
            }

            // Last Name
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.last_name_label),
                    fontWeight = FontWeight.Bold
                )
                CustomTextField(
                    value = lastName,
                    onValueChange ={newText ->
                        lastName = newText} ,
                    placeholder = stringResource(R.string.last_name)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    text = stringResource(R.string.e_mail),
                    fontWeight = FontWeight.Bold
                )
                CustomTextField(
                    value = email,
                    onValueChange = { newText ->
                        email = newText
                    },
                    placeholder = stringResource(R.string.sample_email),
                    keyboardType = KeyboardType.Email,

                )
            }
        }

        Row {
            // Password
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.password),
                    fontWeight = FontWeight.Bold
                )
                PasswordTextField(
                    value = pass,
                    onValueChange = { newText ->
                        pass = newText
                    },
                    placeholder = stringResource(R.string.enter_your_password),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            painterResource(R.drawable.ic_visibility_24)
                        else painterResource(R.drawable.ic_visibility_off_24)

                        IconButton(onClick = {
                            passwordVisible = !passwordVisible
                        }) {
                            Icon(painter = image, contentDescription = "")
                        }
                    }
                )
            }
        }

        Row {
            // Password
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.confirm_pasword),
                    fontWeight = FontWeight.Bold
                )
                PasswordTextField(
                    value = confirmPass,
                    onValueChange = { newText ->
                        confirmPass = newText
                    },
                    placeholder = stringResource(R.string.enter_your_password),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            painterResource(R.drawable.ic_visibility_24)
                        else painterResource(R.drawable.ic_visibility_off_24)

                        IconButton(onClick = {
                            passwordVisible = !passwordVisible
                        }) {
                            Icon(painter = image, contentDescription = "")
                        }
                    }
                )
            }
        }

        Button(
            onClick = {
                if (
                    firstName.isNotEmpty() &&
                    lastName.isNotEmpty() &&
                    email.isNotEmpty() &&
                    pass.isNotEmpty() &&
                    confirmPass.isNotEmpty() &&
                    pass == confirmPass
                ) {
                    // Lakukan aksi yang sesuai di sini, misalnya, panggil fungsi registerUser
                    // viewModel.registerUser(firstName, lastName, email, pass)
                    viewModel.userRegister(firstName, lastName, email, pass)
                } else {
                    Log.d("SignUpScreen", "Validation failed:")
                }
            },
            Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                Color(202, 107, 229, 255),
                contentColor = Color.White
            )
        ) {
            Text(text = stringResource(R.string.sign_up))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SignUpPreview() {
//    FluentInTheme {
//        val viewModel = UserViewModel()
//        SignUpScreen(navController = rememberNavController(), viewModel = viewModel)
//    }
//}