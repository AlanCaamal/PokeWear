package com.example.pokewear.data

import com.google.gson.annotations.SerializedName

/**
 * Modelo simplificado de la respuesta de https://pokeapi.co/api/v2/pokemon/{id}
 * Solo mapeamos lo que necesitamos: nombre y sprite.
 */
data class PokemonResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val sprites: Sprites
)

data class Sprites(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("other") val other: OtherSprites?
)

data class OtherSprites(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork?
)

data class OfficialArtwork(
    @SerializedName("front_default") val frontDefault: String?
)

/**
 * Estado de UI para la pantalla de captura.
 */
sealed class CaptureState {
    data object Idle : CaptureState()
    data object Loading : CaptureState()
    data class Success(val pokemon: PokemonResponse) : CaptureState()
    data class Error(val message: String) : CaptureState()
}
