package com.capstone.fluentin.ui.screen.leaderboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import com.capstone.fluentin.ui.components.TextCenter
import com.capstone.fluentin.ui.theme.FluentInTheme

@Composable
fun LeaderboardScreen( modifier: Modifier = Modifier) {
    TextCenter(
        text = "Hello Leaderboard",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun LeaderboardPreview() {
    FluentInTheme {
        LeaderboardScreen()
    }
}