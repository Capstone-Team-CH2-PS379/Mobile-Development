package com.capstone.fluentin.ui.screen.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.capstone.fluentin.ui.components.TextCenter
import com.capstone.fluentin.ui.theme.FluentInTheme

@Composable
fun ProfileScreen( modifier: Modifier = Modifier) {
    TextCenter(
        text = "Hello Profile",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    FluentInTheme {
        ProfileScreen()
    }
}