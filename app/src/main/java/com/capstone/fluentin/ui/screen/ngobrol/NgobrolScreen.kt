package com.capstone.fluentin.ui.screen.ngobrol

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.capstone.fluentin.ui.components.TextCenter
import com.capstone.fluentin.ui.theme.FluentInTheme

@Composable
fun NgobrolScreen( modifier: Modifier = Modifier) {
    TextCenter(
        text = "Hello Ngobrol",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun NgobrolPreview() {
    FluentInTheme {
        NgobrolScreen()
    }
}