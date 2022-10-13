package com.emdasoft.pokemonapp.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.emdasoft.pokemonapp.R
import com.emdasoft.pokemonapp.databinding.ActivityPokemonInfoBinding
import com.google.android.material.chip.Chip

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
        checkConnection()
        val id = intent.getIntExtra("id", -1)

        viewModel.getPokemonInfo(id)

        viewModel.pokemonInfo.observe(this) { pokemon ->
            binding.nameTextView.text = pokemon.body()?.name
            binding.heightText.text = buildString {
                append("Height: ")
                append(pokemon.body()?.height!! * 10.0)
                append(" cm")
            }
            binding.weightText.text = buildString {
                append("Weight: ")
                append(pokemon.body()?.weight!! / 10.0)
                append(" kg")
            }
            pokemon.body()?.types!!.forEach {
                val chip = Chip(binding.chipGroup.context)
                chip.text= it.types.name
                chip.isClickable = false
                chip.isCheckable = false
                binding.chipGroup.addView(chip)
            }

            Glide.with(this).load(pokemon.body()?.sprites!!.frontDefault).into(binding.imageView)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    private fun checkConnection() {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if (!isConnected) {
            Toast.makeText(this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show()
            binding.detailsCard.visibility = View.GONE
            binding.textViewError.visibility = View.VISIBLE
            binding.tryButton.visibility = View.VISIBLE
            binding.textViewError.text = getString(R.string.error_connection)
            binding.tryButton.setOnClickListener {
                init()
            }
        } else {
            binding.detailsCard.visibility = View.VISIBLE
            binding.textViewError.visibility = View.GONE
            binding.tryButton.visibility = View.GONE
        }
    }

}