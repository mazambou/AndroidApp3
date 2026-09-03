package com.example.androidapp3.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FinishScreen(
    totalLocations: Int,
    onRestartClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "🎉",
            fontSize = 70.sp
        )

        Text(
            text = "CONGRATULATIONS!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Text(
            text = "$totalLocations / $totalLocations",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp)
        )

        Text(
            text = "You completed the City Anniversary Treasure Hunt!",
            fontSize = 19.sp,
            textAlign = TextAlign.Center
        )

        Text(
            text = "You are now eligible to enter the draw for a FREE VACATION!",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                top = 20.dp,
                bottom = 30.dp
            )
        )

        Button(
            onClick = onRestartClick
        ) {

            Text("PLAY AGAIN")
        }
    }
}