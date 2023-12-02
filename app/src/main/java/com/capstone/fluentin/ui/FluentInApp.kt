package com.capstone.fluentin.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.capstone.fluentin.R
import com.capstone.fluentin.ui.navigation.NavigationItem
import com.capstone.fluentin.ui.navigation.Screen
import com.capstone.fluentin.ui.screen.home.HomeScreen
import com.capstone.fluentin.ui.screen.leaderboard.LeaderboardScreen
import com.capstone.fluentin.ui.screen.ngobrol.NgobrolScreen
import com.capstone.fluentin.ui.screen.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FluentInApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Home.route){
                HomeScreen()
            }
            composable(Screen.Ngobrol.route){
                NgobrolScreen()
            }
            composable(Screen.Leaderboard.route){
                LeaderboardScreen()
            }
            composable(Screen.Profile.route){
                ProfileScreen()
            }

        }
    }
}
@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = painterResource(R.drawable.ic_home_filled_24),
                screen = Screen.Home,
                contentDescription = stringResource(R.string.menu_home)
            ),
            NavigationItem(
                title = stringResource(R.string.menu_ngobrol),
                icon = painterResource(R.drawable.ic_podcasts_24),
                screen = Screen.Ngobrol,
                contentDescription = stringResource(R.string.menu_ngobrol)
            ),
            NavigationItem(
                title = stringResource(R.string.menu_leaderboard),
                icon = painterResource(R.drawable.ic_leaderboard_24),
                screen = Screen.Leaderboard,
                contentDescription = stringResource(R.string.menu_leaderboard)
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = painterResource(R.drawable.ic_person_24),
                screen = Screen.Profile,
                contentDescription = stringResource(R.string.menu_profile)
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}