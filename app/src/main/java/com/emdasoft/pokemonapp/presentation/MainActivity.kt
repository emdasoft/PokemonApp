package com.emdasoft.pokemonapp.presentation

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.emdasoft.pokemonapp.R
import com.emdasoft.pokemonapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PokemonListAdapter.OnItemClick, ShowProgress {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PokemonListViewModel
    private lateinit var rvAdapter: PokemonListAdapter
//    private var viewModel = PokemonListViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[PokemonListViewModel::class.java]

        viewModel.getPokemonList()

        setupRecyclerView()

        viewModel.pokemonList.observe(this) { list ->

            list.body()?.let { }

            list.body()
                ?.let { rvAdapter.submitList(it.results) }
        }
    }

    private fun setupRecyclerView() {
        checkConnection()

        binding.apply {
            pokemonListRecyclerView.setHasFixedSize(true)

            pokemonListRecyclerView.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            rvAdapter = PokemonListAdapter(this@MainActivity)
            pokemonListRecyclerView.adapter = rvAdapter
        }


    }

    private fun checkConnection() {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if (!isConnected) {
            Toast.makeText(this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show()
            binding.pokemonListRecyclerView.visibility = View.GONE
            binding.textViewError.visibility = View.VISIBLE
            binding.tryButton.visibility = View.VISIBLE
            binding.textViewError.text = getString(R.string.error_connection)
            binding.tryButton.setOnClickListener {
                setupRecyclerView()
            }
        } else {
            binding.pokemonListRecyclerView.visibility = View.VISIBLE
            binding.textViewError.visibility = View.GONE
            binding.tryButton.visibility = View.GONE
        }
    }

    override fun onItemClick(id: Int) {
        val intent = Intent(this, PokemonInfo::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun onLongItemClick(id: Int) {
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
    }

    override fun showProgress(enabled: Boolean) {
        if (enabled) binding.mainProgress.visibility = View.VISIBLE
        else binding.mainProgress.visibility = View.GONE
    }
}