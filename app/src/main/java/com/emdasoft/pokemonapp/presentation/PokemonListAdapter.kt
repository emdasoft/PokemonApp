package com.emdasoft.pokemonapp.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emdasoft.pokemonapp.R
import com.emdasoft.pokemonapp.api.model.PokeResult
import kotlinx.android.synthetic.main.list_item.view.*

class PokemonListAdapter(private val listener: OnItemClick) :
    ListAdapter<PokeResult, PokemonListAdapter.SearchViewHolder>(PokemonItemDiffUtilsCallback()) {

    private var count = 0

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: PokeResult, position: Int, listener: OnItemClick) {
            itemView.pokemonName.text = pokemon.name
            itemView.setOnClickListener {
                listener.onItemClick(position + 1)
            }
            itemView.setOnLongClickListener {
                listener.onLongItemClick(position + 1)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        Log.d("onBindViewHolder", "${++count}")
        holder.bind(getItem(position), position, listener)
//        holder.itemView.setOnClickListener { pokemonClick(position + 1) }
    }

    interface OnItemClick {
        fun onItemClick(id: Int)
        fun onLongItemClick(id: Int)
    }

}