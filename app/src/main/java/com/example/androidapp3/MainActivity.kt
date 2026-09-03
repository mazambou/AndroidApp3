package com.example.androidapp3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import com.example.androidapp3.navigation.AppNavigation
import com.example.androidapp3.ui.theme.AndroidApp3Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            AndroidApp3Theme {

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {

                    AppNavigation()
                }
            }
        }
    }
}