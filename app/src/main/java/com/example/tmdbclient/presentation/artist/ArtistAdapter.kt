package com.example.tmdbclient.presentation.artist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.databinding.ListItemBinding

class ArtistAdapter : RecyclerView.Adapter<MyViewHolder>() {
    private val artistsList = ArrayList<Artist>()

    fun setArtistList(artists: List<Artist>) {
        artistsList.clear()
        artistsList.addAll(artists)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(artistsList[position])
    }

    override fun getItemCount(): Int {
        return artistsList.size
    }
}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(artist: Artist) {
        binding.titleTextViewFront.text = artist.name
        binding.descriptionTextViewFront.text = artist.popularity.toString()
        val posterURL = "https://image.tmdb.org/t/p/w500/${artist.profilePath}"
        Glide.with(binding.imageViewFront.context)
            .load(posterURL)
            .into(binding.imageViewFront)
        binding.popProgress.visibility = View.GONE
    }
}
