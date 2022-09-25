package com.arafat1419.armovies.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.arafat1419.armovies.core.ui.paging.MoviesPagingAdapter
import com.arafat1419.armovies.databinding.FragmentMoviesBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoviesViewModel by viewModel()

    private val arguments: MoviesFragmentArgs by navArgs()

    private val moviesPagingAdapter by lazy {
        MoviesPagingAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewConfig()

        if (arguments.genreId == 0) {
            setListPopular()
        } else {
            setListByGenre()
        }


        clickHandler()
    }

    private fun clickHandler() {
        with(binding) {
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }

        moviesPagingAdapter.onItemClicked = { movie ->
            movie.id?.let { navigateToDetail(it) }
        }

        moviesPagingAdapter.onTrailerClicked = { movie ->
            movie.id?.let { navigateToTrailer(it) }
        }
    }

    private fun setListByGenre() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getListByGenrePaging(arguments.genreId).collectLatest {
                moviesPagingAdapter.submitData(it)
            }
        }
    }


    private fun setListPopular() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getListPopularPaging().collectLatest {
                moviesPagingAdapter.submitData(it)
            }
        }
    }

    private fun navigateToDetail(idMovie: Int) {
        findNavController().navigate(
            MoviesFragmentDirections.actionMoviesFragmentToDetailFragment(idMovie)
        )
    }

    private fun navigateToTrailer(idMovie: Int) {
        findNavController().navigate(
            MoviesFragmentDirections.actionMoviesFragmentToTrailerFragment(idMovie)
        )
    }

    private fun recyclerViewConfig() {
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moviesPagingAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}