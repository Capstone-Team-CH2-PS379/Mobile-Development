package com.capstone.fluentin.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.fluentin.R



@Preview(showSystemUi = true)
@Composable
fun Belajar() {
    var selectedCategory by remember { mutableStateOf("Category") }
    var isDropdownVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.ucapkan),
            fontSize = 20.sp, // Ganti dengan ukuran yang Anda inginkan
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth() // Ini untuk mengatur agar teks di tengah
        )

        Box(
            modifier = Modifier
                .width(360.dp)
                .height(487.dp)
                .padding(16.dp)
        ) {
            // Content inside the box
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Gray)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Text views
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "English Text",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 30.sp,
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Indonesian Text",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 20.sp,
                    )
                )
            }
        }
        // Icons at the bottom-right corner
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp,start = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = { /* TODO: Handle refresh icon click */ },
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 16.dp)
            ) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
            }
            IconButton(
                onClick = { /* TODO: Handle speaker icon click */ },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_volume_up_24),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, start = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
MicButton(onClick = { /*TODO*/ })
        }
    }

}


@Composable
fun MicButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(72.dp)
            .clip(MaterialTheme.shapes.small)
            .background(
                color = Color(0xFFE91E63),
                shape = MaterialTheme.shapes.small.copy(
                    topEnd = CornerSize(8.dp),
                    topStart = CornerSize(8.dp),
                    bottomEnd = CornerSize(8.dp),
                    bottomStart = CornerSize(8.dp)
                )
            )
            .clickable { onClick.invoke() }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_mic_24),
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = Color.White
        )
    }
}