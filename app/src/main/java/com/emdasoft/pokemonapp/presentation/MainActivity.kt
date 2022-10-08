package com.emdasoft.pokemonapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.emdasoft.pokemonapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
        binding.pokemonListRecyclerView.adapter = PokemonListAdapter {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }
        viewModel.getPokemonList()

        viewModel.pokemonList.observe(this) { list ->
            (pokemonListRecyclerView.adapter as PokemonListAdapter).setData(list)
        }

    }
}