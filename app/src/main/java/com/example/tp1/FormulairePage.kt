package com.example.tp1

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormulairePage() {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = remember { DB.get(context) }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Male") }
    var isAgreed by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf("France") }
    val countries = listOf("Tunisia", "France", "USA", "Canada")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Formulaire", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nom") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Genre:")
        Row {
            RadioButton(
                selected = gender == "Male",
                onClick = { gender = "Male" }
            )
            Text("Male")

            Spacer(Modifier.width(16.dp))

            RadioButton(
                selected = gender == "Female",
                onClick = { gender = "Female" }
            )
            Text("Female")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = isAgreed, onCheckedChange = { isAgreed = it })
            Text("J'accepte les termes")
        }

        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedCountry,
                onValueChange = {},
                readOnly = true,
                label = { Text("Pays") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                countries.forEach { country ->
                    DropdownMenuItem(
                        text = { Text(country) },
                        onClick = {
                            selectedCountry = country
                            expanded = false
                        }
                    )
                }
            }
        }

        Button(
            onClick = {
                // Validation
                if (name.isBlank()) {
                    Toast.makeText(context, "Veuillez entrer un nom", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(context, "Veuillez entrer un email valide", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (!isAgreed) {
                    Toast.makeText(context, "Veuillez accepter les termes", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                scope.launch {
                    try {
                        db.dao().insert(
                            UserForm(
                                name = name.trim(),
                                email = email.trim(),
                                gender = gender,
                                country = selectedCountry,
                                agreed = isAgreed
                            )
                        )
                        // Success feedback
                        Toast.makeText(context, "Formulaire enregistré avec succès!", Toast.LENGTH_SHORT).show()
                        
                        // Reset form
                        name = ""
                        email = ""
                        gender = "Male"
                        selectedCountry = "France"
                        isAgreed = false
                    } catch (e: Exception) {
                        Toast.makeText(context, "Erreur lors de l'enregistrement: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}
