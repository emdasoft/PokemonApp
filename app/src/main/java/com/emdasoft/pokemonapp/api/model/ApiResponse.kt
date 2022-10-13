package com.emdasoft.pokemonapp.api.model

//import com.google.gson.annotations.Expose
//import com.google.gson.annotations.SerializedName

data class PokeApiResponse (
    val count: Int,
    val results: List<PokeResult>
)

data class PokeResult (
    val name: String
)