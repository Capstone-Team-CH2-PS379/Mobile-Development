package com.capstone.fluentin.ui.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.capstone.fluentin.R
import com.capstone.fluentin.ui.screen.home.HomeScreen
import com.capstone.fluentin.ui.theme.FluentInTheme

@Composable
fun WelcomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.welcome),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.intro_message),
                    fontSize = 20.sp,
                    modifier = Modifier.alpha(.3f)
                )
            }
            Column(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    painter = painterResource(id = R.drawable.logo_fluent_in),
                    contentDescription = stringResource(id = R.string.logo_app),
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = { /*TODO*/
                              navController.navigate("login")
                              },
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .absolutePadding(bottom = 5.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color(202, 107, 229, 255),
//                         backgroundColor = Color(255, 125, 0),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = stringResource(R.string.go_to_sign_in))
                }
                OutlinedButton(
                    onClick = { /*TODO*/
                              navController.navigate("signUp")
                              },
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .absolutePadding(top = 5.dp)
                ) {
                    Text(
                        text = stringResource(R.string.no_account_yet_msg),
                        color = Color(red = 202, green = 107, blue = 229, alpha = 255)
                    )
                }

            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun WelcomePreview() {
//    FluentInTheme {
//        WelcomeScreen(navController)
//    }
//}