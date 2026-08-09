package com.example.pokewear.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {

    @GET("pokemon/{idOrName}")
    suspend fun getPokemon(@Path("idOrName") idOrName: String): PokemonResponse

    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"

        // Rango de IDs válidos de la PokeAPI (Pokédex nacional hasta la fecha).
        // Puedes ajustar este número conforme salgan nuevas generaciones.
        const val MAX_POKEMON_ID = 1025

        fun create(): PokeApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(PokeApiService::class.java)
        }
    }
}
