package com.emdasoft.pokemonapp.domain

import com.emdasoft.pokemonapp.api.model.PokeApiResponse
import retrofit2.Response

class GetPokemonListUseCase(private val repository: Repository) {

    suspend fun getPokemonList(limit: Int, offset: Int): Response<PokeApiResponse> {

        return repository.getPokemonList(limit, offset)

    }

}