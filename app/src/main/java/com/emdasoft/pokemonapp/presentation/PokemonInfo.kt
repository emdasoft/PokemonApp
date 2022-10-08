package com.emdasoft.pokemonapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emdasoft.pokemonapp.databinding.ActivityPokemonInfoBinding

class PokemonInfo : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}