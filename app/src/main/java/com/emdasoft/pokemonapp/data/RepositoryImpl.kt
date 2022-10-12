package com.emdasoft.pokemonapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emdasoft.pokemonapp.api.model.PokeApiResponse
import com.emdasoft.pokemonapp.api.model.PokeResult
import com.emdasoft.pokemonapp.domain.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryImpl : Repository {

    private val pokemonListLD = MutableLiveData<List<PokeResult>>()


    override fun getPokemonList(limit: Int, offset: Int) : LiveData<List<PokeResult>> {
        updateList(limit, offset)
        return pokemonListLD
    }

    override fun getPokemonDetails(id: Int) {
        TODO("Not yet implemented")
    }

    private fun updateList(limit: Int, offset: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: PokemonApiService.PokeApiService =
            retrofit.create(PokemonApiService.PokeApiService::class.java)

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
}








