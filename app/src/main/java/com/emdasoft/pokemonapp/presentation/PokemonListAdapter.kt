package com.emdasoft.pokemonapp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emdasoft.pokemonapp.R
import com.emdasoft.pokemonapp.databinding.ListItemBinding
import com.emdasoft.pokemonapp.domain.model.PokeResult
import kotlinx.android.synthetic.main.list_item.view.*

class PokemonListAdapter(val pokemonClick: (Int) -> Unit) :
    RecyclerView.Adapter<PokemonListAdapter.SearchViewHolder>() {

    private var pokemonList: List<PokeResult> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<PokeResult>) {
        pokemonList = list
        notifyDataSetChanged()
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
//    {
//        private val binding = ListItemBinding.bind(itemView)
//        fun bind(pokemon: PokeResult, listener: Listener) = with(binding) {
//            pokemonName.text = buildString {
//                append(pokemon.name)
//            }
//            itemView.setOnClickListener {
//                listener.onClick(pokemon)
//            }
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.itemView.pokemonName.text = pokemon.name
        holder.itemView.setOnClickListener { pokemonClick(position + 1) }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

//    interface Listener {
//        fun onClick(pokemon: PokeResult)
//    }

}