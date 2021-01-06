package com.example.tmdbclient.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    private fun initRecyclerView() {
        binding.homeRecyclerview.layoutManager =
            GridLayoutManager(requireActivity(), 2)
        adapter = HomeAdapter()
        binding.homeRecyclerview.adapter = adapter
        binding.homeRecyclerview.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.homeRecyclerview.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                DividerItemDecoration.HORIZONTAL
            )
        )
        binding.homeRecyclerview.setHasFixedSize(true)
        binding.homeRecyclerview.isLayoutFrozen = true

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.movieButton.setOnClickListener {
//            val intent = Intent(requireActivity(), MovieActivity::class.java)
//            startActivity(intent)
//        }
//        binding.tvButton.setOnClickListener {
//            val intent = Intent(requireActivity(), TvShowActivity::class.java)
//            startActivity(intent)
//        }
//        binding.artistsButton.setOnClickListener {
//            val intent = Intent(requireActivity(), ArtistActivity::class.java)
//            startActivity(intent)
//        }
//    }
}
