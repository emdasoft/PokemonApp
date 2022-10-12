package com.emdasoft.pokemonapp.domain

import androidx.lifecycle.LiveData
import com.emdasoft.pokemonapp.api.model.Pokemon

class GetPokemonDetailsUseCase(private val repository: Repository) {

    fun getPokemonDetails(id: Int): Pokemon {

        return repository.getPokemonDetails(id)
    }

}