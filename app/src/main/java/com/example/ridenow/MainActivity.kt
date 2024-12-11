package com.example.ridenow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.ridenow.navigation.RideNowNavHost
import com.example.ridenow.ui.theme.RideNowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RideNowTheme {
                val navController = rememberNavController()
                RideNowNavHost(navController = navController)
            }
        }
    }
}

