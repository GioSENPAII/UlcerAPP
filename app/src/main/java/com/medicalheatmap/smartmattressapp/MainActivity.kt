package com.medicalheatmap.smartmattressapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.medicalheatmap.smartmattressapp.ui.theme.SmartMattressAppTheme
import com.medicalheatmap.smartmattressapp.screens.*
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartMattressAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SmartMattressApp()
                }
            }
        }
    }
}

@Composable
fun SmartMattressApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                onNavigateToConnection = {
                    navController.navigate("connection") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }
        composable("connection") {
            ConnectionScreen(
                onConnect = {
                    navController.navigate("loading")
                }
            )
        }
        composable("loading") {
            LoadingScreen(
                onLoadingComplete = {
                    navController.navigate("dashboard") {
                        popUpTo("loading") { inclusive = true }
                    }
                }
            )
        }
        composable("dashboard") {
            DashboardScreen()
        }
    }
}