package com.emdasoft.pokemonapp.data

import com.emdasoft.pokemonapp.api.model.PokeApiResponse
import com.emdasoft.pokemonapp.api.model.Pokemon
import retrofit2.Response

class Repository {

    suspend fun getPokemonList(limit: Int, offset: Int): Response<PokeApiResponse> {

        return RetrofitInstance.apiService.getPokemonList(limit, offset)

    }

    suspend fun getPokemonInfo(id: Int): Response<Pokemon> {

        return RetrofitInstance.apiService.getPokemonInfo(id)

    }

}