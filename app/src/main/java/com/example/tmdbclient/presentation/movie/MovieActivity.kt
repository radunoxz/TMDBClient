package com.example.tmdbclient.presentation.movie

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.databinding.ActivityMovieBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class MovieActivity : AppCompatActivity(), OnItemClickListener {
    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMovieBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var frontAnim: AnimatorSet
    private lateinit var backAnim: AnimatorSet
    private var isFront: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("MYTAG", Thread.currentThread().id.toString())
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        (application as Injector).createMovieSubComponent()
            .inject(this)

        frontAnim = AnimatorInflater.loadAnimator(this, R.animator.front_animator) as AnimatorSet
        backAnim = AnimatorInflater.loadAnimator(this, R.animator.back_animator) as AnimatorSet
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.movieRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter(applicationContext, this)
        binding.movieRecyclerView.adapter = adapter
        displayPopularMovies()
    }

    @SuppressLint("CheckResult")
    private fun displayPopularMovies() {
        binding.movieProgressBar.visibility = View.VISIBLE
        movieViewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)
        val responseObservable = movieViewModel.getMovies()
        responseObservable.subscribe(
            { movieList ->
                adapter.setList(movieList)
                adapter.notifyDataSetChanged()
                Log.i("MYTAG", Thread.currentThread().id.toString())
                Log.i("MYTAG", movieList.toString())
            },
            {
                binding.movieProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_LONG).show()
                Log.e("MYTAG 10", it.message.toString())
            },
            { binding.movieProgressBar.visibility = View.GONE }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.update, menu)
        return true
    }

    // TODO Complete this with Rx
    @SuppressLint("CheckResult")
    private fun updateMovies() {
        binding.movieProgressBar.visibility = View.VISIBLE
        val response = movieViewModel.updateMovies()
        response.subscribe(
            { listMovies ->
                adapter.setList(listMovies)
                adapter.notifyDataSetChanged()
                binding.movieProgressBar.visibility = View.GONE
                Log.i("MYTAG", Thread.currentThread().id.toString())
            },
            { e -> Log.i("MYTAG", e.toString()) },
            { binding.movieProgressBar.visibility = View.GONE })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_update -> {
                updateMovies()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onItemCLicked(movie: Movie, cvFront: View, cvBack: View) {
        Toast.makeText(this, movie.title.toString(), Toast.LENGTH_SHORT).show()
        isFront = if (isFront) {
            frontAnim.setTarget(cvFront)
            backAnim.setTarget(cvBack)
            frontAnim.start()
            backAnim.start()
            false
        } else {
            frontAnim.setTarget(cvBack)
            backAnim.setTarget(cvFront)
            backAnim.start()
            frontAnim.start()
            true
        }
    }
}
