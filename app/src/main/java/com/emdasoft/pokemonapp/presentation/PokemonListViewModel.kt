package com.emdasoft.pokemonapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emdasoft.pokemonapp.api.model.PokeApiResponse
import com.emdasoft.pokemonapp.data.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class PokemonListViewModel : ViewModel() {

    private val repository = Repository()

    val pokemonList:  MutableLiveData<Response<PokeApiResponse>> = MutableLiveData()

    fun getPokemonList() {
        viewModelScope.launch {
            pokemonList.value = repository.getPokemonList(100, 0)
        }
    }
}