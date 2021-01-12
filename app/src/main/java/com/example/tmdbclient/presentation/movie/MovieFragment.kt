package com.example.tmdbclient.presentation.movie

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.databinding.FragmentMovieBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class MovieFragment : Fragment(), OnItemClickListener {
    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: FragmentMovieBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var frontAnim: AnimatorSet
    private lateinit var backAnim: AnimatorSet
    private var isFront: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        (requireActivity().application as Injector).createMovieSubComponent()
            .inject(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        frontAnim = AnimatorInflater.loadAnimator(
            requireActivity(),
            R.animator.front_animator
        ) as AnimatorSet
        backAnim = AnimatorInflater.loadAnimator(
            requireActivity(),
            R.animator.back_animator
        ) as AnimatorSet

        initRecyclerView()
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun initRecyclerView() {
        binding.movieRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        adapter = MovieAdapter(requireActivity(), this)
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
                Toast.makeText(requireActivity(), "No data available", Toast.LENGTH_LONG).show()
                Log.e("MYTAG 10", it.message.toString())
            },
            { binding.movieProgressBar.visibility = View.GONE }
        )
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onItemCLicked(movie: Movie, cvFront: View, cvBack: View) {
        Toast.makeText(requireActivity(), movie.title, Toast.LENGTH_SHORT).show()
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
