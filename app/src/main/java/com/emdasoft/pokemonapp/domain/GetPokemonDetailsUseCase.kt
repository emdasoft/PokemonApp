package com.emdasoft.pokemonapp.domain

import com.emdasoft.pokemonapp.api.model.Pokemon
import retrofit2.Response

class GetPokemonDetailsUseCase(private val repository: Repository) {

    suspend fun getPokemonInfo(id: Int): Response<Pokemon> {

        return repository.getPokemonInfo(id)
    }

}