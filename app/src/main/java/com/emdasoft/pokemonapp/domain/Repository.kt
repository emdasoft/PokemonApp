package com.emdasoft.pokemonapp.domain

import androidx.lifecycle.LiveData
import com.emdasoft.pokemonapp.api.model.PokeResult
import com.emdasoft.pokemonapp.api.model.Pokemon

interface Repository {

    fun getPokemonList(limit: Int, offset: Int) : LiveData<List<PokeResult>>

    fun getPokemonDetails(id: Int)

}