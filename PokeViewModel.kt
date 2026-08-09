package com.example.pokewear

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokewear.data.CaptureState
import com.example.pokewear.data.PokeApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class PokeViewModel : ViewModel() {

    private val api = PokeApiService.create()

    private val _state = MutableStateFlow<CaptureState>(CaptureState.Idle)
    val state: StateFlow<CaptureState> = _state

    /**
     * Genera un ID aleatorio (1..MAX_POKEMON_ID) y pide ese Pokémon a la PokeAPI.
     * Esto es lo que se dispara al presionar el botón "Atrapar".
     */
    fun atraparPokemon() {
        _state.value = CaptureState.Loading
        viewModelScope.launch {
            try {
                val randomId = Random.nextInt(1, PokeApiService.MAX_POKEMON_ID + 1)
                val pokemon = api.getPokemon(randomId.toString())
                _state.value = CaptureState.Success(pokemon)
            } catch (e: Exception) {
                _state.value = CaptureState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}
