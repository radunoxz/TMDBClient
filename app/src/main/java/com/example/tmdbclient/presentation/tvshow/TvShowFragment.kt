package com.example.tmdbclient.presentation.tvshow

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.MenuInflater
import android.view.Menu
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.FragmentTvShowBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class TvShowFragment : Fragment() {
    @Inject
    lateinit var factory: TvShowViewModelFactory
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var adapter: TvShowAdapter
    private lateinit var binding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_show, container, false)
        (requireActivity().application as Injector).createTvShowSubComponent()
            .inject(this)
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        binding.tvshowRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        adapter = TvShowAdapter()
        binding.tvshowRecyclerView.adapter = adapter
        displayPopularTvShows()
    }

    @SuppressLint("CheckResult")
    private fun displayPopularTvShows() {
        binding.tvshowProgressBar.visibility = View.VISIBLE
        tvShowViewModel = ViewModelProvider(this, factory).get(TvShowViewModel::class.java)
        val responseObservable = tvShowViewModel.getTvShows()
        responseObservable.subscribe(
            { tvShowsList ->
                adapter.setList(tvShowsList)
                adapter.notifyDataSetChanged()
            },
            {
                binding.tvshowProgressBar.visibility = View.GONE
                Toast.makeText(requireActivity(), "No data available", Toast.LENGTH_LONG).show()
            },
            {
                binding.tvshowProgressBar.visibility = View.GONE
            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @SuppressLint("CheckResult")
    private fun updateTvShows() {
        binding.tvshowProgressBar.visibility = View.VISIBLE
        val responseObservable = tvShowViewModel.updateTvShows()
        responseObservable.subscribe(
            { tvShowsList ->
                adapter.setList(tvShowsList)
                adapter.notifyDataSetChanged()
            },
            {
                binding.tvshowProgressBar.visibility = View.GONE
                Toast.makeText(requireActivity(), "No data available", Toast.LENGTH_LONG).show()
            },
            {
                binding.tvshowProgressBar.visibility = View.GONE
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updateTvShows()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
