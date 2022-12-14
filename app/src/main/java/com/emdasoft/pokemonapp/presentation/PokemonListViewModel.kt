package com.emdasoft.pokemonapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emdasoft.pokemonapp.api.model.PokeApiResponse
import com.emdasoft.pokemonapp.data.RepositoryImpl
import com.emdasoft.pokemonapp.domain.GetPokemonListUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

class PokemonListViewModel(private val process: ShowProgress) : ViewModel() {

    private val repository = RepositoryImpl
    private val getPokemonListUseCase = GetPokemonListUseCase(repository)

    val pokemonList:  MutableLiveData<Response<PokeApiResponse>> = MutableLiveData()

    fun getPokemonList() {
        process.showProgress(true)
        viewModelScope.launch {
//            pokemonList.value = getPokemonListUseCase.getPokemonList(100, 0)
            pokemonList.postValue(getPokemonListUseCase.getPokemonList(100, 0))
            process.showProgress(false)
        }
    }

}