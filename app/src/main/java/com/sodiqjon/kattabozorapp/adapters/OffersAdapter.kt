package com.sodiqjon.kattabozorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sodiqjon.kattabozorapp.R
import com.sodiqjon.kattabozorapp.databinding.OfferItemBinding
import com.sodiqjon.kattabozorapp.response.Offer
import com.sodiqjon.kattabozorapp.response.OfferResponse


class OffersDiffUtil : DiffUtil.ItemCallback<Offer>() {
    override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem == newItem
    }

}

class OffersAdapter : ListAdapter<Offer, RecyclerView.ViewHolder>(OffersDiffUtil()) {

    private var listener: OfferItemClick? = null

    fun setListener(itemClick: OfferItemClick) {
        this.listener = itemClick
    }

    inner class OffersViewHolder(private val binding: OfferItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Offer?) {
            binding.apply {

                itemView.setOnClickListener {
                    item?.apply { listener?.onItemClicked(item) }
                }
                deviceName.text = item?.brand
                brandName.text = item?.name
                item?.image?.height?.toInt()?.let { height ->
                    item.image.width.toInt().let { width ->
                        Glide.with(binding.root)
                            .load(item.image.url)
                            .error(R.drawable.ic_launcher_background)
                            .centerCrop()
                            .override(width, height)
                            .into(imageView)
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OffersViewHolder(
        OfferItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is OffersAdapter.OffersViewHolder) holder.onBind(getItem(position))
    }

    interface OfferItemClick {
        fun onItemClicked(item: Offer)
    }

}