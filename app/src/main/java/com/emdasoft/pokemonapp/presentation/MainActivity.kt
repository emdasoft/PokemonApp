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
import androidx.recyclerview.widget.RecyclerView
import com.emdasoft.pokemonapp.R
import com.emdasoft.pokemonapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PokemonListAdapter.OnItemClick {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PokemonListViewModel
    private lateinit var layoutManager: LinearLayoutManager
    private var loading = true
    private var paginate = true
    private var offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[PokemonListViewModel::class.java]
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        checkConnection()
        binding.pokemonListRecyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        binding.pokemonListRecyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            loading = false
                            paginate()
                        }
                    }
                }
            }
        })

        binding.pokemonListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.pokemonListRecyclerView.adapter = PokemonListAdapter(this)

        viewModel.pokemonList.observe(this) { list ->
            (pokemonListRecyclerView.adapter as PokemonListAdapter).setData(list)
        }

    }

    private fun paginate() {
        if (paginate) {
            offset += 20
            loadPokemon()
            loading = true
        }
    }

    private fun loadPokemon() {

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
}