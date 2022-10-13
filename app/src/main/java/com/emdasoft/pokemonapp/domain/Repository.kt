package com.emdasoft.pokemonapp.domain

import androidx.lifecycle.LiveData
import com.emdasoft.pokemonapp.api.model.PokeApiResponse
import com.emdasoft.pokemonapp.api.model.PokeResult
import com.emdasoft.pokemonapp.api.model.Pokemon
import retrofit2.Response

interface Repository {

    suspend fun getPokemonList(limit: Int, offset: Int): Response<PokeApiResponse>

    suspend fun getPokemonInfo(id: Int): Response<Pokemon>

}