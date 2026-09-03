package com.example.androidapp3.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidapp3.model.Business
import com.example.androidapp3.viewmodel.TreasureHuntViewModel

@Composable
fun HuntScreen(
    viewModel: TreasureHuntViewModel,
    onProgressClick: () -> Unit,
    onFinish: () -> Unit
) {

    val currentIndex by viewModel.currentIndex

    val business: Business = viewModel.businesses[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "TREASURE HUNT",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Stop ${currentIndex + 1} of ${viewModel.businesses.size}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(25.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(25.dp)
            ) {

                Text(
                    text = business.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "📍 ${business.address}",
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = "YOUR CLUE",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = business.clue,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                if (viewModel.isLastBusiness()) {
                    onFinish()
                } else {
                    viewModel.nextBusiness()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            if (viewModel.isLastBusiness()) {
                Text("COMPLETE TREASURE HUNT")
            } else {
                Text("NEXT LOCATION")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = onProgressClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("VIEW PROGRESS")
        }
    }
}