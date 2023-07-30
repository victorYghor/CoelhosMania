package com.example.restapi

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
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.ImagePainter.State.Empty.painter
import coil.compose.rememberImagePainter

@Composable
fun RabbitScreen(
) {
    val viewModel: MainViewModel = hiltViewModel()
    val rabbit = viewModel.state.value.rabbit
    val isLoading = viewModel.state.value.isLoading

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(32.dp)
    ) {
        if(isLoading) {
            CircularProgressIndicator()
        }
        rabbit?.let {
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
            Button(onClick = { viewModel.getRandomRabbit() }) {
                Text(text = "new rabbit")
            }
            Spacer(modifier = Modifier.height(16.dp))
            if(isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}