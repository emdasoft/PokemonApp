package com.emdasoft.pokemonapp.domain

import com.emdasoft.pokemonapp.domain.model.PokeApiResponse
import com.emdasoft.pokemonapp.domain.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class PokemonApiService {
    interface PokeApiService {
        @GET("pokemon/{id}")
        fun getPokemonInfo(@Path("id") id: Int): Call<Pokemon>

        @GET("pokemon")
        fun getPokemonList(
            @Query("limit") limit: Int,
            @Query("offset") offset: Int
        ): Call<PokeApiResponse>
    }
}