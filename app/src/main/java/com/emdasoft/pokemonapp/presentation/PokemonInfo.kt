package com.emdasoft.pokemonapp.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.emdasoft.pokemonapp.databinding.ActivityPokemonInfoBinding

class PokemonInfo : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonInfoBinding
    private lateinit var viewModel: PokemonInfoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProvider(this)[PokemonInfoViewModel::class.java]
        init()

    }

    private fun init() {

        val id = intent.getIntExtra("id", -1)

        viewModel.getPokemonInfo(id)

        viewModel.pokemonInfo.observe(this) { pokemon ->
            binding.nameTextView.text = pokemon.name
            binding.heightText.text = buildString {
                append("Height:")
                append(pokemon.height * 10.0)
                append("cm")
            }
            binding.weightText.text = buildString {
                append("Weight:")
                append(pokemon.weight / 10.0)
                append("kg")
            }

            Glide.with(this).load(pokemon.sprites.frontDefault).into(binding.imageView)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

}