package com.example.tmdbclient.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.HomeItemBinding

class HomeAdapter(private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ViewHolder>() {

    companion object{
        private const val ROW_NUM = 3
    }

    private val itemList = listOf(
        "Popular Movies",
        "Popular Artists",
        "Popular TV Shows",
        "Trending Movies",
        "Discover",
        "Settings"
    )
    private val resourceId =
        listOf(
            R.drawable.ic_movie,
            R.drawable.ic_artist,
            R.drawable.ic_tv_show,
            R.drawable.ic_trending,
            R.drawable.ic_discover,
            R.drawable.ic_settings,
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val height = parent.measuredHeight / ROW_NUM
        val binding: HomeItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.home_item, parent, false)
        binding.root.minimumHeight = height
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position], resourceId[position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemId(position: Int): Long = position.toLong()
}

class ViewHolder(val binding: HomeItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(itemDescription: String, itemIcon: Int, itemClickListener: OnItemClickListener) {
        binding.tvMenuItem.text = itemDescription
        Glide.with(binding.ivMenuItem.context)
            .load(itemIcon)
            .into(binding.ivMenuItem)

        itemView.setOnClickListener {
            itemClickListener.onItemCLicked(itemIcon)
        }
    }
}

interface OnItemClickListener {
    fun onItemCLicked(menuItem: Int)
}
