package com.capstone.fluentin.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DataSection() {
    Column(horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxSize(),
    ) {
        // Penguasaan bahasa
        Text(
            text = "Language :", fontSize = 20.sp, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        // keahlian bahasa
        Text(
            text = "English (expert)",
            color = Color.Gray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = "Indonesia (expert)",
            color = Color.Gray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
        Divider(
            modifier = Modifier.clip(RoundedCornerShape(1000.dp)),
            thickness = 2.dp,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Label(
            icon = Icons.Default.LocationOn,
            text = "Indonesia",
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(10.dp))
        Label(
            icon = Icons.Default.Favorite,
            text = "Playing Games",
            color = Color.Gray
        )
        // poin
        Spacer(modifier = Modifier.height(10.dp))
        Label(icon = Icons.Default.AddCircle, text = "1200")
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "hehehehhehe", fontSize = 16.sp, fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(5.dp))

        Divider(
            modifier = Modifier.clip(RoundedCornerShape(1000.dp)),
            thickness = 2.dp,
        )
    }
}