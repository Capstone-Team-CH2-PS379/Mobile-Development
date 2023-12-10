package com.capstone.fluentin.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.fluentin.R
import com.capstone.fluentin.ui.components.TextCenter
import com.capstone.fluentin.ui.theme.FluentInTheme

@Composable
fun HomeScreen( modifier: Modifier = Modifier) {
    TextCenter(
        text = "Hello Home!",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    val genres = listOf("School", "Books", "Market", "Others")

    FluentInTheme {
        Scaffold {
            Surface(
                color = Color.White,
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 20.dp)
            ) {
                Column {
                    Text(text = buildAnnotatedString {
                        append("Choose what\n\n")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        ) {
                            append("to learn today?")
                        }
                    }, fontSize = 40.sp, modifier = Modifier.padding(bottom = 25.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(bottom = 25.dp)
                    ) {
                        items(genres) { genre ->
                            Genre(genre)
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10))
                            .background(color = colorResource(id = R.color.purple_200))
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(
                                modifier = Modifier
                                    .padding(vertical = 30.dp)
                                    .padding(start = 20.dp),
                            ) {
                                Text(
                                    text = "Vocabulary",
                                    color = Color.White,
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.size(10.dp))
                                Text(
                                    text = "Check pronunciation..!",
                                    color = Color.White,
                                    fontSize = 20.sp
                                )
                                Spacer(modifier = Modifier.height(40.dp))

                                Button(
                                    modifier = Modifier
                                        .size(height = 60.dp, width = 120.dp)
                                        .clip(shape = RoundedCornerShape(20)),
                                    onClick = { /*TODO*/ },
                                    colors = ButtonDefaults.run {
                                        buttonColors(
                                            Color.White
                                        )
                                    }
                                ) {
                                    Text(text = "Start", color = Color.Black, fontSize = 18.sp)
                                    Spacer(modifier = Modifier.size(10.dp))
                                    Box(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clip(shape = CircleShape)
                                            .background(color = Color.Black),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            modifier = Modifier.padding(5.dp),
                                            imageVector = Icons.Filled.PlayArrow,
                                            contentDescription = "Arrow icon",
                                            tint = Color.White
                                        )
                                    }
                                }
                            }
                            Image(
                                painterResource(id = R.drawable.logo_fluent_in),
                                contentDescription = "avatar image",
                                modifier = Modifier.weight(1f, fill = false),
                            )
                        }
                    }

                    Text(
                        text = "Conversations",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        modifier = Modifier.padding(vertical = 30.dp)
                    )

                    LazyColumn(
                        modifier = Modifier.padding(end = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(2) {
                            Recommended()
                        }
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Genre(text: String = "School") {
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(30))
            .background(color = if (isSelected) Color.Black else Color.LightGray)
            .clickable {
                isSelected = !isSelected
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(10.dp),
            text = text,
            color = if (isSelected) Color.White else Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Recommended() {
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20))
            .background(color = colorResource(id = R.color.purple_200)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20))
                    .background(color = colorResource(id = R.color.purple_200)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
//                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Chatting",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Talk with other",
                    color = Color.DarkGray,
                    fontSize = 18.sp,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { isSelected = !isSelected }) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "icon",
                    tint = if (isSelected) Color.Yellow else Color.LightGray,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}


