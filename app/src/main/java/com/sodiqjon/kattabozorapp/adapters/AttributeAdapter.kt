package com.sodiqjon.kattabozorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sodiqjon.kattabozorapp.databinding.AttributeItemBinding
import com.sodiqjon.kattabozorapp.response.Attribute


class AttributeDiffUtil : DiffUtil.ItemCallback<Attribute>() {
    override fun areItemsTheSame(oldItem: Attribute, newItem: Attribute): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Attribute, newItem: Attribute): Boolean {
        return oldItem == newItem
    }

}

class AttributeAdapter : ListAdapter<Attribute, RecyclerView.ViewHolder>(AttributeDiffUtil()) {

    inner class AttributeViewHolder(private val binding: AttributeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Attribute?) {
            binding.apply {
                attrName.text = item?.name
                attrValue.text = item?.value

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AttributeViewHolder(
        AttributeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AttributeViewHolder) holder.onBind(getItem(position))
    }

}