package com.capstone.fluentin.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.capstone.fluentin.ui.components.TextCenter
import com.capstone.fluentin.ui.theme.FluentInTheme

@Composable
fun HomeScreen( modifier: Modifier = Modifier) {
    TextCenter(
        text = "Hello Home!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    FluentInTheme {
       HomeScreen()
    }
}