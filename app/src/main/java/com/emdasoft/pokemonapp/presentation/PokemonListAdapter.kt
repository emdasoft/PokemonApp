package com.emdasoft.pokemonapp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emdasoft.pokemonapp.R
import com.emdasoft.pokemonapp.databinding.ListItemBinding
import com.emdasoft.pokemonapp.domain.model.PokeResult

class PokemonListAdapter(val pokemonClick: (Int) -> Unit) :
    RecyclerView.Adapter<PokemonListAdapter.SearchViewHolder>() {

    var pokemonList: List<PokeResult> = emptyList<PokeResult>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<PokeResult>) {
        pokemonList = list
        notifyDataSetChanged()
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemBinding.bind(itemView)
        fun bind(pokemon: PokeResult) = with(binding) {
            pokemonName.text = buildString {
                append(pokemon.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(pokemonList[position])

//        holder.itemView.setOnClickListener { pokemonClick(position + 1) }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}