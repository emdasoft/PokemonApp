package com.emdasoft.pokemonapp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emdasoft.pokemonapp.R
import com.emdasoft.pokemonapp.databinding.ListItemBinding
import com.emdasoft.pokemonapp.domain.model.PokeResult

class PokemonListAdapter(private val listener: Listener) :
    RecyclerView.Adapter<PokemonListAdapter.SearchViewHolder>() {

    private var pokemonList: List<PokeResult> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<PokeResult>) {
        pokemonList = list
        notifyDataSetChanged()
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemBinding.bind(itemView)
        fun bind(pokemon: PokeResult, listener: Listener) = with(binding) {
            pokemonName.text = buildString {
                append(pokemon.name)
            }
            itemView.setOnClickListener {
                listener.onClick(pokemon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(pokemonList[position], listener)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    interface Listener {
        fun onClick(pokemon: PokeResult)
    }

}