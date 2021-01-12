package com.example.tmdbclient.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.FragmentHomeBinding
import com.example.tmdbclient.presentation.artist.ArtistActivity
import com.example.tmdbclient.presentation.tvshow.TvShowActivity

class HomeFragment : Fragment(), OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    private fun initRecyclerView() {
        binding.homeRecyclerview.layoutManager =
            GridLayoutManager(requireActivity(), 2)
        adapter = HomeAdapter(this)
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

    override fun onItemCLicked(menuItem: Int) {
        when (menuItem) {
            R.drawable.ic_movie -> {
//                val intent = Intent(requireActivity(), MovieActivity::class.java)
//                Toast.makeText(requireActivity(), menuItem.toString(),Toast.LENGTH_SHORT).show()
//                startActivity(intent)
                findNavController().navigate(R.id.action_homeFragment2_to_movieFragment)
            }
            R.drawable.ic_artist -> {
                val intent = Intent(requireActivity(), ArtistActivity::class.java)
                startActivity(intent)
            }
            R.drawable.ic_tv_show -> {
                val intent = Intent(requireActivity(), TvShowActivity::class.java)
                startActivity(intent)
            }
            else -> return
        }
    }
}
