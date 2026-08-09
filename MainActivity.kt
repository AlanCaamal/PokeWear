package com.example.pokewear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import com.example.pokewear.data.CaptureState

class MainActivity : ComponentActivity() {

    private val viewModel: PokeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeWearApp(viewModel)
        }
    }
}

@Composable
fun PokeWearApp(viewModel: PokeViewModel) {
    val state by viewModel.state.collectAsState()

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            when (val currentState = state) {
                is CaptureState.Idle -> IdleContent(onAtrapar = { viewModel.atraparPokemon() })
                is CaptureState.Loading -> LoadingContent()
                is CaptureState.Success -> PokemonResultContent(
                    state = currentState,
                    onAtrapar = { viewModel.atraparPokemon() }
                )
                is CaptureState.Error -> ErrorContent(
                    message = currentState.message,
                    onRetry = { viewModel.atraparPokemon() }
                )
            }
        }
    }
}

@Composable
private fun IdleContent(onAtrapar: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "PokeWear",
            style = MaterialTheme.typography.title3,
            textAlign = TextAlign.Center
        )
        Spacer(height = 12.dp)
        Button(
            onClick = onAtrapar,
            colors = ButtonDefaults.primaryButtonColors()
        ) {
            Text(text = "Atrapar")
        }
    }
}

@Composable
private fun LoadingContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(32.dp))
        Spacer(height = 8.dp)
        Text(text = "Atrapando...", style = MaterialTheme.typography.caption1)
    }
}

@Composable
private fun PokemonResultContent(
    state: CaptureState.Success,
    onAtrapar: () -> Unit
) {
    val pokemon = state.pokemon
    val imageUrl = pokemon.sprites.other?.officialArtwork?.frontDefault
        ?: pokemon.sprites.frontDefault

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = pokemon.name,
            modifier = Modifier.size(70.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(height = 4.dp)
        Text(
            text = pokemon.name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.title3,
            textAlign = TextAlign.Center
        )
        Text(
            text = "#${pokemon.id}",
            style = MaterialTheme.typography.caption2
        )
        Spacer(height = 8.dp)
        Button(
            onClick = onAtrapar,
            colors = ButtonDefaults.secondaryButtonColors()
        ) {
            Text(text = "Otro")
        }
    }
}

@Composable
private fun ErrorContent(message: String, onRetry: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Error: $message",
            style = MaterialTheme.typography.caption1,
            textAlign = TextAlign.Center
        )
        Spacer(height = 8.dp)
        Button(onClick = onRetry) {
            Text(text = "Reintentar")
        }
    }
}

@Composable
private fun Spacer(height: androidx.compose.ui.unit.Dp) {
    Box(modifier = Modifier.height(height))
}
