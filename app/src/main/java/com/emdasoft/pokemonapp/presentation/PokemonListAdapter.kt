package com.emdasoft.pokemonapp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emdasoft.pokemonapp.R
import com.emdasoft.pokemonapp.api.model.PokeResult
import kotlinx.android.synthetic.main.list_item.view.*

class PokemonListAdapter(private val listener: OnItemClick) :
    RecyclerView.Adapter<PokemonListAdapter.SearchViewHolder>() {

    private var pokemonList = listOf<PokeResult>()

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: PokeResult, position: Int, listener: OnItemClick) {
            itemView.pokemonName.text = pokemon.name
            itemView.setOnClickListener {
                listener.onItemClick(position + 1)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(pokemonList[position], position, listener)
//        holder.itemView.setOnClickListener { pokemonClick(position + 1) }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<PokeResult>) {
        pokemonList = list
        notifyDataSetChanged()
    }

    interface OnItemClick {
        fun onItemClick(id: Int)
    }

}