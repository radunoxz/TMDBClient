package com.example.tmdbclient.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.HomeItemBinding

class HomeAdapter() : RecyclerView.Adapter<ViewHolder>() {
    private val itemList = listOf("Item 1", "Item 1", "Item 1", "Item 1", "Item 1", "Item 1")
    private val resourceId =
        listOf(
            R.drawable.ic_home,
            R.drawable.ic_home,
            R.drawable.ic_home,
            R.drawable.ic_home,
            R.drawable.ic_home,
            R.drawable.ic_home
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val height = parent.measuredHeight / 3
        val binding: HomeItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.home_item, parent, false)
        binding.root.minimumHeight = height
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position], resourceId[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}

class ViewHolder(val binding: HomeItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(itemDescription: String, itemIcon: Int) {
        binding.tvMenuItem.text = itemDescription
        Glide.with(binding.ivMenuItem.context)
            .load(itemIcon)
            .into(binding.ivMenuItem)
    }
}
