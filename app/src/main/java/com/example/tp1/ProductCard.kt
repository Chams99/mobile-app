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
    // Load theme from SharedPreferences
    val savedTheme = PreferencesManager.getTheme()
    val initialColor = if (savedTheme == PreferencesManager.THEME_LIGHT) {
        Color(0xFFE3F2FD) // Light blue
    } else {
        Color(0xFFFFCDD2) // Pink
    }
    
    var backgroundColor by remember { mutableStateOf(initialColor) }

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
                    // Toggle theme and save to SharedPreferences
                    if (backgroundColor == Color(0xFFE3F2FD)) {
                        backgroundColor = Color(0xFFFFCDD2)
                        PreferencesManager.setTheme(PreferencesManager.THEME_DARK)
                    } else {
                        backgroundColor = Color(0xFFE3F2FD)
                        PreferencesManager.setTheme(PreferencesManager.THEME_LIGHT)
                    }
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp)
            ) {
                Text("Toggle Theme")
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
