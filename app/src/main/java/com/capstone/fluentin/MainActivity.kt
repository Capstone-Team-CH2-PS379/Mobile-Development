package com.capstone.fluentin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.capstone.fluentin.data.repository.UserRepository
import com.capstone.fluentin.ui.FluentInApp
import com.capstone.fluentin.ui.screen.home.HomeScreen
import com.capstone.fluentin.ui.screen.user.UserViewModel
import com.capstone.fluentin.ui.theme.FluentInTheme
import com.capstone.fluentin.ui.screen.user.login.LoginScreen
import com.capstone.fluentin.ui.screen.user.signup.SignUpScreen
import com.capstone.fluentin.ui.screen.welcome.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            WindowCompat.setDecorFitsSystemWindows(window,false)
            CompositionLocalProvider {
//                LocalScaffoldState provides rememberScaffoldState()
            }
            FluentInTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    FluentInApp()
//                }
                NavHost(navController = navController, startDestination = "welcome") {
                    composable("welcome") { WelcomeScreen(navController) }
                    composable("login") { LoginScreen(navController) }
                    composable("signUp") {
                        val viewModel: UserViewModel = viewModel()
                        SignUpScreen(navController, viewModel)
                    }
                    composable("home") { HomeScreen(navController) }
                    // Add more destinations as needed
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FluentInTheme {
        Greeting("Android")
    }
}