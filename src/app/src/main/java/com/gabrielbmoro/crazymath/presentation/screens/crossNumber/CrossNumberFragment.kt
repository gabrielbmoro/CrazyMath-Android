package com.gabrielbmoro.crazymath.presentation.screens.crossNumber

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.gabrielbmoro.crazymath.R
import com.gabrielbmoro.crazymath.databinding.FragmentCrossNumberBinding
import com.gabrielbmoro.crazymath.domain.model.UserLevel
import com.gabrielbmoro.crazymath.helpers.AdsHandler
import com.gabrielbmoro.crazymath.helpers.MediaPlayerHandler
import com.gabrielbmoro.crazymath.helpers.VibrationHandler
import com.gabrielbmoro.crazymath.presentation.extensions.changeStateFrom
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CrossNumberFragment : Fragment() {

    private val args: CrossNumberFragmentArgs by navArgs()
    private val viewModel: CrossNumberViewModel by viewModel() {
        val userLevel = UserLevel.fromValue(args.userLevelValue)
                ?: throw IllegalArgumentException("userLevel is required")
        parametersOf(userLevel)
    }
    private lateinit var binding: FragmentCrossNumberBinding

    private val adsHandler: AdsHandler by inject()
    private val mediaPlayerHandler: MediaPlayerHandler by inject()
    private val vibratorHandler: VibrationHandler by inject()

    private var mediaPlayer: MediaPlayer? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCrossNumberBinding.inflate(inflater, container, false)

        viewModel.liveDataLoading.observe(viewLifecycleOwner) {
            binding.progressBar.changeStateFrom(it)
        }

        lifecycle.addObserver(binding.stopwatch)

        defaultMode()

        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.fragmentGameZoneNavoImage.setOnClickListener {
            viewModel.goToUserFeedback()
        }

        return binding.root
    }

    private fun defaultMode() {
        binding.gameButton.setMode(
                enabled = true,
                res = R.string.play,
                listener = {
                    startGame()
                    sendMode()
                    enableGrid()
                },
                startDrawableRes = null
        )
        binding.numbers.setupAsEmptyStateMode()
    }

    private fun startGame() {
        viewModel.refresh()
        binding.stopwatch.start({
            finishGame()
        }, lifecycle)
    }

    private fun sendMode() {
        binding.gameButton.setMode(
                enabled = true,
                res = R.string.send,
                listener = {
                    finishGame()
                    defaultMode()
                    adsHandler.showIfIsReady(requireContext())
                },
                startDrawableRes = R.drawable.ic_air_plane
        )
        binding.numbers.visibility = View.VISIBLE
    }

    private fun enableGrid() {
        binding.numbers.setupAsGameZoneMode(viewModel.dimen(), viewModel.rows) { isEquationRight ->
            makeSoundEffect(isEquationRight)

            viewModel.onSelectTheEquation(
                    isEquationRight,
                    binding.stopwatch.currentTimeInSeconds()
            )
        }
    }

    private fun makeSoundEffect(isEquationRight: Boolean) {
        if (isEquationRight) {
            mediaPlayer = context?.let { ctx ->
                mediaPlayerHandler.createMediaPlayer(ctx, R.raw.right_answer)
            }
            mediaPlayerHandler.startThePlayer(mediaPlayer)
        } else {
            context?.let { ctx ->
                vibratorHandler.vibrate(ctx, VIBRATION_WRONG_ANSWER)
            }
        }
    }

    private fun finishGame() {
        binding.stopwatch.releaseCountDownTimer()
        binding.numbers.isEnabled = false
        viewModel.onFinishTheGame()
    }

    override fun onDestroyView() {
        lifecycle.removeObserver(binding.stopwatch)

        super.onDestroyView()
    }

    override fun onDestroy() {
        if (mediaPlayer != null)
            mediaPlayer = mediaPlayerHandler.stopAndReleaseThePlayer(mediaPlayer)

        super.onDestroy()
    }

    companion object {
        private const val VIBRATION_WRONG_ANSWER = 200L
    }
}