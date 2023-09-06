package com.example.restapi

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RabbitScreen(
) {
    val mainViewModel: MainViewModel = hiltViewModel()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(32.dp)
    ) {
        Log.e("SpecificProblem", "creating the UI")
        val rabbit = mainViewModel.state.value.rabbit
        val isLoading = mainViewModel.state.value.isLoading
        if(isLoading) {

            CircularProgressIndicator()
        }
        rabbit?.let {
            Log.e("SpecificProblem", "put the rabbit on the screen")
            Image(
                painter = rememberImagePainter(
                    data = rabbit.imageUrl,
                    builder = { crossfade(true) }),
                "Beautiful rabbit"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = rabbit.name, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = rabbit.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { mainViewModel.getRandomRabbit() }) {
                Text(text = "new rabbit")
            }
            Spacer(modifier = Modifier.height(16.dp))
            if(isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}