package com.emdasoft.pokemonapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emdasoft.pokemonapp.api.model.PokeApiResponse
import com.emdasoft.pokemonapp.api.model.Pokemon
import com.emdasoft.pokemonapp.data.Repository
import com.emdasoft.pokemonapp.domain.GetPokemonDetailsUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

class PokemonInfoViewModel : ViewModel() {


    private val repository = Repository()

    val pokemonInfo:  MutableLiveData<Response<Pokemon>> = MutableLiveData()

    fun getPokemonInfo(id: Int) {
        viewModelScope.launch {
            pokemonInfo.value = repository.getPokemonInfo(id)
        }
    }
}