package com.sodiqjon.kattabozorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sodiqjon.kattabozorapp.adapters.AttributeAdapter
import com.sodiqjon.kattabozorapp.databinding.ActivityDetailsBinding
import com.sodiqjon.kattabozorapp.response.Offer
import com.sodiqjon.kattabozorapp.response.OfferResponse
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var attributeAdapter: AttributeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent?.getParcelableExtra<Offer>("offerdata")
        populateData(item)

    }

    private fun populateData(item: Offer?) {

        val linearLayoutManager = LinearLayoutManager(this)

        attributeAdapter = AttributeAdapter()

        binding.apply {
            deviceName.text = item?.name
            tvMerchant.text = item?.merchant

            item?.image?.height?.toInt()?.let { height ->
                item.image.width.toInt().let { width ->
                    Glide.with(binding.root)
                        .load(item.image.url)
                        .error(R.drawable.error)
                        .centerCrop()
                        .override(width, height)
                        .into(imagePhone)
                }
            }

            attributeAdapter.submitList(item?.attributes)
            attributeRecyclerView.layoutManager = linearLayoutManager
            attributeRecyclerView.adapter = attributeAdapter
        }
    }


}