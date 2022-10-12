package com.emdasoft.pokemonapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emdasoft.pokemonapp.api.model.Pokemon
import com.emdasoft.pokemonapp.data.RepositoryImpl
import com.emdasoft.pokemonapp.domain.GetPokemonDetailsUseCase

class PokemonInfoViewModel : ViewModel() {

    private val repository = RepositoryImpl()

    private val getPokemonDetailsUseCase = GetPokemonDetailsUseCase(repository)

    val pokemonInfo = MutableLiveData<Pokemon>()

    fun getPokemonInfo(id: Int) {
        val pokemon = getPokemonDetailsUseCase.getPokemonDetails(id)
        pokemonInfo.value = pokemon
    }

}