package com.emdasoft.pokemonapp.domain

import androidx.lifecycle.LiveData
import com.emdasoft.pokemonapp.api.model.PokeResult

class GetPokemonListUseCase(private val repository: Repository) {

    fun getPokemonList(limit: Int, offset: Int) : LiveData<List<PokeResult>> {

        return repository.getPokemonList(limit, offset)

    }

}