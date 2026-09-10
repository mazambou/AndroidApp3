package com.example.androidapp3.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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

    val context = LocalContext.current

    val currentIndex by viewModel.currentIndex

    val business: Business = viewModel.businesses[currentIndex]

    val totalLocations = viewModel.businesses.size

    val progress =
        (currentIndex + 1).toFloat() / totalLocations.toFloat()

    val progressPercentage =
        (progress * 100).toInt()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "CITY TREASURE HUNT",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Stop ${currentIndex + 1} of $totalLocations",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "$progressPercentage%",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Text(
                    text = "CURRENT LOCATION",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = business.name,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "📍 ${business.address}",
                    fontSize = 17.sp
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Latitude: ${business.latitude}",
                    fontSize = 13.sp
                )

                Text(
                    text = "Longitude: ${business.longitude}",
                    fontSize = 13.sp
                )

                Spacer(
                    modifier = Modifier.height(25.dp)
                )

                Text(
                    text = "YOUR CLUE",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = business.clue,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
            }
        }

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        OutlinedButton(
            onClick = {

                val latitude = business.latitude
                val longitude = business.longitude

                val uri = Uri.parse(
                    "geo:0,0?q=$latitude,$longitude(${Uri.encode(business.name)})"
                )

                val mapIntent = Intent(
                    Intent.ACTION_VIEW,
                    uri
                )

                mapIntent.setPackage(
                    "com.google.android.apps.maps"
                )

                try {

                    context.startActivity(mapIntent)

                } catch (e: Exception) {

                    val fallbackIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            "https://www.google.com/maps/search/?api=1&query=$latitude,$longitude"
                        )
                    )

                    context.startActivity(fallbackIntent)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "OPEN MAP"
            )
        }

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = "Use the Android back button to return to the treasure hunt.",
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = {

                if (viewModel.isLastBusiness()) {

                    viewModel.confirmVisit(context)

                    onFinish()

                } else {

                    viewModel.confirmVisit(context)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            if (viewModel.isLastBusiness()) {

                Text(
                    text = "I'M HERE - COMPLETE HUNT"
                )

            } else {

                Text(
                    text = "I'M HERE"
                )
            }
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedButton(
            onClick = onProgressClick,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "VIEW PROGRESS"
            )
        }
    }
}