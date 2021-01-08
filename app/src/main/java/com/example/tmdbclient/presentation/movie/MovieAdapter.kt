package com.example.tmdbclient.presentation.movie

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.databinding.ListItemBinding

class MovieAdapter(private val context: Context, private val onClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MyViewHolder>() {
    private val movieList = ArrayList<Movie>()

    fun setList(movies: List<Movie>) {
        movieList.clear()
        movieList.addAll(movies)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(movieList[position], context, onClickListener)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var frontAnim: AnimatorSet
    private lateinit var backAnim: AnimatorSet

    fun bind(movie: Movie, context: Context, itemClickListener: OnItemClickListener) {
        val scale = context.resources.displayMetrics.density
        binding.cardViewFront.cameraDistance = 8000 * scale
        binding.cardViewBack.cameraDistance = 8000 * scale
        frontAnim = AnimatorInflater.loadAnimator(context, R.animator.front_animator) as AnimatorSet
        backAnim = AnimatorInflater.loadAnimator(context, R.animator.back_animator) as AnimatorSet

        binding.titleTextViewFront.text = movie.title
        binding.descriptionTextViewFront.text = movie.overview
        binding.commentTextView.text = movie.review
        val posterUrl = "https://image.tmdb.org/t/p/w500/${movie.posterPath}"
        Glide.with(binding.imageViewFront.context)
            .load(posterUrl)
            .into(binding.imageViewFront)


        binding.cardViewBack.setOnClickListener {
            itemClickListener.onItemCLicked(movie, binding.cardViewFront, binding.cardViewBack)
        }
    }
}

interface OnItemClickListener {
    fun onItemCLicked(movie: Movie, cvFront: View, cvBack: View)
}
