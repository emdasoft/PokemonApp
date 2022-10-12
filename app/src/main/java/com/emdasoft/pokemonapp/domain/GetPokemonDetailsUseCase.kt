package com.emdasoft.pokemonapp.domain

class GetPokemonDetailsUseCase(private val repository: Repository) {

    fun getPokemonDetails(id: Int) {

        repository.getPokemonDetails(id)

    }

}