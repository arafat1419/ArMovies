package com.arafat1419.armovies.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.arafat1419.armovies.assets.R
import com.arafat1419.armovies.core.ui.basic.TitleAdapter
import com.arafat1419.armovies.core.ui.paging.ReviewsPagingAdapter
import com.arafat1419.armovies.core.utils.DataMapper
import com.arafat1419.armovies.core.utils.Helper
import com.arafat1419.armovies.core.utils.ResultHandler
import com.arafat1419.armovies.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModel()

    private val arguments: DetailFragmentArgs by navArgs()

    private val resultHandler by lazy {
        ResultHandler(TAG, binding.loading.root)
    }

    private val reviewTitleAdapter by lazy {
        TitleAdapter().apply {
            setData(
                getString(R.string.reviews),
                false,
                ADAPTER_TITLE_REVIEWS
            )
        }
    }

    private val reviewsPagingAdapter by lazy {
        ReviewsPagingAdapter()
    }

    private val concatAdapter by lazy {
        val config = ConcatAdapter.Config.Builder().apply {
            setIsolateViewTypes(false)
        }.build()

        ConcatAdapter(
            config,
            reviewTitleAdapter,
            reviewsPagingAdapter
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewConfig()

        setDataMovie()
        setDataReviews()
        clickHandler()
    }

    private fun clickHandler() {
        with(binding) {
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }

            btnTrailer.setOnClickListener {
                navigateToTrailer(arguments.movieId)
            }
        }
    }

    private fun setDataMovie() {
        viewModel.getMovieDetail(arguments.movieId).observe(viewLifecycleOwner) { result ->
            resultHandler.handle(result) { data ->
                with(binding) {
                    Glide.with(this@DetailFragment)
                        .load(DataMapper.imageUrl + data?.posterPath)
                        .into(imgPoster)

                    txtTitle.text = data?.title
                    txtSubtitle.text = data?.releaseDate
                    txtStoryLine.text = data?.overview
                }
            }
        }
    }

    private fun setDataReviews() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMovieReviewsPaging(arguments.movieId).collectLatest {
                reviewsPagingAdapter.submitData(it)
                reviewsPagingAdapter.setItemType(ADAPTER_CONTENT_REVIEWS)
            }
        }
    }

    private fun navigateToTrailer(idMovie: Int) {
        findNavController().navigate(
            DetailFragmentDirections.actionDetailFragmentToTrailerFragment(idMovie)
        )
    }

    private fun recyclerViewConfig() {
        with(binding) {
            val layoutManager = LinearLayoutManager(context)

            Helper.setRecyclerView(rvDetail, layoutManager, concatAdapter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ADAPTER_TITLE_REVIEWS = 101
        private const val ADAPTER_CONTENT_REVIEWS = 102
        const val TAG = "DetailFragment"
    }
}