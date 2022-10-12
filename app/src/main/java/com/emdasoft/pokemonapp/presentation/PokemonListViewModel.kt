package com.emdasoft.pokemonapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emdasoft.pokemonapp.api.model.PokeApiResponse
import com.emdasoft.pokemonapp.api.model.PokeResult
import com.emdasoft.pokemonapp.data.RepositoryImpl
import com.emdasoft.pokemonapp.domain.GetPokemonListUseCase


class PokemonListViewModel : ViewModel() {

    private val repository = RepositoryImpl()

    private val getPokemonListUseCase = GetPokemonListUseCase(repository)

//    val pokemonList = MutableLiveData<List<PokeResult>>()
    val pokemonList = getPokemonListUseCase.getPokemonList(200, 0)

//    fun getPokemonList(){
//        getPokemonListUseCase.getPokemonList(1154,0)
//    }
}