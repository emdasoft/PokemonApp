package com.emdasoft.pokemonapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emdasoft.pokemonapp.api.model.Pokemon
import com.emdasoft.pokemonapp.data.RepositoryImpl
import com.emdasoft.pokemonapp.domain.GetPokemonDetailsUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

class PokemonInfoViewModel(private val progress: ShowProgress) : ViewModel() {


    private val repository = RepositoryImpl

    private val getPokemonDetailsUseCase = GetPokemonDetailsUseCase(repository)

    val pokemonInfo:  MutableLiveData<Response<Pokemon>> = MutableLiveData()

    fun getPokemonInfo(id: Int) {
        progress.showProgress(true)
        viewModelScope.launch {
//            pokemonInfo.value = getPokemonDetailsUseCase.getPokemonInfo(id)
            pokemonInfo.postValue(getPokemonDetailsUseCase.getPokemonInfo(id))
            progress.showProgress(false)
        }
    }

}