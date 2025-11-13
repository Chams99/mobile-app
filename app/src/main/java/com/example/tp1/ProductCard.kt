package com.example.tp1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProductCard(
    name: String,
    description: String,
    imageRes: Int,
    goToForm: () -> Unit,
    goToRecords: () -> Unit,
    modifier: Modifier = Modifier
) {
    var backgroundColor by remember { mutableStateOf(Color(0xFFE3F2FD)) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 32.dp)
            )

            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = Color.DarkGray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = {
                    backgroundColor =
                        if (backgroundColor == Color(0xFFE3F2FD))
                            Color(0xFFFFCDD2)
                        else
                            Color(0xFFE3F2FD)
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp)
            ) {
                Text("OK")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = goToForm,
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp)
            ) {
                Text("Aller au formulaire")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = goToRecords,
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp)
            ) {
                Text("Show Records")
            }
        }
    }
}
