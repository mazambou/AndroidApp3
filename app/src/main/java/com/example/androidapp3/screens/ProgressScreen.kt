package com.example.androidapp3.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidapp3.model.Business
import com.example.androidapp3.viewmodel.TreasureHuntViewModel

@Composable
fun ProgressScreen(
    viewModel: TreasureHuntViewModel,
    onBackClick: () -> Unit,
) {

    val currentIndex by viewModel.currentIndex

    val totalBusinesses = viewModel.businesses.size

    val progress = if (totalBusinesses > 0) {
        (currentIndex + 1).toFloat() / totalBusinesses.toFloat()
    } else {
        0f
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "YOUR PROGRESS",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "${currentIndex + 1} / $totalBusinesses locations visited",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        LinearProgressIndicator(
            progress = {
                progress
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            itemsIndexed(viewModel.businesses) { index, business ->

                val locationText = when {

                    index < currentIndex -> {
                        "✅ ${business.name}"
                    }

                    index == currentIndex -> {
                        "📍 ${business.name}"
                    }

                    else -> {
                        "🔒 Location ${business.id}"
                    }
                }

                Text(
                    text = locationText,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(
                        vertical = 10.dp
                    )
                )
            }
        }

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "BACK TO TREASURE HUNT"
            )
        }
    }
}