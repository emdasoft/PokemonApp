package com.emdasoft.pokemonapp.data

import com.emdasoft.pokemonapp.api.model.PokeApiResponse
import com.emdasoft.pokemonapp.api.model.Pokemon
import retrofit2.Response

object RepositoryImpl : com.emdasoft.pokemonapp.domain.Repository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Response<PokeApiResponse> {

        return RetrofitInstance.apiService.getPokemonList(limit, offset)

    }

    override suspend fun getPokemonInfo(id: Int): Response<Pokemon> {

        return RetrofitInstance.apiService.getPokemonInfo(id)

    }
}