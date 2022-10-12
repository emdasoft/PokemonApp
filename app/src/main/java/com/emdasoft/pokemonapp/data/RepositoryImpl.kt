package com.emdasoft.pokemonapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emdasoft.pokemonapp.api.model.PokeApiResponse
import com.emdasoft.pokemonapp.api.model.PokeResult
import com.emdasoft.pokemonapp.api.model.Pokemon
import com.emdasoft.pokemonapp.domain.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryImpl : Repository {

    private val pokemonListLD = MutableLiveData<List<PokeResult>>()

    private val pokemonInfoLD = MutableLiveData<Pokemon>()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PokemonApiService.PokeApiService =
        retrofit.create(PokemonApiService.PokeApiService::class.java)

    override fun getPokemonList(limit: Int, offset: Int): LiveData<List<PokeResult>> {
        updateList(limit, offset)
        return pokemonListLD
    }

    override fun getPokemonDetails(id: Int): Pokemon {
        getInfo(id)
        return pokemonInfoLD.value
    }

    private fun updateList(limit: Int, offset: Int) {

        val call = service.getPokemonList(limit, offset)

        call.enqueue(object : Callback<PokeApiResponse> {
            override fun onResponse(
                call: Call<PokeApiResponse>,
                response: Response<PokeApiResponse>
            ) {
                response.body()?.results?.let { list ->
                    pokemonListLD.postValue(list)
                }
            }

            override fun onFailure(call: Call<PokeApiResponse>, t: Throwable) {
                call.cancel()
            }
        })

    }

    private fun getInfo(id: Int) {
        val call = service.getPokemonInfo(id)

        call.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { pokemon ->
                    pokemonInfoLD.postValue(pokemon)
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel()
            }

        })
    }
}








