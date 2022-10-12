package com.emdasoft.pokemonapp.presentation

import androidx.lifecycle.ViewModel
import com.emdasoft.pokemonapp.data.RepositoryImpl
import com.emdasoft.pokemonapp.domain.GetPokemonListUseCase

class PokemonListViewModel : ViewModel() {

    private val repository = RepositoryImpl()

    private val getPokemonListUseCase = GetPokemonListUseCase(repository)

    val pokemonList = getPokemonListUseCase.getPokemonList(200, 0)

}