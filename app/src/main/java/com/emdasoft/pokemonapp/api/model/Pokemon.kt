package com.emdasoft.pokemonapp.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemon(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val sprites: Sprites,
    val types: List<Types>
)

data class Sprites(
    @Expose @SerializedName("front_default") val frontDefault: String?,
    @Expose @SerializedName("front_shiny") val frontShiny: String?
)

data class Types(
    @Expose @SerializedName("type") val types: Type,
)

data class Type(
    @Expose @SerializedName("name") val name: String
)

