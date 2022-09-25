package com.arafat1419.armovies.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.arafat1419.armovies.assets.R
import com.arafat1419.armovies.core.ui.ConcatLinearHorizontalAdapter
import com.arafat1419.armovies.core.ui.basic.GenresAdapter
import com.arafat1419.armovies.core.ui.basic.MoviesAdapter
import com.arafat1419.armovies.core.ui.basic.TitleAdapter
import com.arafat1419.armovies.core.utils.Helper
import com.arafat1419.armovies.core.utils.ResultHandler
import com.arafat1419.armovies.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModel()

    private val nowPlayingTitleAdapter by lazy {
        TitleAdapter().apply {
            setData(getString(R.string.now_playing), false, ADAPTER_TITLE_NOW_PLAYING)
        }
    }

    private val concatNowPlayingAdapter by lazy {
        ConcatLinearHorizontalAdapter()
    }

    private val genresTitleAdapter by lazy {
        TitleAdapter().apply {
            setData(getString(R.string.genres), true, ADAPTER_TITLE_GENRE)
            notifyDataSetChanged()
        }
    }

    private val genresAdapter by lazy {
        GenresAdapter()
    }

    private val popularTitleAdapter by lazy {
        TitleAdapter().apply {
            setData(getString(R.string.popular), true, ADAPTER_TITLE_POPULAR)
        }
    }

    private val concatPopularAdapter by lazy {
        ConcatLinearHorizontalAdapter()
    }

    private val concatAdapter by lazy {
        val config = ConcatAdapter.Config.Builder().apply {
            setIsolateViewTypes(false)
        }.build()

        ConcatAdapter(
            config,
            nowPlayingTitleAdapter,
            concatNowPlayingAdapter,
            genresTitleAdapter,
            genresAdapter,
            popularTitleAdapter,
            concatPopularAdapter
        )
    }

    private val resultHandler by lazy {
        ResultHandler(TAG, binding.loading.root)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewConfig()

        setDataNowPlaying()
        setDataGenre()
        setDataPopular()

        clickHandler()
    }

    private fun clickHandler() {
        concatNowPlayingAdapter.onItemClicked = { movie ->
            movie.id?.let { navigateToDetail(it) }
        }

        concatNowPlayingAdapter.onTrailerClicked = { movie ->
            movie.id?.let { navigateToTrailer(it) }
        }

        genresTitleAdapter.onShowClicked = {
            navigateToGenres()
        }

        genresAdapter.onItemClicked = { genreItem ->
            genreItem.id?.let { navigateToMovies(it) }
        }

        popularTitleAdapter.onShowClicked = {
            navigateToMovies(0)
        }

        concatPopularAdapter.onItemClicked = { movie ->
            movie.id?.let { navigateToDetail(it) }
        }

        concatPopularAdapter.onTrailerClicked = { movie ->
            movie.id?.let { navigateToTrailer(it) }
        }
    }

    private fun setDataNowPlaying() {
        viewModel.getNowPlaying().observe(viewLifecycleOwner) { result ->
            resultHandler.handle(result) { data ->
                concatNowPlayingAdapter.setAdapter(MoviesAdapter().apply {
                    setData(data?.results, false, ADAPTER_CONTENT_NOW_PLAYING)
                })
            }
        }
    }

    private fun setDataGenre() {
        viewModel.getListGenre().observe(viewLifecycleOwner) { result ->
            resultHandler.handle(result) { data ->
                genresAdapter.setData(data?.slice(0..3), ADAPTER_CONTENT_GENRE)
            }
        }
    }

    private fun setDataPopular() {
        viewModel.getListPopular().observe(viewLifecycleOwner) { result ->
            resultHandler.handle(result) { data ->
                concatPopularAdapter.setAdapter(MoviesAdapter().apply {
                    setData(data?.results, true, ADAPTER_CONTENT_POPULAR)
                })
                popularTitleAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun navigateToDetail(idMovie: Int) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(idMovie)
        )
    }

    private fun navigateToTrailer(idMovie: Int) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToTrailerFragment(idMovie)
        )
    }

    private fun navigateToMovies(genreId: Int) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToMoviesFragment().apply {
                this.genreId = genreId
            }
        )
    }

    private fun navigateToGenres() {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToGenresFragment()
        )
    }

    private fun recyclerViewConfig() {
        with(binding) {
            val layoutManager = GridLayoutManager(context, 2).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (concatAdapter.getItemViewType(position)) {
                            ADAPTER_CONTENT_GENRE -> 1
                            else -> 2
                        }
                    }

                }
            }

            Helper.setRecyclerView(rvHome, layoutManager, concatAdapter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ADAPTER_TITLE_NOW_PLAYING = 101
        private const val ADAPTER_TITLE_GENRE = 102
        private const val ADAPTER_TITLE_POPULAR = 103
        private const val ADAPTER_CONTENT_NOW_PLAYING = 104
        private const val ADAPTER_CONTENT_GENRE = 105
        private const val ADAPTER_CONTENT_POPULAR = 106
        const val TAG = "HomeFragment"
    }
}