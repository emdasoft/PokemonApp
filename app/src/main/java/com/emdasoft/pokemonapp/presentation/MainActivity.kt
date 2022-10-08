package com.emdasoft.pokemonapp.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.emdasoft.pokemonapp.databinding.ActivityMainBinding
import com.emdasoft.pokemonapp.domain.model.PokeResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PokemonListAdapter.Listener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PokemonListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[PokemonListViewModel::class.java]
        initUI()
    }

    private fun initUI() {
        binding.pokemonListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.pokemonListRecyclerView.adapter = PokemonListAdapter(this)

//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra("id", it)
//            startActivity(intent)

        viewModel.getPokemonList()

        viewModel.pokemonList.observe(this) { list ->
            (pokemonListRecyclerView.adapter as PokemonListAdapter).setData(list)
        }

    }

    override fun onClick(pokemon: PokeResult) {
        Toast.makeText(this, "This is ${pokemon.name}", Toast.LENGTH_SHORT).show()
    }
}