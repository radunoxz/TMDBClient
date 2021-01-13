package com.example.tmdbclient.presentation.artist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.FragmentArtistBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class ArtistFragment : Fragment() {
    @Inject
    lateinit var factory: ArtistViewModelFactory
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var binding: FragmentArtistBinding
    private lateinit var adapter: ArtistAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_artist, container, false)
        (requireActivity().application as Injector).createArtistSubComponent().inject(this)
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(requireActivity())
        adapter = ArtistAdapter()
        binding.artistRecyclerView.layoutManager = layoutManager
        binding.artistRecyclerView.adapter = adapter
        displayArtistList()
    }

    @SuppressLint("CheckResult")
    private fun displayArtistList() {
        binding.artistProgressBar.visibility = View.VISIBLE
        artistViewModel = ViewModelProvider(this, factory).get(ArtistViewModel::class.java)
        artistViewModel.getArtists().subscribe(
            {
                adapter.setArtistList(it)
                adapter.notifyDataSetChanged()
            },
            {
                binding.artistProgressBar.visibility = View.GONE
                Toast.makeText(requireActivity(), "No data available", Toast.LENGTH_LONG).show()
            },
            {
                binding.artistProgressBar.visibility = View.GONE
            })
    }

    @SuppressLint("CheckResult")
    private fun updateArtists() {
        binding.artistProgressBar.visibility = View.VISIBLE
        artistViewModel = ViewModelProvider(this, factory).get(ArtistViewModel::class.java)
        artistViewModel.updateArtists().subscribe(
            {
                adapter.setArtistList(it)
                adapter.notifyDataSetChanged()
            },
            {
                binding.artistProgressBar.visibility = View.GONE
                Toast.makeText(requireActivity(), "No data available", Toast.LENGTH_LONG).show()
            },
            {
                binding.artistProgressBar.visibility = View.GONE
            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updateArtists()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
