package com.sodiqjon.kattabozorapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sodiqjon.kattabozorapp.adapters.OffersAdapter
import com.sodiqjon.kattabozorapp.common.UiEvent
import com.sodiqjon.kattabozorapp.databinding.ActivityMainBinding
import com.sodiqjon.kattabozorapp.response.Offer
import com.sodiqjon.kattabozorapp.viewModels.OfferViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<OfferViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var offersAdapter: OffersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            offersAdapter = OffersAdapter()
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.getOfferList()
            }


            lifecycleScope.launchWhenCreated {
                viewModel.offerListObserver.collectLatest { event ->
                    when (event) {
                        is UiEvent.Loading -> {
                            if (!swipeRefreshLayout.isRefreshing) {
                                progressBar.isVisible = true
                            }
                        }

                        is UiEvent.Empty -> Unit
                        is UiEvent.Success<*> -> {
                            swipeRefreshLayout.isRefreshing = false
                            offersAdapter.submitList(event.data as? List<Offer>)
                            progressBar.isVisible = false
                        }

                        is UiEvent.Error -> {
                            swipeRefreshLayout.isRefreshing = false
                            progressBar.isVisible = false
                            errorTv.isVisible = true

                        }
                    }
                }
            }

            mainRecycler.apply {
                layoutManager = linearLayoutManager
                adapter = offersAdapter
            }

            offersAdapter.setListener(object : OffersAdapter.OfferItemClick {
                override fun onItemClicked(item: Offer) {
                    Intent(this@MainActivity, DetailsActivity::class.java).apply {
                        putExtra("offerdata", item)
                        startActivity(this)
                    }
                }

            })

        }

    }
}