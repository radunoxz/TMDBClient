package com.example.tmdbclient.presentation.movie

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.databinding.ListItemBinding

class MovieAdapter(
    private val context: Context,
    private val onClickListener: OnItemClickListener,
) :
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

    companion object {
        private const val TIMER_MULTIPLER = 3 * 1000L
        private const val CAMERA_DISTANCE = 8000
    }

    private lateinit var frontAnim: AnimatorSet
    private lateinit var backAnim: AnimatorSet

    fun bind(movie: Movie, context: Context, itemClickListener: OnItemClickListener) {
        val scale = context.resources.displayMetrics.density
        binding.cardViewFront.cameraDistance = CAMERA_DISTANCE * scale
        binding.cardViewBack.cameraDistance = CAMERA_DISTANCE * scale
        frontAnim = AnimatorInflater.loadAnimator(context, R.animator.front_animator) as AnimatorSet
        backAnim = AnimatorInflater.loadAnimator(context, R.animator.back_animator) as AnimatorSet

        binding.titleTextViewFront.text = movie.title
        binding.descriptionTextViewFront.text = movie.overview
        binding.commentTextView.text = movie.review
        binding.popProgress.popularityScore = movie.voteAverage
        val posterUrl = "https://image.tmdb.org/t/p/w500/${movie.posterPath}"
        Glide.with(binding.imageViewFront.context)
            .load(posterUrl)
            .into(binding.imageViewFront)

        binding.cardViewBack.setOnClickListener {
            itemClickListener.onItemCLicked(movie, binding.cardViewFront, binding.cardViewBack)
        }

        object : CountDownTimer(TIMER_MULTIPLER, 1) {
            override fun onTick(millisUntilFinished: Long) {
                binding.popProgress.progress =
                    (TIMER_MULTIPLER - millisUntilFinished).toFloat() / TIMER_MULTIPLER
                binding.popProgress.progressText =
                    movie.voteAverage * (TIMER_MULTIPLER - millisUntilFinished).toFloat() / TIMER_MULTIPLER
//                Log.e(
//                    "TIMER",
//                    ((TIMER_MULTIPLER - millisUntilFinished).toFloat() / TIMER_MULTIPLER).toString()
//                )
            }

            override fun onFinish() {
                binding.popProgress.progress = 1f
            }
        }.start()
    }
}

interface OnItemClickListener {
    fun onItemCLicked(movie: Movie, cvFront: View, cvBack: View)
}
