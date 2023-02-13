package com.emdasoft.pokemonapp.presentation

import androidx.recyclerview.widget.DiffUtil
import com.emdasoft.pokemonapp.api.model.PokeResult

class PokemonItemDiffUtilsCallback : DiffUtil.ItemCallback<PokeResult>() {
    override fun areItemsTheSame(oldItem: PokeResult, newItem: PokeResult): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: PokeResult, newItem: PokeResult): Boolean {
        return oldItem == newItem
    }
}