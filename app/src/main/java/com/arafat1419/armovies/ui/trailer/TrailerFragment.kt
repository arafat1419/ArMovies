package com.arafat1419.armovies.ui.trailer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.arafat1419.armovies.core.utils.ResultHandler
import com.arafat1419.armovies.databinding.FragmentTrailerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrailerFragment : Fragment() {

    private var _binding: FragmentTrailerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TrailerViewModel by viewModel()

    private val arguments: TrailerFragmentArgs by navArgs()

    private val resultHandler by lazy {
        ResultHandler(TAG, binding.loading.root)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTrailerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTrailersData()
    }

    private fun setTrailersData() {
        viewModel.getMovieTrailers(arguments.movieId).observe(viewLifecycleOwner) {result ->
            resultHandler.handle(result) { data ->
                val newData = data?.firstOrNull {
                    it.type == "Trailer"
                }

                newData?.key?.let { youtubePlayerConfig(it) }
            }

        }
    }

    private fun youtubePlayerConfig(trailerLink: String) {
        with(binding) {
            lifecycle.addObserver(youtubePlayerView)
            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(trailerLink, 0F)
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.youtubePlayerView.release()
        _binding = null
    }

    companion object {
        const val TAG = "TrailerFragment"
    }
}