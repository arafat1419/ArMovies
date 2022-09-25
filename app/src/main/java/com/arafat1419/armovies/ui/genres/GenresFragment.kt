package com.arafat1419.armovies.ui.genres

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.arafat1419.armovies.core.ui.basic.GenresAdapter
import com.arafat1419.armovies.core.utils.Helper
import com.arafat1419.armovies.core.utils.ResultHandler
import com.arafat1419.armovies.databinding.FragmentGenresBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenresFragment : Fragment() {
    private var _binding: FragmentGenresBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GenresViewModel by viewModel()

    private val genresAdapter by lazy {
        GenresAdapter()
    }

    private val resultHandler by lazy {
        ResultHandler(TAG, binding.loading.root)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGenresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewConfig()
        setListGenre()
        clickHandler()
    }

    private fun clickHandler() {
        with(binding) {
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }

        genresAdapter.onItemClicked = { genre ->
            genre.id?.let { navigateToMovies(it) }
        }
    }

    private fun setListGenre() {
        viewModel.getListGenre().observe(viewLifecycleOwner) { result ->
            resultHandler.handle(result) { data ->
                genresAdapter.setData(data)
            }
        }
    }

    private fun navigateToMovies(genreId: Int) {
        findNavController().navigate(
            GenresFragmentDirections.actionGenresFragmentToMoviesFragment().apply {
                this.genreId = genreId
            }
        )
    }

    private fun recyclerViewConfig() {
        with(binding) {
            val layoutManager = GridLayoutManager(context, 2)
            Helper.setRecyclerView(rvGenres, layoutManager, genresAdapter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "GenresFragment"
    }
}