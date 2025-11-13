package com.example.tp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tp1.ui.theme.Tp1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tp1Theme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "productCard"
                ) {

                    composable("productCard") {
                        ProductCard(
                            name = "nom : Chams",
                            description = "Description : Étudiant de FSGF.",
                            imageRes = R.drawable.background,
                            goToForm = {
                                navController.navigate("formulaire")
                            },
                            goToRecords = {
                                navController.navigate("records")
                            }
                        )
                    }

                    composable("formulaire") {
                        FormulairePage()
                    }

                    composable("records") {
                        RecordsPage()
                    }
                }
            }
        }
    }
}
